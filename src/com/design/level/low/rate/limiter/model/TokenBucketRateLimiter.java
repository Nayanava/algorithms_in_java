package com.design.level.low.rate.limiter.model;

import com.design.level.low.rate.limiter.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * @author nayanava
 */

public class TokenBucketRateLimiter extends RateLimiter {

    public int tokenCount;
    public int maxBucketSize;
    public int additionalToken;
    public Long lastUpdatedTS;
    public long refillIntervalInMS;

    public TokenBucketRateLimiter(int tokenCount, int maxBucketSize, Long lastUpdatedTS, long refillIntervalInMS) {
        super(RateLimiterType.TOKEN_BUCKET);
        this.tokenCount = tokenCount;
        this.maxBucketSize = maxBucketSize;
        this.refillIntervalInMS = refillIntervalInMS;
        this.lastUpdatedTS = System.currentTimeMillis();
        this.additionalToken = 2;
        this.refillToken();
    }

    @Override
    public RateLimitResponse validateLimit(RateLimitRequest request) {
        RateLimitResponse response = null;
        if(tokenCount > 0) {
            synchronized (this) {
                if(tokenCount > 0) {
                    tokenCount--;
                }
                response = new RateLimitResponse(true, tokenCount, null);
            }
        } else {
            response = new RateLimitResponse(false, null,  this.lastUpdatedTS + refillIntervalInMS - System.currentTimeMillis());
        }
        return response;
    }

    private void refillToken() {
        Schedulers.SCHEDULED_EXECUTOR_SERVICE.scheduleWithFixedDelay(() -> {
            synchronized (this) {
                this.tokenCount = Math.min(this.tokenCount + this.additionalToken, this.maxBucketSize);
                this.lastUpdatedTS = System.currentTimeMillis();
            }
        }, this.refillIntervalInMS, this.refillIntervalInMS, TimeUnit.MILLISECONDS);
    }
}

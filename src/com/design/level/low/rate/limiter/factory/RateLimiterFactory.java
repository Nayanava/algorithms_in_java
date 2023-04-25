package com.design.level.low.rate.limiter.factory;
/**
 * @author nayanava
 */

import com.design.level.low.rate.limiter.model.RateLimiter;
import com.design.level.low.rate.limiter.model.RateLimiterType;
import com.design.level.low.rate.limiter.model.SlidingWindowRateLimiter;
import com.design.level.low.rate.limiter.model.TokenBucketRateLimiter;

import java.io.*;

public class RateLimiterFactory {
    public static RateLimiter getRateLimiter(RateLimiterType type) {
        switch (type) {
            case TOKEN_BUCKET:
                return new TokenBucketRateLimiter(5, 10, null, 5000);
            case SLIDING_WINDOW:
                    return new SlidingWindowRateLimiter();
            default:
                throw new IllegalArgumentException("Invalid RateLimiterType " + type);

        }
    }
}

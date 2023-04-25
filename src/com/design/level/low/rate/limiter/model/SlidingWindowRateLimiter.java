package com.design.level.low.rate.limiter.model;
/**
 * @author nayanava
 */

import com.design.level.low.rate.limiter.service.IRateLimiterService;

public class SlidingWindowRateLimiter extends RateLimiter {
    public SlidingWindowRateLimiter() {
        super(RateLimiterType.SLIDING_WINDOW);
    }
    @Override
    public RateLimitResponse validateLimit(RateLimitRequest request) {
        return null;
    }
}

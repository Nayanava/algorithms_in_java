package com.design.level.low.rate.limiter.model;
/**
 * @author nayanava
 */

public abstract class RateLimiter {
    public static final RateLimiter DEFAULT = null;
    private RateLimiterType type;
    public RateLimiter(RateLimiterType type) {
        this.type = type;
    }
    public abstract RateLimitResponse validateLimit(RateLimitRequest request);
}

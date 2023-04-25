package com.design.level.low.rate.limiter.model;

/**
 * @author nayanava
 */
public enum RateLimiterType {
    TOKEN_BUCKET,
    LEAKY_BUCKET,
    FIXED_WINDOW,
    SLIDING_WINDOW
}

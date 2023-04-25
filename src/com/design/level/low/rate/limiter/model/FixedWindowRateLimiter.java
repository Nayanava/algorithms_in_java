package com.design.level.low.rate.limiter.model;
/**
 * @author nayanava
 */

import com.design.level.low.rate.limiter.service.IRateLimiterService;

public class FixedWindowRateLimiter implements IRateLimiterService {
    @Override
    public RateLimitResponse validateLimit(RateLimitRequest request) {
        return null;
    }
}

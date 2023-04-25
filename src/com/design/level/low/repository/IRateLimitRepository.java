package com.design.level.low.repository;
/**
 * @author nayanava
 */

import com.design.level.low.rate.limiter.model.RateLimiter;

public interface IRateLimitRepository {
    void create(RateLimiter rateLimiter, String request);
    RateLimiter getRateLimiter(String request);
    boolean update(RateLimiter rateLimiter, String request);
}

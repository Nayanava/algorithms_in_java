package com.design.level.low.repository.impl;
/**
 * @author nayanava
 */

import com.design.level.low.rate.limiter.model.RateLimiter;
import com.design.level.low.repository.IRateLimitRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRateLimitRepository implements IRateLimitRepository {
    private Map<String, RateLimiter> rateLimiterRepo = new ConcurrentHashMap<>();

    @Override
    public void create (RateLimiter rateLimiter, String request) {
        rateLimiterRepo.put(request, rateLimiter);
    }

    @Override
    public RateLimiter getRateLimiter(String request) {
        return rateLimiterRepo.getOrDefault(request, RateLimiter.DEFAULT);
    }

    @Override
    public boolean update(RateLimiter rateLimiter, String request) {
        return true;
    }

}

package com.design.level.low.rate.limiter.service;
/**
 * @author nayanava
 */

import com.design.level.low.rate.limiter.RateLimitConfig;
import com.design.level.low.rate.limiter.factory.RateLimiterFactory;
import com.design.level.low.rate.limiter.model.RateLimitRequest;
import com.design.level.low.rate.limiter.model.RateLimitResponse;
import com.design.level.low.rate.limiter.model.RateLimiter;
import com.design.level.low.rate.limiter.model.RateLimiterType;
import com.design.level.low.repository.IRateLimitRepository;

public class RateLimiterService implements IRateLimiterService {

    private IRateLimitRepository repository;
    public RateLimiterService(IRateLimitRepository repository) {
        this.repository = repository;
    }
    @Override
    public RateLimitResponse validateLimit(RateLimitRequest request) {
        RateLimiter rateLimiter = repository.getRateLimiter(request.getRequestId());
        if(rateLimiter == RateLimiter.DEFAULT) {
            RateLimiterType type = RateLimitConfig.getRateLimiter(request.apiName);
            rateLimiter = RateLimiterFactory.getRateLimiter(type);
            repository.create(rateLimiter, request.getRequestId());
        }
        RateLimitResponse response = rateLimiter.validateLimit(request);
        return response;
    }
}

package com.design.level.low.rate.limiter.middleware;
/**
 * @author nayanava
 */

import com.design.level.low.rate.limiter.service.IRateLimiterService;
import com.design.level.low.rate.limiter.service.RateLimiterService;
import com.design.level.low.repository.IRateLimitRepository;
import com.design.level.low.repository.impl.InMemoryRateLimitRepository;

import java.io.*;

public class RateLimitMiddleware {
    private String testApi = "/v1/someApi";
    public static void main(String[] args) {
        IRateLimitRepository repository = new InMemoryRateLimitRepository();
        IRateLimiterService rateLimiterService = new RateLimiterService(repository);

    }
}

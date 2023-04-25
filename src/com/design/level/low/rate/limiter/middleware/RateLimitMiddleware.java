package com.design.level.low.rate.limiter.middleware;
/**
 * @author nayanava
 */

import com.design.level.low.rate.limiter.RateLimitConfig;
import com.design.level.low.rate.limiter.model.RateLimitRequest;
import com.design.level.low.rate.limiter.model.RateLimitResponse;
import com.design.level.low.rate.limiter.model.RateLimiter;
import com.design.level.low.rate.limiter.schedulers.Schedulers;
import com.design.level.low.rate.limiter.service.IRateLimiterService;
import com.design.level.low.rate.limiter.service.RateLimiterService;
import com.design.level.low.repository.IRateLimitRepository;
import com.design.level.low.repository.impl.InMemoryRateLimitRepository;

import java.io.*;
import java.util.Random;

public class RateLimitMiddleware {
    private static String testApi = "/v1/someApi";
    public static void main(String[] args) throws InterruptedException {
        IRateLimitRepository repository = new InMemoryRateLimitRepository();
        IRateLimiterService rateLimiterService = new RateLimiterService(repository);
        RateLimitConfig.initConfig();
        RateLimitRequest[] requests = new RateLimitRequest[2];
        requests[0] = new RateLimitRequest();
        requests[0].apiName = testApi;
        requests[0].clientId = "user_123";
        requests[1] = new RateLimitRequest();
        requests[1].apiName = testApi;
        requests[1].clientId = "user_124";
        try {
            for(int i = 1; i < 10; i++) {
                RateLimitRequest request = getRequest(requests);
                RateLimitResponse response = rateLimiterService.validateLimit(request);
                if(!response.isSuccessfulRequest) {
                    System.out.println("for user " + request.clientId + response.toString());
                    Thread.sleep(response.rateLimitRetryAfter + 100);
                }
                System.out.println("for user " + request.clientId + response.toString());
            }
        } finally {
            Schedulers.SCHEDULED_EXECUTOR_SERVICE.shutdown();
        }
    }

    private static RateLimitRequest getRequest(RateLimitRequest[] requests) {
        Random rand = new Random();
        return requests[rand.nextInt(requests.length)];
    }
}

package com.design.level.low.rate.limiter;
/**
 * @author nayanava
 */

import com.design.level.low.rate.limiter.model.RateLimiter;
import com.design.level.low.rate.limiter.model.RateLimiterType;
import com.design.level.low.rate.limiter.model.SlidingWindowRateLimiter;
import com.design.level.low.rate.limiter.model.TokenBucketRateLimiter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RateLimitConfig {
    public static final Map<String, RateLimiter> apiRateLimitConfig = new HashMap<>();
    private static void config() {
        apiRateLimitConfig.put("/v1/someApi", new TokenBucketRateLimiter(5, 10, null, 5000));
        apiRateLimitConfig.put("/v1/someOtherApi", new SlidingWindowRateLimiter());
    }

    public static RateLimiter getRateLimiter(String apiName) {
        return apiRateLimitConfig.get(apiName);
    }
}

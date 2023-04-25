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
    public static final Map<String, RateLimiterType> apiRateLimitConfig = new HashMap<>();
    public static void initConfig() {
        apiRateLimitConfig.put("/v1/someApi", RateLimiterType.TOKEN_BUCKET);
        apiRateLimitConfig.put("/v1/someOtherApi", RateLimiterType.SLIDING_WINDOW);
    }

    public static RateLimiterType getRateLimiter(String apiName) {
        return apiRateLimitConfig.get(apiName);
    }
}

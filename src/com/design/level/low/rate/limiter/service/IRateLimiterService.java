package com.design.level.low.rate.limiter.service;
/**
 * @author nayanava
 */

import com.design.level.low.rate.limiter.model.RateLimitRequest;
import com.design.level.low.rate.limiter.model.RateLimitResponse;

import java.io.*;

public interface IRateLimiterService {
    RateLimitResponse validateLimit(RateLimitRequest request);
}

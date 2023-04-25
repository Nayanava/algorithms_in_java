package com.design.level.low.rate.limiter.model;
/**
 * @author nayanava
 */

import java.io.*;

public class RateLimitResponse {

    public boolean isSuccessfulRequest;
    public Integer rateLimitRemaining;
    public Long rateLimitRetryAfter;

    public RateLimitResponse(boolean isSuccessfulRequest, Integer rateLimitRemaining, Long rateLimitRetryAfter) {
        this.isSuccessfulRequest = isSuccessfulRequest;
        this.rateLimitRemaining = rateLimitRemaining;
        this.rateLimitRetryAfter = rateLimitRetryAfter;
    }
}

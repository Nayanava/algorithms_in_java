package com.design.level.low.rate.limiter.model;
/**
 * @author nayanava
 */

import java.io.*;
import java.util.Objects;

public class RateLimitRequest {
    public String clientId;
    public String apiName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateLimitRequest that = (RateLimitRequest) o;
        return clientId.equals(that.clientId) && apiName.equals(that.apiName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, apiName);
    }

    public String getRequestId() {
        return String.valueOf(this.hashCode());
    }
}

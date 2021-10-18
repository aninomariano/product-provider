package com.backend.productorderwrapper.exception;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.http.HttpStatus;

public class ApiException {

    private final HttpStatus status;
    private final String message;

    private ApiException(Builder builder) {
        status = builder.status;
        message = builder.message;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ApiException that = (ApiException) o;

        return new EqualsBuilder()
                .append(status, that.status)
                .append(message, that.message)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(status)
                .append(message)
                .toHashCode();
    }

    public static final class Builder {
        private HttpStatus status;
        private String message;

        private Builder() {
        }

        public Builder withStatus(HttpStatus val) {
            status = val;
            return this;
        }

        public Builder withMessage(String val) {
            message = val;
            return this;
        }

        public ApiException build() {
            return new ApiException(this);
        }
    }
}

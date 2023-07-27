package io.testomat.api.common;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public class ResponseDecorator<T> {

    private Response targetResponse;
    private int expectedDefaultStatusCode;
    private Class<T> targetClass;

    public ResponseDecorator() {
    }

    public ResponseDecorator(Response targetResponse, int expectedDefaultStatusCode, Class<T> targetClass) {
        this.targetResponse = targetResponse;
        this.expectedDefaultStatusCode = expectedDefaultStatusCode;
        this.targetClass = targetClass;
    }

    public ResponseDecorator assertStatusCode(int statusCode) {
        Assertions.assertThat(targetResponse.statusCode()).isEqualTo(statusCode);
        return this;
    }

    public <T> T toObject() {
        Assertions.assertThat(targetResponse.statusCode()).isEqualTo(expectedDefaultStatusCode);
        return (T) targetResponse.as(this.targetClass);
    }

    public <T> T as(Class<T> targetClass) {
        return targetResponse.as(targetClass);
    }

    public ResponseDecorator targetResponse(Response targetResponse) {
        this.targetResponse = targetResponse;
        return this;
    }

    public ResponseDecorator expectedDefaultStatusCode(int expectedDefaultStatusCode) {
        this.expectedDefaultStatusCode = expectedDefaultStatusCode;
        return this;
    }

    public ResponseDecorator targetClass(Class<T> targetClass) {
        this.targetClass = targetClass;
        return this;
    }

}

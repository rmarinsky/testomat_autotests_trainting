package io.testomat.testmanagement.api.controllers;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.testomat.testmanagement.common.LogRequestFilter;

public abstract class BaseController<T> {

    private String authToken;

    @Step
    public T withToken(String authToken) {
        this.authToken = authToken;
        return (T) this;
    }

    @Step
    public void cleanToken() {
        this.authToken = null;
    }

    protected RequestSpecification baseClient() {
        RequestSpecification authorization = RestAssured.given()
                .baseUri("https://beta.testomat.io/api")
                .filters(new LogRequestFilter(), new AllureRestAssured())
                .contentType("application/vnd.api+json");

        if (authToken != null) {
            authorization.header("Authorization", authToken);
        }
        return authorization;
    }

}

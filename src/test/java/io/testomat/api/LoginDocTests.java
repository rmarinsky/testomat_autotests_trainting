package io.testomat.api;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.testomat.ui.common.pw.Configuration;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginDocTests {

    static {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://beta.testomat.io")
                .setBasePath("/api")
                .log(LogDetail.ALL)
                .build();

        Configuration.baseUrl = "https://beta.testomat.io";
        Configuration.poolingInterval = 50;
        Configuration.defaultTimeout = 10000;
        Configuration.headless = false;
    }

    @SneakyThrows
    @Test
    @DisplayName("login and create test suite")
    void loginAndCreateTestSuite() {

        String authToken = RestAssured.given()
                .formParams(
                        "email", "newromka@gmail.com",
                        "password", "testtest"
                )
                .post("/login")
                .body().jsonPath().get("jwt");


        String targetProject = "asdfasdfasf";
        String targetSuiteBody = "{\"data\":{\"attributes\":{\"title\":\"%s\",\"file-type\":\"file\"," +
                "\"is-root\":false,\"sync\":false,\"test-count\":null,\"position\":null,\"file\":null,\"created-at\":null,\"updated-at\":null},\"type\":\"suites\"}}";

        String titleOfTestSuite = new Faker().book().title();
        targetSuiteBody = String.format(targetSuiteBody, titleOfTestSuite);


        RestAssured.given()
                .header("Authorization", authToken)
                .contentType("application/vnd.api+json")
                .body(targetSuiteBody)
                .post("/{targetProject}/suites", targetProject)
                .prettyPeek();

    }

}

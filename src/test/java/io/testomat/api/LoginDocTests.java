package io.testomat.api;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.testomat.ui.common.pw.Configuration;
import io.testomat.ui.common.pw.conditions.Condition;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.testomat.ui.common.pw.PlaywrightWrapper.find;
import static io.testomat.ui.common.pw.PlaywrightWrapper.open;

public class LoginDocTests {

    static {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://beta.testomat.io")
                .setBasePath("/api")
                .log(LogDetail.ALL)
                .build()
                .baseUri("https://beta.testomat.io");

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

        open("/users/sign_in");
        find("#content-desktop #new_user").shouldBe(Condition.visible);

        find("#content-desktop #user_email").fill("newromka@gmail.com");
        find("#content-desktop #user_password")
                .fill("testtest")
                .press("Enter")
                .shouldBe(Condition.hidden);

        open("/projects/" + targetProject);

        find(".list-group-wrapper a[href*='suite']", titleOfTestSuite).shouldBe(Condition.visible);

        Thread.sleep(10000);


    }

}

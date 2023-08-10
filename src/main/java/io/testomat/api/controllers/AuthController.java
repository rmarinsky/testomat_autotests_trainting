package io.testomat.api.controllers;


import io.qameta.allure.Step;
import io.restassured.http.ContentType;

public class AuthController extends BaseController<AuthController> {


    @Step
    public String loginUser(String email, String password) {
        return baseClient()
                .contentType(ContentType.URLENC)
                .formParams(
                        "email", email,
                        "password", password
                )
                .post("/login")
                .body().jsonPath().get("jwt");
    }

}

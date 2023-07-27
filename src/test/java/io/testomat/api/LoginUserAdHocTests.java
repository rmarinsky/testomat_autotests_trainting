package io.testomat.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.*;

public class LoginUserAdHocTests {

    static {
        baseURI = "https://beta.testomat.io";
        requestSpecification = new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }


    @Test
    @DisplayName("login user to testomat via UI emulation")
    void loginUserToTestomatViaUiEmulation() {
        var signInForm = given()
                .basePath("/users/sign_in")
                .get();

        var token = extractAuthTokenFormForm(signInForm.asString());

        var signInUser = given()
                .basePath("/users/sign_in")
                .redirects().follow(false)
                .accept(ContentType.HTML)
                .contentType(ContentType.URLENC)
                .cookies(signInForm.getDetailedCookies())
                .formParams(
                        "user[email]", "newromka@gmail.com",
                        "user[password]", "testtest",
                        "authenticity_token", token,
                        "user[remember_me]", "0",
                        "commit", "Sign in"
                ).post();

        given().cookies(signInUser.getDetailedCookies())
                .get(signInUser.header("location"))
                .prettyPeek();
    }

    private static String extractAuthTokenFormForm(String signInForm) {
        Pattern pattern = Pattern.compile("name=\"authenticity_token\" value=\"(.*?)\"");
        Matcher matcher = pattern.matcher(signInForm);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

}

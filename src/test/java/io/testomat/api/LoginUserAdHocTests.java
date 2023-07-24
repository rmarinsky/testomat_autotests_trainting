package io.testomat.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginUserAdHocTests {

    static {
        RestAssured.baseURI = "https://beta.testomat.io";
    }


    @Test
    @DisplayName("login user to testomat via UI emulation")
    void loginUserToTestomatViaUiEmulation() {
        String signInForm = RestAssured.given()
                .basePath("/users/sign_in")
                .get()
                .body().asString();

        extractAuthTokenFormForm(signInForm);


        Response signInPost = RestAssured.given()
                .basePath("/users/sign_in")
                .redirects().follow(false)
                .contentType(ContentType.URLENC)
                .formParams(
                        "user[email]", "newromka@gmail.com",
                        "user[password]", "testtest",
                        "authenticity_token", signInForm,
                        "user[remember_me]", "0",
                        "commit", "Sign in"
                ).post();
        System.out.println(signInPost.header("Set-Cookie"));
        signInPost
                .prettyPeek();
    }

    private static void extractAuthTokenFormForm(String signInForm) {
        Pattern pattern = Pattern.compile("name=\"authenticity_token\" value=\"(.*?)\"");
        Matcher matcher = pattern.matcher(signInForm);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }

}

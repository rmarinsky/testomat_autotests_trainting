package io.testomat.ui.pages;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class SignInPage extends  BasePage {

    public SignInPage singUser() {
        find("#user_email").val("newromka@gmail.com");
        find("#user_password")
                .val("p8qfCZ7Jv7pT!hh")
                .pressEnter();
        return this;
    }

    public SignInPage isLoaded() {
        $("#content-desktop #new_user").shouldBe(Condition.visible, Duration.ofSeconds(20));
        return this;
    }

}

package io.testomat.ui.pages;

import com.codeborne.selenide.Condition;
import io.testomat.testmanagement.common.CredentialsLoader;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class SignInPage extends BasePage {

    public SignInPage singInUser(CredentialsLoader.Credentials admin) {
        find("#user_email").val(admin.getUsername());
        find("#user_password")
                .val(admin.getPassword())
                .pressEnter();
        return this;
    }

    public SignInPage isLoaded() {
        $("#content-desktop #new_user").shouldBe(Condition.visible, Duration.ofSeconds(20));
        return this;
    }

}

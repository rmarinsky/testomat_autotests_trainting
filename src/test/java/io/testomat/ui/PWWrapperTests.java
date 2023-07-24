package io.testomat.ui;

import com.github.javafaker.Faker;
import io.testomat.ui.common.pw.Configuration;
import io.testomat.ui.common.pw.conditions.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.testomat.ui.common.pw.PlaywrightWrapper.find;
import static io.testomat.ui.common.pw.PlaywrightWrapper.open;

public class PWWrapperTests {

    Faker faker = new Faker();

    static {
        Configuration.baseUrl = "https://uat.testomat.io";
        Configuration.poolingInterval = 50;
        Configuration.defaultTimeout = 10000;
        Configuration.headless = false;
    }

    @BeforeEach
    void createContextAndPage() {
        open("/users/sign_in");
        find("#content-desktop #new_user").shouldBe(Condition.visible);

        find("#content-desktop #user_email").fill("newromka@gmail.com");
        find("#content-desktop #user_password")
                .fill("3y77b7HzrL2ebwQ!")
                .press("Enter")
                .shouldBe(Condition.hidden);

        open("/projects/new");
    }

    @Test
    @DisplayName("first test on playwright")
    void firstTestOnPlaywrigh() {
        String targetname = faker.book().title();
        find("#project_title").fill(targetname);

        find("[name=commit]").click();

        find("#app-loader").shouldBe(Condition.hidden);

        find(".back").click();

        find(".list-group-wrapper a[href*='suite']").shouldHas(Condition.text(targetname));
    }

}

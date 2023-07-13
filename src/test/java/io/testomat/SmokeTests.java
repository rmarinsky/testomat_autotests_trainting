package io.testomat;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import io.testomat.ui.dtos.BaseProjectInfo;
import io.testomat.ui.pages.ProjectsPage;
import io.testomat.ui.pages.SignInPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.testomat.ui.pages.PreLoaders.disappearsMainPreloader;

@DisplayName("Smoke tests for testomat.io")
public class SmokeTests {

    Faker faker = new Faker();
    ProjectsPage projectsPage = new ProjectsPage();


    static {
        Configuration.baseUrl = "https://app.testomat.io";
        Configuration.pollingInterval = 200;
    }


    @BeforeEach
    void openLoginForm() {
        open("/users/sign_in");
        new SignInPage()
                .isLoaded()
                .singUser();

        open("/projects/new");
    }

    @Test
    @DisplayName("Create test case flow test")
    void createTestCaseFlowTest() {
        //        $("a[href='/projects/new']").click();
        var targetTestSuiteName = faker.commerce().department();

        projectsPage
                .isLoaded()
                .createProjWithName(targetTestSuiteName);

        disappearsMainPreloader();

        var expectedProjectTile = BaseProjectInfo.builder()
                .name(targetTestSuiteName)
                .count("0")
                .label(BaseProjectInfo.ProjectType.CLASSICAL)
                .build();

        open("/");

        projectsPage
                .assertThat(expectedProjectTile)
                .hasCorrectInfo();
    }

    @Test
    @DisplayName("Create test case flow test")
    void createTestCaseFlowEasierTest() {
        new ProjectsPage().isLoaded();

        $("#project_title").val(faker.book().title());

        $("[name='commit']").click();

        $("#app-loader")
                .shouldBe(Condition.visible)
                .shouldBe(hidden, Duration.ofSeconds(20));

        $(".back").click();

        String targetTestSuiteName = faker.commerce().department();

        $(".list-group-wrapper a[href*='suite']").shouldHave(text(targetTestSuiteName), text("0"));
    }


}

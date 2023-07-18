package io.testomat.ui.pages;

import io.testomat.ui.asserts.ProjectsPageAsserts;
import io.testomat.ui.dtos.BaseProjectInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProjectsPage {

    String headerOfTypePage = "#content-desktop h2";

    public ProjectsPage isLoaded() {
        $("#content-desktop h2").shouldBe(
                text("New project"),
                Duration.ofSeconds(20)
        );
        return this;
    }

    public ProjectsPage createProjWithName(String targetTestSuiteName) {
        $("[placeholder='My Project']").val(targetTestSuiteName).pressEnter();
        return this;
    }

    public ProjectsPage closeSidebar() {
        $(".back").click();
        return this;
    }

    public ProjectsPageAsserts assertThat(BaseProjectInfo expectedProjectTile) {
        return new ProjectsPageAsserts(expectedProjectTile);
    }

}

package io.testomat.ui.asserts;

import io.testomat.ui.dtos.BaseProjectInfo;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;

public class ProjectsPageAsserts {

    private final BaseProjectInfo expectedProjectTile;

    public ProjectsPageAsserts(BaseProjectInfo expectedProjectTile) {
        this.expectedProjectTile = expectedProjectTile;
    }

    public ProjectsPageAsserts hasCorrectInfo() {
        var baseTile = $(by("title", expectedProjectTile.getName()));

        baseTile.$("h3").shouldHave(text(expectedProjectTile.getName())
                .because("Expected project name is equal to " + expectedProjectTile.getName()));
        baseTile.$("p").shouldHave(text(expectedProjectTile.getCount() + 1)
                .because("Expected project count is equal to " + expectedProjectTile.getCount()), text("tests")
        );
        baseTile.$("img").shouldBe(visible
                .because("Expected project count is equal to " + expectedProjectTile.getCount()));
        baseTile.$("span").shouldHave(text(expectedProjectTile.getLabel().toString())
                .because("Expected project label is equal to " + expectedProjectTile.getLabel().toString()));
        return this;
    }

}

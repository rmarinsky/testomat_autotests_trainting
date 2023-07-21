package io.testomat.ui.common.pw;

import com.microsoft.playwright.Locator;
import io.testomat.ui.common.pw.conditions.Condition;
import lombok.Data;

@Data
public class LocationActions {

    private final Locator locator;


    public LocationActions fill(String text) {
        locator.fill(text);
        return this;
    }

    public LocationActions press(String key) {
        locator.press(key);
        return this;
    }

    public LocationActions click() {
        locator.click();
        return this;
    }

    public LocationActions shouldBe(Condition condition) {
        condition.verify(this);
        return this;
    }

    public LocationActions should(Condition condition) {
        condition.verify(this);
        return this;
    }

    public LocationActions shouldHave(Condition condition) {
        condition.verify(this);
        return this;
    }

    public LocationActions shouldHas(Condition condition) {
        condition.verify(this);
        return this;
    }

}

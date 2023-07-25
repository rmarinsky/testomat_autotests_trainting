package io.testomat.ui.common.pw;

import com.microsoft.playwright.Locator;
import io.testomat.ui.common.pw.conditions.Condition;
import lombok.Data;

@Data
public class LocatorActions {

    private final Locator locator;

    public LocatorActions fill(String text) {
        locator.fill(text);
        return this;
    }

    public LocatorActions press(String key) {
        locator.press(key);
        return this;
    }

    public LocatorActions click() {
        locator.click();
        return this;
    }

    public LocatorActions shouldBe(Condition condition) {
        condition.verify(this);
        return this;
    }

    public LocatorActions should(Condition condition) {
        condition.verify(this);
        return this;
    }

    public LocatorActions shouldHave(Condition condition) {
        condition.verify(this);
        return this;
    }

    public LocatorActions shouldHas(Condition condition) {
        condition.verify(this);
        return this;
    }

}

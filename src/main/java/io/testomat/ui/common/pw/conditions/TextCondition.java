package io.testomat.ui.common.pw.conditions;

import com.microsoft.playwright.assertions.LocatorAssertions;
import io.testomat.ui.common.pw.Configuration;
import io.testomat.ui.common.pw.LocatorActions;
import lombok.AllArgsConstructor;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@AllArgsConstructor
public class TextCondition implements Condition {

    private final String expectedText;

    @Override
    public void verify(LocatorActions locatorActions) {
        assertThat(locatorActions.getLocator()).hasText(
                expectedText,
                new LocatorAssertions.HasTextOptions().setTimeout(Configuration.defaultTimeout)
        );
    }

}

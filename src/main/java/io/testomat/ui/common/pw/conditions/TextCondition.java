package io.testomat.ui.common.pw.conditions;

import com.microsoft.playwright.assertions.LocatorAssertions;
import io.testomat.ui.common.pw.Configuration;
import io.testomat.ui.common.pw.LocationActions;
import lombok.AllArgsConstructor;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@AllArgsConstructor
public class TextCondition implements Condition {

    private final String expectedText;

    @Override
    public void verify(LocationActions locationActions) {
        assertThat(locationActions.getLocator()).hasText(
                expectedText,
                new LocatorAssertions.HasTextOptions().setTimeout(Configuration.defaultTimeout)
        );
    }

}

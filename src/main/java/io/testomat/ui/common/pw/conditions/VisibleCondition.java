package io.testomat.ui.common.pw.conditions;

import com.microsoft.playwright.assertions.LocatorAssertions;
import io.testomat.ui.common.pw.Configuration;
import io.testomat.ui.common.pw.LocatorActions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class VisibleCondition implements Condition {

    @Override
    public void verify(LocatorActions locatorActions) {
        assertThat(locatorActions.getLocator()).isVisible(
                new LocatorAssertions.IsVisibleOptions().setTimeout(Configuration.defaultTimeout)
        );
    }

}

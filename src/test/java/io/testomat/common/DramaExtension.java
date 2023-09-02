package io.testomat.common;

import io.testomat.ui.common.pw.Configuration;
import io.testomat.ui.common.pw.PlaywrightWrapper;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DramaExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        PlaywrightWrapper.initTestContext(Configuration.saveTraces, getTestName(context));
    }

    @Override
    public void afterEach(ExtensionContext context) {
        PlaywrightWrapper.closeContext(Configuration.saveTraces, getTestName(context));
    }

    private String getTestName(ExtensionContext context) {
        return context.getRequiredTestClass().getName() + " " + context.getDisplayName();
    }

}

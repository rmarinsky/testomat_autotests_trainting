package io.testomat.ui.common.pw;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

public class PlaywrightWrapper {

    private static final ConcurrentHashMap<Long, PWStage> pwStage = new ConcurrentHashMap<>();

    public static PWStage pwStage() {
        return pwStage.computeIfAbsent(Thread.currentThread().getId(), k -> {
            var playwright = Playwright.create();
            var browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(Configuration.headless)
                            .setTimeout(Configuration.browserToStartTimeout)
                            .setDevtools(Configuration.devTools)
                            .setSlowMo(Configuration.poolingInterval)
                            .setTracesDir(Paths.get(Configuration.tracesPath))
            );
            return new PWStage(null, null, playwright, browser);
        });
    }

    public static void close() {
        long threadId = Thread.currentThread().getId();

        if (pwStage.containsKey(threadId)) {
            pwStage.get(threadId).getPage().close();
            pwStage.get(threadId).getContext().close();
            pwStage.get(threadId).getBrowser().close();
            pwStage.get(threadId).getPlaywright().close();
            pwStage.remove(threadId);
        }

    }

    public static void initTestContext(boolean traces, String testName) {
        var newContextOptions = new Browser.NewContextOptions();
        newContextOptions.baseURL = Configuration.baseUrl;

        var pwStage = pwStage();
        var browserContext = pwStage.getBrowser().newContext(newContextOptions);
        if (traces) {
            browserContext.tracing().start(new Tracing.StartOptions()
                    .setTitle(testName)
                    .setName(testName + ".zip")
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true)
            );
        }
        var targetPage = browserContext.newPage();

        pwStage.setContext(browserContext);
        pwStage.setPage(targetPage);
    }

    public static void closeContext(boolean traces, String testName) {
        var pwStage = pwStage();

        var targetContext = pwStage.getContext();
        if (traces) {
            targetContext.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get(Configuration.tracesPath, testName + ".zip"))
            );
        }
        pwStage.getPage().close();
        targetContext.close();
    }

    public static void open(String url) {
        var targetUrl = StringUtils.isNotBlank(Configuration.baseUrl) ? Configuration.baseUrl + url : url;
        pwStage().getPage().navigate(targetUrl);
    }

    public static Locator find(String selector, int index) {
        return pwStage().getPage().locator(selector).nth(index);
    }

    public static LocatorActions find(String selector) {
        return new LocatorActions(pwStage().getPage().locator(selector).first());
    }

    public static LocatorActions find(String selector, String text) {
        return new LocatorActions(pwStage().getPage().locator(selector).filter(
                new Locator.FilterOptions().setHasText(text)
        ));
    }

    public void mockRequestWith(String url, String response) {
        pwStage().getPage().route(url, route -> route.fulfill(
                new Route.FulfillOptions()
                        .setStatus(200)
                        .setBody(response)
        ));
    }

    public void clearCoolies() {
        pwStage().getContext().clearCookies();
    }

    public void waitForUrl(String url) {
        PlaywrightAssertions.assertThat(pwStage().getPage()).hasURL(url);
    }

    @Data
    public static class PWStage {

        private BrowserContext context;
        private Page page;
        private final Playwright playwright;
        private final Browser browser;

        public PWStage(BrowserContext browserContext, Page page, Playwright playwright, Browser browser) {
            this.context = browserContext;
            this.page = page;
            this.playwright = playwright;
            this.browser = browser;
        }

    }

}

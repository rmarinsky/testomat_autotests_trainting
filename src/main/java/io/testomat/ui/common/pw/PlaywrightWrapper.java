package io.testomat.ui.common.pw;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import lombok.Data;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

@UtilityClass
public class PlaywrightWrapper {

    private static final ConcurrentHashMap<Long, PWStage> pwStage = new ConcurrentHashMap<>();

    public PWStage pwStage() {
        long threadId = Thread.currentThread().getId();
        if (!pwStage.containsKey(threadId)) {
            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(Configuration.headless)
                            .setTimeout(Configuration.browserToStartTimeout)
                            .setDevtools(false)
                            .setSlowMo(Configuration.poolingInterval)
            );
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            pwStage.put(threadId, new PWStage(context, page, playwright, browser));
        }

        return pwStage.get(threadId);
    }

    public void close() {
        long threadId = Thread.currentThread().getId();

        if (pwStage.containsKey(threadId)) {
            pwStage.get(threadId).getPage().close();
            pwStage.get(threadId).getContext().close();
            pwStage.get(threadId).getBrowser().close();
            pwStage.get(threadId).getPlaywright().close();
            pwStage.remove(threadId);
        }

    }

    public void open(String url) {
        var targetUrl = StringUtils.isNotBlank(Configuration.baseUrl) ? Configuration.baseUrl + url : url;
        pwStage().getPage().navigate(targetUrl);
    }

    public LocatorActions find(String selector) {
        return new LocatorActions(pwStage().getPage().locator(selector).first());
    }

    public LocatorActions find(String selector, String text) {
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

    public void waitForUrl(String url) {
        PlaywrightAssertions.assertThat(pwStage().getPage()).hasURL(url);
    }

    @Data
    public static class PWStage {

        private final BrowserContext context;
        private final Page page;
        private final Playwright playwright;
        private final Browser browser;

    }

}

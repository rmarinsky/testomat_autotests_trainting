package io.testomat.ui

import com.microsoft.playwright.*
import com.microsoft.playwright.BrowserType.LaunchOptions
import com.microsoft.playwright.assertions.PlaywrightAssertions
import io.testomat.ui.common.pw.Configuration
import java.util.concurrent.ConcurrentHashMap


private val pwStages = ConcurrentHashMap<Long, PWStages>()

fun pwStages(): PWStages {
    val threadId = Thread.currentThread().id
    if (!pwStages.containsKey(threadId)) {
        val playwright = Playwright.create()
        val browser = playwright.chromium().launch(
                LaunchOptions()
                        .setHeadless(Conf.headless)
                        .setTimeout(Conf.browserToStartTimeout)
                        .setDevtools(Conf.devTools)
                        .setSlowMo(Configuration.poolingInterval)
        )
        val context = browser.newContext()
        val page = context.newPage()
        pwStages[threadId] = PWStages(context, page, playwright, browser)
    }
    return pwStages[threadId]!!
}

fun close() {
    Thread.currentThread().id.let {
        if (pwStages.containsKey(it)) {
            pwStages[it]!!.page.close()
            pwStages[it]!!.context.close()
            pwStages[it]!!.browser.close()
            pwStages[it]!!.playwright.close()
            pwStages.remove(it)
        }
    }
}

fun open(url: String) {
    val targetUrl = Conf.baseUrl ?: url
    pwStages().page.navigate(targetUrl)
}

fun find(selector: String): Locator {
    return pwStages().page.locator(selector)
}

fun find(selector: String, withText: String): Locator {
    return pwStages().page.locator(selector).filter(Locator.FilterOptions().setHasText(withText))
}


fun Locator.containsText(text: String) {
    PlaywrightAssertions.assertThat(this).containsText(text)
}

fun Locator.shouldBeVisible() {
    PlaywrightAssertions.assertThat(this).isVisible()
}

fun Locator.shouldBeHidden() {
    PlaywrightAssertions.assertThat(this).isHidden()
}

data class PWStages(
        var context: BrowserContext,
        var page: Page,
        var playwright: Playwright,
        var browser: Browser
)


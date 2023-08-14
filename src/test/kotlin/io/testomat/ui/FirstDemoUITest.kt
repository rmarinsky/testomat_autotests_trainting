package io.testomat.ui

import com.github.javafaker.Faker
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FirstDemoUITest {

    var faker = Faker()

    companion object {
        init {
            Conf.baseUrl = "https://uat.testomat.io"
            Conf.poolingInterval = 0.0
            Conf.defaultTimeout = 10000.0
            Conf.headless = false
        }
    }

    @BeforeEach
    fun createContextAndPage() {
        open("/users/sign_in")
        find("#content-desktop #new_user").shouldBeVisible()
        find("#content-desktop #user_email").fill("newromka@gmail.com")
        find("#content-desktop #user_password").apply {
            fill("3y77b7HzrL2ebwQ!")
            press("Enter")
            shouldBeHidden()
        }

        //example
        listOf("", "", "").filter { it.isNotEmpty() }.sumOf { it.length }
        mapOf("key" to "value")

        open("/projects/new")
    }

    @Test
    fun `first test on playwrigh`() {
        val targetname = "${faker.book().title()} ${faker.book().author()}"

        find("#project_title").fill(targetname)
        find("[name=commit]").click()
        find("#app-loader").shouldBeHidden()
        find(".back").click()
        find(".list-group-wrapper a[href*='suite']").containsText(targetname)
    }

}

package io.testomat.api

import com.github.javafaker.Faker
import io.testomat.SuiteDto
import org.junit.jupiter.api.Test

class APiTest {


    @Test
    fun `demo test api for new reified controller`() {
        val targetSuite = SuiteDto(id = Long.MAX_VALUE, name = Faker().name().firstName())

        println(SuiteController().postSuite(targetSuite))
    }
}

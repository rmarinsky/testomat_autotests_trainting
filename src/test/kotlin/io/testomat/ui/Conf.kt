package io.testomat.ui

object Conf {
    var baseUrl: String? = null // This will be null by default

    var headless: Boolean = true
    var devTools: Boolean = false
    var defaultTimeout: Double = 4000.0
    var poolingInterval: Double = 100.0

    var browserToStartTimeout: Double = 40000.0
}

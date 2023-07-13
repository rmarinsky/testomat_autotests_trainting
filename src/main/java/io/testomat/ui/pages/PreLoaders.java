package io.testomat.ui.pages;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.$;

public class PreLoaders {
    
    
    public static void disappearsMainPreloader() {
        $("#app-loader").shouldBe(hidden, Duration.ofSeconds(20));
    }



}

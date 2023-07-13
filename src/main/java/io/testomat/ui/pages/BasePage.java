package io.testomat.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    public String baseDeviceType = "desktop";

    public SelenideElement find(String childSelector) {
        return $("#content-" + baseDeviceType + " " + childSelector);
    }


}

package io.testomat;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.Duration;

public class BaseTest {

    @RegisterExtension
    TextReportExtension textReportExtension = new TextReportExtension().onFailedTest(true).onSucceededTest(true);

    static {
        Configuration.baseUrl = "https://app.testomat.io";
        Configuration.browserSize = "1024*1024";
        Configuration.clickViaJs = true;
        Configuration.fastSetValue = true;
        Configuration.remoteReadTimeout = Duration.ofSeconds(30).toMillis();
        Configuration.remoteConnectionTimeout = Duration.ofSeconds(30).toMillis();
    }

}

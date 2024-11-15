package io.testomat.api;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import io.testomat.api.asserts.SuiteResponseAsserts;
import io.testomat.testmanagement.api.controllers.AuthController;
import io.testomat.testmanagement.api.controllers.SuitesController;
import io.testomat.testmanagement.api.dtos.postSuite.SuitesRequest;
import io.testomat.testmanagement.api.dtos.postSuite.SuitesRequest.DataDetail;
import io.testomat.testmanagement.api.dtos.postSuite.SuitesResponse;
import io.testomat.ui.common.pw.Configuration;
import io.testomat.ui.common.pw.conditions.Condition;
import org.junit.jupiter.api.*;

import static io.testomat.testmanagement.api.dtos.postSuite.SuitesRequest.DataDetail.Attributes;
import static io.testomat.ui.common.pw.PlaywrightWrapper.find;
import static io.testomat.ui.common.pw.PlaywrightWrapper.open;
import static io.testomat.ui.common.pw.conditions.Condition.text;

@Tags({@Tag("api"), @Tag("smoke")})
@DisplayName("Suites tests")
public class SuitesTests {

    private final Book book = new Faker().book();

    static {
        Configuration.baseUrl = "https://beta.testomat.io";
        Configuration.poolingInterval = 50;
        Configuration.defaultTimeout = 10000;
        Configuration.headless = true;
    }

    private String authToken;

    @BeforeEach
    void beforeEach() {
        this.authToken = new AuthController().loginUser("newromka@gmail.com", "testtest");
    }

    String targetProject = "asdfasdfasf";

    @Test
    @DisplayName("positive suites tests")
    void positiveSuitesTests() {
        var targetTestSuite = getSuitesDto();

        var suitesController = new SuitesController().withToken(authToken);

        SuitesResponse suitesResponse = (SuitesResponse) suitesController
                .createSuite(targetProject, targetTestSuite)
                .toObject();

        SuitesResponse getSuite = suitesController
                .getSuite(targetProject, suitesResponse.getData().getId())
                .toObject();

        new SuiteResponseAsserts(getSuite)
                .idIsNotNull();

        open("/users/sign_in");

        find("#content-desktop #user_email").fill("newromka@gmail.com");
        find("#content-desktop #user_password")
                .fill("testtest")
                .press("Enter")
                .shouldBe(Condition.hidden);

        open("/projects/" + targetProject + "/suite/" + suitesResponse.getData().getId());
        find(".detailed-view-suite-body h3").shouldHas(text(suitesResponse.getData().getAttributes().getTitle()));
    }

    @Test
    @DisplayName("auth negative tests")
    void authNegativeTests() {


        SuitesController suitesController = new SuitesController();
        suitesController.cleanToken();

        suitesController.getSuites(targetProject)
                .assertStatusCode(401);
        suitesController.createSuite("", getSuitesDto())
                .assertStatusCode(401);
        suitesController.getSuite(targetProject, "")
                .assertStatusCode(401);
    }


    private SuitesRequest getSuitesDto() {
        var targetTestSuite = SuitesRequest.builder()
                .datas(DataDetail.builder()
                        .type("suite")
                        .attributes(
                                Attributes.builder()
                                        .description(book.genre())
                                        .title(book.title())
                                        .build())
                        .build()
                ).build();
        return targetTestSuite;
    }


}

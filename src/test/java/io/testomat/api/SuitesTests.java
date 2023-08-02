package io.testomat.api;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import io.testomat.api.asserts.SuiteResponseAsserts;
import io.testomat.api.controllers.AuthController;
import io.testomat.api.controllers.SuitesController;
import io.testomat.api.dtos.postSuite.SuitesRequest;
import io.testomat.api.dtos.postSuite.SuitesRequest.DataDetail;
import io.testomat.api.dtos.postSuite.SuitesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.testomat.api.dtos.postSuite.SuitesRequest.DataDetail.Attributes;

public class SuitesTests {

    private final Book book = new Faker().book();
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
                .idIsNotNull()
                .fileTypeIsPdf();

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

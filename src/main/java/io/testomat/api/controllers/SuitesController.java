package io.testomat.api.controllers;

import io.qameta.allure.Step;
import io.testomat.api.common.ResponseDecorator;
import io.testomat.api.dtos.postSuite.SuitesRequest;
import io.testomat.api.dtos.postSuite.SuitesResponse;

public class SuitesController extends BaseController<SuitesController> {

    @Step
    public ResponseDecorator createSuite(String targetProjectId, SuitesRequest targetSuiteBody) {
        return new ResponseDecorator(
                baseClient()
                        .body(targetSuiteBody)
                        .post("/{targetProject}/suites", targetProjectId),
                200,
                SuitesResponse.class
        );
    }

    @Step
    public ResponseDecorator<SuitesResponse> getSuites(String targetProjectId) {
        return new ResponseDecorator<>(
                baseClient().get("/{targetProject}/suites", targetProjectId),
                200,
                SuitesResponse.class
        );
    }

    @Step
    public ResponseDecorator<SuitesResponse> getSuite(String targetProjectId, String targetSuiteId) {
        return new ResponseDecorator(
                baseClient().get("/{targetProject}/suites/{targetSuite}", targetProjectId, targetSuiteId),
                200,
                SuitesResponse.class
        );
    }

}

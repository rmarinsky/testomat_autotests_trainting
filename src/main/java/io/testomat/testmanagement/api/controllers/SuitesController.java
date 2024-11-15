package io.testomat.testmanagement.api.controllers;

import io.qameta.allure.Step;
import io.testomat.testmanagement.api.common.ResponseDecorator;
import io.testomat.testmanagement.api.dtos.postSuite.SuitesRequest;
import io.testomat.testmanagement.api.dtos.postSuite.SuitesResponse;

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
    public ResponseDecorator<SuitesResponse> getSuitesV2(String targetProjectId) {
        return new ResponseDecorator<>(
                baseClient().get("/v2/{targetProject}/suites", targetProjectId),
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

package hu.sokizoltan.rsstest.apitest;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ApiTestServer {

    @Inject
    ApiTestService apiTestService;

    @Inject
    public ApiTestServer() {
    }

    public Observable<List<ApiTestResponse>> getApiTest() {
        return apiTestService.apiTest();
    }

}

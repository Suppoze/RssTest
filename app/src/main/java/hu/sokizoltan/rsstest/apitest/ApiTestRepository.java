package hu.sokizoltan.rsstest.apitest;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ApiTestRepository {

    @Inject
    ApiTestServer apiTestServer;

    @Inject
    public ApiTestRepository() {

    }

    public Observable<ApiTestResponse> getApiTestData() {
        return apiTestServer.getApiTest();
    }
}

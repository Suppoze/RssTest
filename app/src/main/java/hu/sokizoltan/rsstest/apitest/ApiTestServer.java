package hu.sokizoltan.rsstest.apitest;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ApiTestServer implements ApiTestDataSource {

    @Inject
    ApiTestService apiTestService;

    @Inject
    public ApiTestServer() {

    }

    public Observable<ApiTestResponse> getApiTest() {
        return apiTestService.apiTest().flatMap(Observable::fromIterable);
    }

}

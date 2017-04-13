package hu.sokizoltan.rsstest.apitest;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class ApiTestServer {

    @Inject
    ApiTestService apiTestService;

    @Inject
    public ApiTestServer() {
    }

    public Observable<ResponseBody> getApiTest() {
        return apiTestService.apiTest();
    }

}

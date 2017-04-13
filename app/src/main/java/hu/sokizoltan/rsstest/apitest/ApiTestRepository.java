package hu.sokizoltan.rsstest.apitest;

import javax.inject.Inject;

import hu.sokizoltan.rsstest.jsonview.JsonFileManager;
import io.reactivex.Observable;

public class ApiTestRepository {

    @Inject
    ApiTestServer apiTestServer;

    @Inject
    JsonFileManager jsonFileManager;

    @Inject
    public ApiTestRepository() {

    }

    public Observable<ApiTestResponse> getApiTestData() {

        return apiTestServer.getApiTest()
                .doAfterNext((list) -> jsonFileManager.saveToFile(list))
                .flatMap(Observable::fromIterable);
    }
}

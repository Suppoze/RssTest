package hu.sokizoltan.rsstest.apitest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.inject.Inject;

import hu.sokizoltan.rsstest.jsonview.JsonFileManager;
import io.reactivex.Observable;

public class ApiTestRepository {

    @Inject
    ApiTestServer apiTestServer;

    @Inject
    JsonFileManager jsonFileManager;

    @Inject
    Gson gson;

    @Inject
    public ApiTestRepository() {
    }

    public Observable<ApiTestResponse> getApiTestData() {

        Type listType = new TypeToken<ArrayList<ApiTestResponse>>() {
        }.getType();

        return apiTestServer.getApiTest()
                .flatMap(responseBody -> Observable.just(jsonFileManager.saveToFile(responseBody.string()))
                        .doOnNext(__ -> jsonFileManager.notifyFileChanged()))
                .flatMap(responseString -> Observable.fromIterable(gson.fromJson(responseString, listType)));
    }
}

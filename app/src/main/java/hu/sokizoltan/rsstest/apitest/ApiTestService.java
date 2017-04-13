package hu.sokizoltan.rsstest.apitest;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiTestService {

    @GET("api/json/get/claNBwNqOG")
    Observable<List<ApiTestResponse>> apiTest();

}

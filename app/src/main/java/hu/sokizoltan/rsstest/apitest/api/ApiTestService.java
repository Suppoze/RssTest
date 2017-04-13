package hu.sokizoltan.rsstest.apitest.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface ApiTestService {

    @GET("api/json/get/claNBwNqOG")
    Observable<ResponseBody> apiTest();

}

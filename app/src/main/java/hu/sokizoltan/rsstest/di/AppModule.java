package hu.sokizoltan.rsstest.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.sokizoltan.rsstest.RssTestApplication;
import hu.sokizoltan.rsstest.apitest.ApiTestService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private RssTestApplication app;

    public AppModule(RssTestApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return app;
    }

    @Provides
    @Singleton
    public RssTestApplication provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    public Retrofit provideRetroFit() {
        return new Retrofit.Builder()
                .baseUrl("http://www.json-generator.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public ApiTestService provideApiTestService(Retrofit retrofit) {
        return retrofit.create(ApiTestService.class);
    }

}
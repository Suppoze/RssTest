package hu.sokizoltan.rsstest;


import android.app.Application;

import hu.sokizoltan.rsstest.di.AppComponent;
import hu.sokizoltan.rsstest.di.AppModule;
import hu.sokizoltan.rsstest.di.DaggerAppComponent;

public class RssTestApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

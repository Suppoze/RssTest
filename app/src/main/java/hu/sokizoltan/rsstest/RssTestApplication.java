package hu.sokizoltan.rsstest;


import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;

import hu.sokizoltan.rsstest.battery.BatteryReceiver;
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

        registerReceiver(new BatteryReceiver(), new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

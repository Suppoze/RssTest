package hu.sokizoltan.rsstest.battery;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import hu.sokizoltan.rsstest.R;

public class BatteryLevelReceiver extends BroadcastReceiver {

    private static final String BATTERY_LEVEL = "level";

    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BATTERY_LEVEL, 0);

        Notification noti = new NotificationCompat.Builder(context)
                .setContentTitle(level + "%")
                .setContentText(context.getString(R.string.battery_state))
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .build();

        NotificationManager notiManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notiManager.notify(001, noti);

    }

}
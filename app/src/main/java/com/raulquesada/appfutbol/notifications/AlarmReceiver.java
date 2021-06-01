package com.raulquesada.appfutbol.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.content.ContextCompat;

/**
 * The type Alarm receiver.
 */
public class AlarmReceiver extends BroadcastReceiver {
    /**
     * The constant EXTRA_RECEIVER_TITLE.
     */
    public static final String EXTRA_RECEIVER_TITLE = "titlereceiver";
    /**
     * The constant EXTRA_RECEIVER_MESSAGE.
     */
    public static final String EXTRA_RECEIVER_MESSAGE = "messagereceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra(NotificationsManager.EXTRA_TITLE);
        String message = intent.getStringExtra(NotificationsManager.EXTRA_MESSAGE);
        Intent service = new Intent(context, ResultService.class);
        service.putExtra(EXTRA_RECEIVER_MESSAGE,message);
        service.putExtra(EXTRA_RECEIVER_TITLE,title);
        service.setData(Uri.parse("custom://"+System.currentTimeMillis()));
        ContextCompat.startForegroundService(context, service);
    }
}

package com.raulquesada.appfutbol.notifications;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.raulquesada.appfutbol.activities.MainActivity;
import com.raulquesada.appfutbol.R;

/**
 * The type Result service.
 */
public class ResultService extends IntentService  {

    /**
     * The Notification manager.
     */
    private NotificationManager notificationManager;
    /**
     * The Pending intent.
     */
    private PendingIntent pendingIntent;
    /**
     * The constant NOTIFICATION_ID.
     */
    private static int NOTIFICATION_ID = 1;
    /**
     * The Notification.
     */
    private Notification notification;
    /**
     * The Context.
     */
    private Context context;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public ResultService() {
        super("SERVICE");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String message = intent.getStringExtra(AlarmReceiver.EXTRA_RECEIVER_MESSAGE);
        String title = intent.getStringExtra(AlarmReceiver.EXTRA_RECEIVER_TITLE);
        context = this.getApplicationContext();
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent mIntent = new Intent(this, MainActivity.class); //Cargamos el intent al mainactivity
        mIntent.putExtra(MainActivity.EXTRA_MAIN,2);
        Resources res = this.getResources();
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//Comprobamos versión de Android
            final int NOTIFY_ID = 0; // ID notification
            String id = context.getString(R.string.app_name); // id del canal
            PendingIntent pendingIntent;
            NotificationCompat.Builder builder;
            NotificationManager notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (notifManager == null) {
                notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            }
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = notifManager.getNotificationChannel(id); //Canal de notificación
            if (mChannel == null) { //Cargamos el canal de atributos
                mChannel = new NotificationChannel(id, title, importance);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(context, id);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setCategory(Notification.CATEGORY_SERVICE)//Modificamos la notificación
                    .setSmallIcon(R.mipmap.ic_launcher)   // required
                    .setContentTitle(title)
                    .setContentText(message)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setSound(soundUri)
                    .setContentIntent(pendingIntent)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            Notification notification = builder.build();
            notifManager.notify(NOTIFY_ID, notification);

            startForeground(1, notification);

        } else {
            pendingIntent = PendingIntent.getActivity(context, 1, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification = new NotificationCompat.Builder(this)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(android.R.drawable.ic_notification_overlay)
                    .setLargeIcon(BitmapFactory.decodeResource(res, android.R.drawable.ic_notification_overlay))
                    .setSound(soundUri)
                    .setAutoCancel(true)
                    .setContentTitle(title).setCategory(Notification.CATEGORY_SERVICE)
                    .setContentText(message).build();
            notificationManager.notify(NOTIFICATION_ID, notification);
        }
    }
}

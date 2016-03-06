package br.gov.sp.ima.hackathon.monitor156.notificatioin;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import br.gov.sp.ima.hackathon.monitor156.R;

public class BigBangNotification {

    public static final int NOTIFICATION_ID = 100;

    public static void notify(Context context, int title, int message, PendingIntent intent) {

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setContentIntent(intent)
                        .setSmallIcon(R.drawable.calendar_check)
                        .setContentTitle(context.getString(title))
                        .setContentText(context.getString(message))
                        .setAutoCancel(true)
                        .setSound(alarmSound);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}

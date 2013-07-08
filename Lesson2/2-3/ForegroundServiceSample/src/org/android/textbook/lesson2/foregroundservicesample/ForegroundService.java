
package org.android.textbook.lesson2.foregroundservicesample;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;

public class ForegroundService extends Service {
    private static final int NOTIFY_ID = 1;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @SuppressLint("NewApi")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Notificationの生成
        Notification notification = new Notification.Builder(this).
                setContentTitle("title").setContentText("text")
                .setSmallIcon(R.drawable.ic_launcher).build();

        // Notificationの表示
        NotificationManager manager = (NotificationManager) 
                getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFY_ID, notification);

        // Foregroundサービスとして登録
        startForeground(NOTIFY_ID, notification);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

}

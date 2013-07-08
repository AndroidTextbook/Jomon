
package org.android.textbook.lesson3.notificationsample;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;

public class NotificationSample extends Activity {

    private static final int NOTIFICATION_TYPE_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_sample);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("３時限目");
        mBuilder.setSmallIcon(android.R.drawable.stat_notify_chat);
        mBuilder.setContentText("ノーティフィケーションサンプル");
        mBuilder.setTicker("通知");

        NotificationManager mNM = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        mNM.notify(NOTIFICATION_TYPE_1, mBuilder.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.notification_sample, menu);
        return true;
    }
}

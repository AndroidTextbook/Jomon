
package com.android_textbook.learnjunit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PowerConnectedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())) {
            // 電源が接続されたらサービスを起動する
            Intent serviceIntent = new Intent(context, CountUpService.class);
            context.startService(serviceIntent);
        } else if (Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction())) {
            // 電源が切断されたらサービスを停止する
            Intent serviceIntent = new Intent(context, CountUpService.class);
            context.stopService(serviceIntent);
        }
    }
}

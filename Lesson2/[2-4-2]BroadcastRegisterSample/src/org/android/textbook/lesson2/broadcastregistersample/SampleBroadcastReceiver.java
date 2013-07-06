
package org.android.textbook.lesson2.broadcastregistersample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SampleBroadcastReceiver extends BroadcastReceiver {
    private static final String LOGTAG = "SampleBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(LOGTAG, "get action:" + intent.getAction());

    }

}

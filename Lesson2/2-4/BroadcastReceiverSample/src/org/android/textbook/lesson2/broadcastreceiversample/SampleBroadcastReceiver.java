
package org.android.textbook.lesson2.broadcastreceiversample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class SampleBroadcastReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = "SampleBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_TIMEZONE_CHANGED.equals(action)) {
            Log.i(LOG_TAG, "GET Broadcast Event @TIMEZONE_CHANGED");
        }
        
        
        if(BroadCastReceiverMainActivity.SAMPLE_ACTION.equals(action)){
            Log.i(LOG_TAG, "GET Broadcast Event @SAMPLE_ACTION");
        }
    }

}


package com.android_textbook.learnjunit;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends Activity {
    public static final int EVENT_START = 1;

    private static class HandlerEx extends Handler {
        @Override
        public void handleMessage(Message msg) {
            SplashActivity activity = (SplashActivity)msg.obj;
            if (msg.what == EVENT_START) {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
            }
        }
    }

    private static HandlerEx mHandler = new HandlerEx();

    @Override
    protected void onResume() {
        super.onResume();
        Message msg = mHandler.obtainMessage(EVENT_START, this);
        mHandler.sendMessageDelayed(msg, 5000);
    }
}

package org.android.textbook.lesson4.keyeventsample;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;

public class KeyEventSampleActivity extends Activity {
    private static final String TAG = "KeyEventSampleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.e(TAG, "dispatchKeyEvent : ");
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e(TAG, "onKeyDown : ");
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            event.startTracking();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.isTracking()
                && !event.isCanceled()) {
            // onKeyLongPressでtrueを返すと、isCanceled()はtrueとなる
            // onKeyLongPressで処理されていない場合、このルートを通る
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        // バックキーが押されると呼ばれる
        super.onBackPressed();
    }
}

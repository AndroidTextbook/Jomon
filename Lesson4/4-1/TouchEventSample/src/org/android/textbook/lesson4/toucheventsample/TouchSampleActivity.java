package org.android.textbook.lesson4.toucheventsample;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;

public class TouchSampleActivity extends Activity {

    private static final String TAG = "TouchSampleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, "dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent");
        switch (event.getActionMasked()) {
        case MotionEvent.ACTION_DOWN:
            // タッチダウン
            break;
        case MotionEvent.ACTION_UP:
            // タッチアップ
            break;
        case MotionEvent.ACTION_MOVE:
            // タッチムーブ
            break;
        case MotionEvent.ACTION_CANCEL:
            // タッチが破棄されました
            break;
        case MotionEvent.ACTION_OUTSIDE:
            // 領域外でタッチ操作が行われた
            break;
        case MotionEvent.ACTION_POINTER_DOWN:
            // ２点目以降のタッチダウン
            // 何番目のポインターか取得
            int downIndex = event.getActionIndex();
            // ポインターのIDを取得
            int downId = event.getPointerId(event.getActionIndex());
            Log.e(TAG, "ACTION_POINTER_DOWN downIndex = "  + downIndex + ",downId = " + downId);
            break;
        case MotionEvent.ACTION_POINTER_UP:
            // ２点目以降のタッチアップ
            int upIndex = event.getActionIndex();
            // ポインターのIDを取得
            int upId = event.getPointerId(event.getActionIndex());
            Log.e(TAG, "ACTION_POINTER_UP upIndex = "  + upIndex + ",downId = " + upId);
            break;
        default:
            break;

        }
        return super.onTouchEvent(event);
    }
}

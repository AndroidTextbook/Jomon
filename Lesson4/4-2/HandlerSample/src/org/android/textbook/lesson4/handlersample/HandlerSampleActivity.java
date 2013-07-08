package org.android.textbook.lesson4.handlersample;

import java.lang.ref.WeakReference;

import org.android.textbook.lesson4.handlersample.WorkerThread.WorkerThreadListener;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.widget.TextView;

public class HandlerSampleActivity extends Activity {
    private CountHandler mHandler;
    private WorkerThread mWorkerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_sample);

        TextView textView = (TextView) findViewById(R.id.textView);
        // ハンドラーの生成
        mHandler = new CountHandler(textView);
        // スレッドのインスタンス生成

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWorkerThread == null) {
            mWorkerThread = new WorkerThread(mWorkerThreadListener);
            mWorkerThread.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWorkerThread != null) {
            mWorkerThread.stopThread();
            mWorkerThread = null;
        }
    }

    private static class CountHandler extends Handler {
        private int counter;
        private WeakReference<TextView> mTextWeakRef;

        public CountHandler(TextView textView) {
            mTextWeakRef = new WeakReference<TextView>(textView);

        }

        public static final int MSG_COUNT_UP = 0;

        @Override
        public void handleMessage(Message msg) {
            TextView text = mTextWeakRef.get();
            if (text == null) {
                return;
            }
            switch (msg.what) {
            case MSG_COUNT_UP:
                counter++;
                text.setText(String.valueOf(counter));
                break;

            default:
                break;

            }
        }

    };

    private WorkerThreadListener mWorkerThreadListener = new WorkerThreadListener() {

        @Override
        public void onEventFromWorkerThread() {
            // このメソッドはWorkerThreadクラスのスレッドから呼ばれます。
            // UIスレッドのハンドラーにメッセージを送信します。
            Message msgUp =
                    mHandler.obtainMessage(CountHandler.MSG_COUNT_UP);
            mHandler.sendMessage(msgUp);
        }

    };

}

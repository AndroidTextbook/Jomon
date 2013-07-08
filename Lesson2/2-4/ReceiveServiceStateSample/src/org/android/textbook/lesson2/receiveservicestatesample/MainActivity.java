
package org.android.textbook.lesson2.receiveservicestatesample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
    private static final String LOG_TAG = "ReceiveServiceState";

    BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            Log.i("koba", "" + action);
            if (SampleService.ACTION_INIT.equals(action)) {
                // 表示内容の切り換え処理
                Log.i(LOG_TAG, "GET ACTION INIT");
            } else if (SampleService.ACTION_RUNNING.equals(action)) {
                // 表示内容の切り換え処理
                Log.i(LOG_TAG, "GET ACTION RUNNING");
            } else if (SampleService.ACTION_DOWNLOADING.equals(action)) {
                // 表示内容の切り換え処理
                Log.i(LOG_TAG, "GET ACTION DOWNLOADING");
            } else if (SampleService.ACTION_DONE.equals(action)) {
                // 表示内容の切り換え処理
                Log.i(LOG_TAG, "GET ACTION DONE");
            } else if (SampleService.ACTION_DESTROY.equals(action)) {
                // 表示内容の切り換え処理
                Log.i(LOG_TAG, "GET ACTION DESTROY");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // インテントフィルタの設定
        IntentFilter filter = new IntentFilter();
        filter.addAction(SampleService.ACTION_INIT);
        filter.addAction(SampleService.ACTION_RUNNING);
        filter.addAction(SampleService.ACTION_DOWNLOADING);
        filter.addAction(SampleService.ACTION_DONE);
        filter.addAction(SampleService.ACTION_DESTROY);
        filter.addCategory(Intent.CATEGORY_DEFAULT);

        // ブロードキャストレシーバーの登録
        registerReceiver(mReceiver, filter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // サービスの起動
        Intent service = new Intent();
        service.setClassName("org.android.textbook.lesson2.receiveservicestatesample",
                "org.android.textbook.lesson2.receiveservicestatesample.SampleService");
        startService(service);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // ブロードキャストレシーバーの解除
        unregisterReceiver(mReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}

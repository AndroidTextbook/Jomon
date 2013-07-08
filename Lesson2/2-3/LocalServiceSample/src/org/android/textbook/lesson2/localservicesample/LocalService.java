
package org.android.textbook.lesson2.localservicesample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class LocalService extends Service {
    private static final String TAG = "LocalService";

    // アクティビティがサービスの実態にアクセスするためのクラス
    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }

    // サービスがアクティビティにバインドされたときの処理
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "Service#onBind()");
        return new LocalBinder();
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "Service#onCreate()");
        super.onCreate();
    }

    @Override
    public void onDestroy() {

        Log.i(TAG, "Service#onDestroy()");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service#onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "Service#onUnbind()");
        return super.onUnbind(intent);
    }

    // トーストを出力するメソッド
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}

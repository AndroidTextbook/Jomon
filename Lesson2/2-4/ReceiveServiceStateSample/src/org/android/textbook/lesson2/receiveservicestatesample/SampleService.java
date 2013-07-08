
package org.android.textbook.lesson2.receiveservicestatesample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SampleService extends Service {
    // 独自ACTIONの定義
    public static final String ACTION_INIT = "org.android.textbook.lesson2.receiveservicestatesample.action.INIT";
    public static final String ACTION_RUNNING = "org.android.textbook.lesson2.receiveservicestatesample.action.RUNNING";
    public static final String ACTION_DOWNLOADING = "org.android.textbook.lesson2.receiveservicestatesample.action.DOWNLOADING";
    public static final String ACTION_DONE = "org.android.textbook.lesson2.receiveservicestatesample.action.DONE";
    public static final String ACTION_DESTROY = "org.android.textbook.lesson2.receiveservicestatesample.action.DESTORY";

    // 状態管理用
    public enum MyServiceState {
        INIT,
        RUNNING,
        DOWNLOADING,
        DONE,
        DESTORY,
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        postBroadcast(MyServiceState.INIT);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        postBroadcast(MyServiceState.DESTORY);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("koba", "startCommand");
        mThread.start();

        return Service.START_STICKY;
    }

    /**
     * 各状態をブロードキャスト送信する
     * 
     * @param state 送信する状態
     */
    private void postBroadcast(MyServiceState state) {
        Intent broadcast = new Intent();
        switch (state) {
            case INIT:
                broadcast.setAction(ACTION_INIT);
                break;
            case RUNNING:
                broadcast.setAction(ACTION_RUNNING);
                break;
            case DOWNLOADING:
                broadcast.setAction(ACTION_DOWNLOADING);
                break;
            case DONE:
                broadcast.setAction(ACTION_DONE);
                break;
            case DESTORY:
                broadcast.setAction(ACTION_DESTROY);
                break;
            default:
                broadcast = null;
                break;
        }

        if (broadcast != null) {
            sendBroadcast(broadcast);
        }
    }

    private Thread mThread = new Thread(new Runnable() {

        @Override
        public void run() {
            postBroadcast(MyServiceState.RUNNING);

            /* ダウンロード処理は省略 */
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            postBroadcast(MyServiceState.DOWNLOADING);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            postBroadcast(MyServiceState.DONE);

        }
    });

}

package org.android.textbook4.asynctasksample;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class ImageLoadAsyncTask extends
        AsyncTask<String, Integer, List<Bitmap>> {
    private Context mContext;
    private ImageLoadAsyncTaskListener mListener;

    /**
     * UIスレッドに通知するためのインターフェイス
     * 
     * @note
     */
    public interface ImageLoadAsyncTaskListener {
        /** タスク実行前 */
        public void onStartBackgroundTask();

        /** タスクの進捗状況を通知します */
        public void onProgressUpdate(int progress);

        /** タスク結果を渡します */
        public void onEndBackgroundTask(List<Bitmap> result);

        /** タスクをキャンセルした */
        public void onCancelledTask();
    }

    /**
     * コンストラクタ
     * 
     * @param コンテキスト
     * @param UIスレッドに通知を行うリスナー
     */
    public ImageLoadAsyncTask(Context context,
            ImageLoadAsyncTaskListener listener) {
        mContext = context;
        mListener = listener;
    }

    /**
     * バックグラウンド処理
     * 
     * @param ファイルパス
     * @note バックグラウンドのスレッドで実行されます
     */
    @Override
    protected List<Bitmap> doInBackground(String... files) {
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        // assetsからファイルをreadする
        for (int i = 0; i < files.length; i++) {
            if (isCancelled()) {
                // タスクのキャンセル
                break;
            }

            try {
                InputStream inputStream = mContext.getAssets().open(files[i]);
                Bitmap bmp = BitmapFactory.decodeStream(inputStream);
                bitmaps.add(bmp);
                // UIスレッドに進捗状況を通知
                publishProgress(i + 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmaps;
    }

    /**
     * タスク開始前処理
     * 
     * @note　UIスレッド上で実行します
     */
    @Override
    protected void onPreExecute() {
        mListener.onStartBackgroundTask();
    }

    /**
     * 進捗更新
     * 
     * @note　UIスレッド上で実行します
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        mListener.onProgressUpdate(values[0]);
    }

    /**
     * タスク終了処理
     * 
     * @note　UIスレッド上で実行します
     */
    @Override
    protected void onPostExecute(List<Bitmap> result) {
        // タスクが終了したことをUIスレッドに通知
        mListener.onEndBackgroundTask(result);
    }

    /**
     * キャンセル処理
     * 
     */
    @Override
    protected void onCancelled() {
        // UIスレッドに通知
        mListener.onCancelledTask();
    }
}

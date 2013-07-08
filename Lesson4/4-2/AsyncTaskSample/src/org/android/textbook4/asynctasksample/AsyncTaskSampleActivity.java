package org.android.textbook4.asynctasksample;

import java.util.List;

import org.android.textbook4.asynctasksample.ImageLoadAsyncTask.ImageLoadAsyncTaskListener;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;

public class AsyncTaskSampleActivity extends Activity implements
        OnClickListener {

    private ImageLoadAsyncTask mAsyncTask;
    private ProgressDialog mProgressDialog;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_sample);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        mImageView = (ImageView) findViewById(R.id.imageView);

        mProgressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        // タスクを実行する
        mAsyncTask = new ImageLoadAsyncTask(this, mImageLoadAsyncTaskListener);
        mAsyncTask.execute("ic_launcher-web.png", "ic_launcher-web.png",
                "ic_launcher-web.png");
    }

    @Override
    public void onPause() {
        super.onPause();

        if (!mAsyncTask.isCancelled()) {
            // タスクのキャンセル
            mAsyncTask.cancel(true);
        }
    }

    private ImageLoadAsyncTaskListener mImageLoadAsyncTaskListener = new ImageLoadAsyncTaskListener() {

        /**
         * プログレスダイアログのキャンセルリスナー
         * 
         * @note バックキー押下でダイアログを消すと呼ばれます
         * */
        private DialogInterface.OnCancelListener mOnCancelListener = new OnCancelListener() {

            public void onCancel(DialogInterface dialogInterface) {
                // タスクのキャンセル
                mAsyncTask.cancel(true);
            }

        };

        public void onStartBackgroundTask() {
            // タスクの開始
            // バックグランド実行中であることを、ユーザーに知らせるため、
            // プログレスダイアログを表示
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setMax(3);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            // キャンセルリスナーの登録
            mProgressDialog.setOnCancelListener(mOnCancelListener);

            mProgressDialog.show();
        }

        public void onProgressUpdate(int progress) {
            // 進捗状況の更新
            mProgressDialog.setProgress(progress);

        }

        public void onEndBackgroundTask(List<Bitmap> result) {
            // バックグランド処理の結果を受け取る
            mProgressDialog.dismiss();
            mImageView.setImageBitmap(result.get(0));
        }

        public void onCancelledTask() {
            // キャンセル処理
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }

    };

}

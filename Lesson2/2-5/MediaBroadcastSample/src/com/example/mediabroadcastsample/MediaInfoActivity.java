
package com.example.mediabroadcastsample;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MediaInfoActivity extends Activity implements OnClickListener {

    private static final String LOGTAG = "MediaBroadcastSample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt1, bt2,bt3;
        bt1 = (Button) findViewById(R.id.button1);
        bt1.setOnClickListener(this);
        bt2 = (Button) findViewById(R.id.button2);
        bt2.setOnClickListener(this);
        bt3 = (Button) findViewById(R.id.button3);
        bt3.setOnClickListener(this);
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1: {
                // ブロードキャストアクションを使ったコンテントプロバイダーへの登録
                String filePath = Environment.getExternalStorageDirectory() + "/sample.jpg";

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                // DataフィールドにファイルパスをURI形式で指定
                intent.setData(Uri.fromFile(new File(filePath)));

                sendBroadcast(intent);
                break;
            }
            case R.id.button2: {
                // scanFile APIを使ったコンテントプロバイダーへの登録
                String[] filePaths = {
                        Environment.getExternalStorageDirectory() + "/sample2.jpg"
                };
                String[] mimeTypes = {
                        "media/jpeg"
                };

                MediaScannerConnection.scanFile(getApplicationContext(), filePaths, mimeTypes,
                        new OnScanCompletedListener() {

                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                // メディア登録が終わったことを通知する
                                Log.v(LOGTAG, "uri : " + uri.getPath() + " is completed");
                            }
                        });

                break;
            }
            case R.id.button3: {
                // コンテントプロバイダーの情報へのアクセス
                String[] projection = new String[] {
                        MediaStore.MediaColumns.TITLE,
                        MediaStore.MediaColumns.DATA
                };

                // 対象のコンテントプロバイダを設定し、カーソルを取得する
                Cursor cursor = getContentResolver().query(
                        MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
                        projection,
                        null, null, null);

                if (null == cursor) {
                    // カーソルの取得に失敗した
                } else if (cursor.getCount() < 1) {
                    // 取得したレコードが0個
                } else {
                    // 取得したレコードに対する処理

                    // カーソル位置を先頭に移動
                    if (!cursor.moveToFirst()) {
                        cursor.close();
                        return;
                    }

                    // 取得したいカラムのインデックスを保持
                    int titleIndex =
                            cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);

                    int pathIndex =
                            cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);

                    // カーソルを移動させながら、タイトルカラムとデータカラムの情報をログ出力する
                    do {
                        Log.d(LOGTAG, "title : " + cursor.getString(titleIndex));
                        Log.d(LOGTAG, "path : " + cursor.getString(pathIndex));
                    } while (cursor.moveToNext());

                    cursor.close();
                }

            }
        }
    }
}


package org.android.textbook.lesson4.camerasample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

public class CameraSampleActivity extends Activity implements OnClickListener {
    private Camera mCamera;
    private CameraPreview mPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // フルスクリーン表示にします
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_camera_sample);

        mPreview = new CameraPreview(this);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);

        // 撮影ボタンのクリックリスナーを登録
        Button button = (Button) findViewById(R.id.button_capture);
        button.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCamera = getCameraInstance();
        mPreview.setCamera(mCamera);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // カメラデバイスの使用停止
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
            mPreview.setCamera(null);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_capture) {
            mCamera.takePicture(mShutter, mRawPicture, mJpegPicture);
        }
    }

    /**
     * Cameraクラスのオブジェクトを取得します
     * 
     * @return Camera、もし、デバイスが未対応の場合、nullを返します
     */
    public static Camera getCameraInstance() {
        Camera camera = null;
        try {
            // Cameraクラスのオブジェクトを取得する
            camera = Camera.open();
        } catch (Exception e) {
            // オブジェクトが生成できない場合、Exceptionが発生する
        }
        return camera;
    }

    private ShutterCallback mShutter = new ShutterCallback() {
        @Override
        public void onShutter() {
            // シャッター音を鳴動させます
        }
    };

    private PictureCallback mRawPicture = new PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // 撮影後、圧縮前のRawデータを受け取ることができます。
            // プレビュー再開
            mCamera.startPreview();
        }
    };

    private PictureCallback mJpegPicture = new PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // 圧縮後のJpegデータを受け取ることができます。
            File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File pictureFile = new File(path, "camera_sample.jpg");
            android.util.Log.e("", "" + pictureFile.exists());

            try {
                FileOutputStream fos = new
                        FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();

                // コンテンツの登録
                ContentValues values = new ContentValues();
                ContentResolver contentResolver = getContentResolver();
                values.put(MediaStore.MediaColumns.DATA, pictureFile.getPath());
                values.put(MediaStore.MediaColumns.TITLE, "camera_sample.jpg");
                values.put(MediaStore.MediaColumns.SIZE, data.length);
                values.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
                contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            }
            catch (FileNotFoundException e) {
                // 失敗
            } catch (IOException e) {
                // 失敗
            }
            // プレビュー再開
            mCamera.startPreview();
        }
    };

}

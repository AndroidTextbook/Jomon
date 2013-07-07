
package org.android.textbook.lesson4.camerasample;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera mCamera;
    List<Size> mPictureSizeList;

    public CameraPreview(Context context) {
        super(context);

        mHolder = getHolder();
        mHolder.addCallback(this);
        // 非推奨の設定ですが、3.0以前の場合は必要
        // mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    public void setCamera(Camera camera) {
        mCamera = camera;
        if (camera != null) {
            // プレビューに設定可能なサイズが取得できます
            mCamera.getParameters().getSupportedPreviewSizes();
            // 撮影画像に設定可能なサイズが取得できます
            mCamera.getParameters().getPictureSize();
        }
    }

    /**
     * Surface生成後に呼ばれます
     */
    public void surfaceCreated(SurfaceHolder holder) {
        // Cameraのプレビューを表示するためにCameraクラスへセットします
        if (mCamera != null) {
            try {
                mCamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                // Cameraのプレビューに失敗
            }
        }
    }

    /**
     * Surfaceが破棄されたときに呼ばれます
     */
    public void surfaceDestroyed(SurfaceHolder holder) {
        // empty. Take care of releasing the Camera preview in your activity.
    }

    /**
     * Surfaceのサイズが変更されてときに呼ばれます
     */
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // サイズが決定した場合や、
        // 画面回転でサイズが変更になった場合に呼ばれます
        if (mHolder.getSurface() == null) {
            return;
        }

        if (mCamera != null) {
            try {
                // プレビューを止めます
                mCamera.stopPreview();

                // ここプレビューのリサイズに必要な処理を書く
                Camera.Parameters parameters = mCamera.getParameters();
                Size size = getOptimalPreviewSize(parameters.getSupportedPreviewSizes(),
                        w,
                        h);
                parameters.setPreviewSize(size.width, size.height);
                mCamera.setParameters(parameters);

                // プレビューを再開します
                mCamera.setPreviewDisplay(mHolder);
                mCamera.startPreview();
            } catch (Exception e) {
                // 失敗
            }
        }
    }

    /**
     * SurfaceViewのサイズにあったプレビューサイズを取得
     */
    private Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.1;
        double targetRatio = (double) w / h;
        if (sizes == null) {
            return null;
        }

        Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        // アスペクト比がほぼ一緒で、高さが近いものを探す
        for (Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
                continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        // アスペクト比が合わない場合、高さのみで近いものを探す
        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }
        return optimalSize;
    }

}

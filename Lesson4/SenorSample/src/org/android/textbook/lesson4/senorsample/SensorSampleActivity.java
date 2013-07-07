package org.android.textbook.lesson4.senorsample;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

public class SensorSampleActivity extends Activity {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mMagneticField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        // SensorManagerのインスタンス取得
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // 各センサーのインスタンス取得
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagneticField = mSensorManager
                .getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // リスナーの登録
        mSensorManager.registerListener(mSensorEventListener, mAccelerometer,
                SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(mSensorEventListener, mMagneticField,
                SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onPause() {
        super.onPause();
        // リスナーの解除
        mSensorManager.unregisterListener(mSensorEventListener);
    }

    /**
     * センサーマネージャにセットするリスナー
     */
    private SensorEventListener mSensorEventListener = new SensorEventListener() {
        private float[] mAccelerometerValues = null;
        private float[] mMagneticValues = null;
        private float[] orientationValues = new float[3];
        private float[] RotaionMatrixR = new float[16];// 4x4 matrix
        private float[] remapRotaionMatrixR = new float[16];// 4x4 matrix
        private float[] RotaionMatrixI = new float[16];// 4x4 matrix

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        /**
         * センサー値の変更通知
         */
        @Override
        public void onSensorChanged(SensorEvent event) {
            // センサー値の通知、値が変更される度にコールされます
            switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                // 加速度センサーの通知
                mAccelerometerValues = event.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                // 地磁気センサーの通知
                mMagneticValues = event.values.clone();
                break;
            }

            // 2つのセンサーが取得済みの場合
            if (mAccelerometerValues != null && mMagneticValues != null) {
                // 回転行列の取得
                SensorManager.getRotationMatrix(RotaionMatrixR, RotaionMatrixI,
                        mAccelerometerValues, mMagneticValues);
                // 画面の向きを考慮して、行列に反映
                SensorManager.remapCoordinateSystem(RotaionMatrixR,
                        SensorManager.AXIS_X, SensorManager.AXIS_Y,
                        remapRotaionMatrixR);
                // 傾きの取得
                SensorManager.getOrientation(remapRotaionMatrixR,
                        orientationValues);
                displayOrientation(orientationValues);
            }

        }

    };

    /**
     * センサーの値をUI表示
     */
    private void displayOrientation(float[] val) {
        ((TextView) this.findViewById(R.id.azimuth)).setText(""
                + Math.toDegrees(val[0]));
        ((TextView) this.findViewById(R.id.pitch)).setText(""
                + Math.toDegrees(val[1]));
        ((TextView) this.findViewById(R.id.roll)).setText(""
                + Math.toDegrees(val[2]));
    }

}

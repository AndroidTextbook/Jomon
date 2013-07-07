package org.android.textbook.lesson4.locationsample;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

public class LocationSampleActivity extends Activity {

    private static final String TAG = "LocationSampleActivity";
    private LocationManager mLocationManager;
    private LocationProvider mProvider;
    private boolean mLocationEnabled;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // LocationManagerのオブジェクト取得
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // GPS_PROVIDERの取得
        mProvider = mLocationManager.getProvider(LocationManager.GPS_PROVIDER);
        Log.e(TAG,mProvider.toString());
        // NETWORK_PROVIDERの場合
        // mProvider =
        // mLocationManager.getProvider(LocationManager.NETWORK_PROVIDER);

    }

    @Override
    protected void onStart() {
        super.onStart();

        mLocationEnabled = mLocationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        // NETWORK_PROVIDERの場合
        // mLocationEnabled =
        // mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!mLocationEnabled) {

            // 位置情報の設定がOFFの場合、ユーザーに設定を変更してもらうように促す
            EnableLocationDialogFragment dialog = new EnableLocationDialogFragment();
            dialog.show(this.getFragmentManager(), "enable_locaition_dialog");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mLocationEnabled) {
            // リスナーを登録します
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 100, // 時間指定：10秒間隔
                    10, // 距離指定：10m間隔
                    mListener);

            // NETWORK_PROVIDERの場合
            // mLocationManager.requestLocationUpdates(LocationManager
            // .NETWORK_PROVIDER, 100, // 時間指定：10秒間隔
            // 10, // 距離指定：10m間隔
            // mListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mLocationEnabled) {
            // 通知が不要になったタイミングで、必ずリスナーを解除する
            mLocationManager.removeUpdates(mListener);
        }
    }

    /**
     * ロケーションマネージャにセットするリスナー
     */
    private final LocationListener mListener = new LocationListener() {

        public void onLocationChanged(Location location) {
            // 位置情報が変更された
            setLocationData(location);
        }

        public void onProviderDisabled(String provider) {
            // ユーザーによってProviderが無効になった
        }

        public void onProviderEnabled(String provider) {
            // ユーザーによってProviderが有効になった
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Providerの状態が変わった
        }

    };

    /**
     * センサーマネージャから通知される値をUI表示
     */
    private void setLocationData(Location location) {
        ((TextView) findViewById(R.id.provider_name)).setText(location
                .getProvider());
        ((TextView) findViewById(R.id.latitude)).setText(""
                + location.getLatitude());
        ((TextView) findViewById(R.id.longitude)).setText(""
                + location.getLongitude());

        if (location.hasAltitude()) {
            ((TextView) findViewById(R.id.altitude)).setText(""
                    + location.getAltitude());
        } else {
            ((TextView) findViewById(R.id.altitude)).setText("disable");
        }

        if (location.hasAccuracy()) {
            ((TextView) findViewById(R.id.accuracy)).setText(""
                    + location.getAccuracy());
        } else {
            ((TextView) findViewById(R.id.accuracy)).setText("disable");
        }
        ((TextView) findViewById(R.id.time)).setText("" + location.getTime());

        if (location.hasBearing()) {
            ((TextView) findViewById(R.id.bearing)).setText(""
                    + location.getBearing());

        } else {
            ((TextView) findViewById(R.id.bearing)).setText("disable");
        }
        if (location.hasSpeed()) {
            ((TextView) findViewById(R.id.speed)).setText(""
                    + location.getSpeed());

        } else {
            ((TextView) findViewById(R.id.speed)).setText("disable");
        }

    }

}


package org.android.textbook.lesson2.configurationchangesample;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class ConfigurationChangeActivity extends Activity {
    private static final String TAG = "ConfigurationChangedSample";
    private Configuration oldConfig;

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        Log.i("koba", "onLowMemory");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i(TAG, "LANDSCAPEになりました");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.i(TAG, "PORTRAITになりました");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oldConfig = getResources().getConfiguration();
        Log.i("koba", "onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        Log.i("koba", "onCreateOptionMenu");
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        int change = getChangingConfigurations();
        if (0 != (change & (ActivityInfo.CONFIG_ORIENTATION | ActivityInfo.CONFIG_SCREEN_SIZE))) {
            Log.i(TAG, "デバイスの向きが変更されました");
        }
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        Log.i("koba", "onRestart");
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        Log.i("koba", "onResume");
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Log.i("koba", "onStart");
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        Log.i("koba", "onStop");
    }

}

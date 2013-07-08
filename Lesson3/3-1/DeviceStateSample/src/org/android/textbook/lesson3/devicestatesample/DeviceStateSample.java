package org.android.textbook.lesson3.devicestatesample;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.WindowManager;

public class DeviceStateSample extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_device_state_sample);

        Resources resources = getResources();
        Configuration config = resources.getConfiguration();

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);

        // ��ʃT�C�Y�擾
        getScreenSize(config);

        // ��ʖ��x�擾
        getDensity(displayMetrics);

        // �𑜓x�擾
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        // ��ʂ̌����擾
        getOrientation(config);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.device_state_sample, menu);
        return true;
    }

    private int getScreenSize(Configuration config) {
        int ret = Configuration.SCREENLAYOUT_SIZE_UNDEFINED;
        if ((config.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_LARGE) {
            // ���̃f�o�C�X�̉�ʃT�C�Y�́A�uSCREENLAYOUT_SIZE_LARGE�v
            ret = Configuration.SCREENLAYOUT_SIZE_LARGE;
        }
        return ret;
    }

    private int getDensity(DisplayMetrics displayMetrics) {
        int ret = DisplayMetrics.DENSITY_DEFAULT;
        switch (displayMetrics.densityDpi) {
        case DisplayMetrics.DENSITY_XHIGH:
            // ���̃f�o�C�X�̉�ʃT�C�Y�́A�uDENSITY_XHIGH�v
            ret = DisplayMetrics.DENSITY_XHIGH;
            break;
        }
        return ret;
    }

    private int getOrientation(Configuration config) {
        int ret = Configuration.ORIENTATION_UNDEFINED;
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // ���̃f�o�C�X�̉�ʂ̌����́A�uORIENTATION_PORTRAIT�v
            ret = Configuration.ORIENTATION_PORTRAIT;
        }
        return ret;
    }
}

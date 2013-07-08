
package com.android_textbook.learnjunit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.android_textbook.learnjunit.R;

public class LifeCycleActivity extends Activity {
    private TextView mLifecycleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        mLifecycleText = (TextView)findViewById(R.id.lifecycleText);
    }

    protected void onStart() {
        super.onStart();
        mLifecycleText.setText("onStart");
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        mLifecycleText.setText("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLifecycleText.setText("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLifecycleText.setText("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLifecycleText.setText("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLifecycleText.setText("onDestroy");
    }
}

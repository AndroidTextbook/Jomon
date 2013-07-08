
package org.android.textbook.lesson5.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ApplicationShareSampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_share);

        ApplicationShare app = ((ApplicationShare) getApplication());
        app.setIntValue(65);
        app.setStringValue("Application Share");

        Button button = (Button) findViewById(R.id.button_launch_activity);
        button.setOnClickListener(mOnClickListener);

    }

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_launch_activity:
                    Intent intent = new Intent(ApplicationShareSampleActivity.this,
                            ShareActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }

    };

}

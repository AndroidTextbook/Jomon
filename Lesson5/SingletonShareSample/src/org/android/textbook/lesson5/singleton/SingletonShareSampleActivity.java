
package org.android.textbook.lesson5.singleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SingletonShareSampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton_share_sample);

        ShareDataManager dataManager = ShareDataManager.getInstance();
        dataManager.setIntValue(55);
        dataManager.setStringValue("Singleton Share");

        Button button = (Button) findViewById(R.id.button_launch_activity);
        button.setOnClickListener(mOnClickListener);

    }

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_launch_activity:
                    Intent intent = new Intent(SingletonShareSampleActivity.this,
                            ShareActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }

    };
}


package com.android_textbook.learnjunit;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.android_textbook.learnjunit.R;

public class RotatableActivity extends Activity {
    private TextView mBottomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotatable);
        mBottomView = (TextView)findViewById(R.id.bottomView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 表示の更新処理を呼び出す
        updateBottomView(getResources().getConfiguration());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateBottomView(newConfig);
    }

    /** コンフィギュレーションにあわせて表示を更新します。 */
    private void updateBottomView(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mBottomView.setVisibility(View.GONE);
        } else {
            mBottomView.setVisibility(View.VISIBLE);
        }
    }
}

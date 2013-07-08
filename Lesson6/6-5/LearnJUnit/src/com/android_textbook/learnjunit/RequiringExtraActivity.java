
package com.android_textbook.learnjunit;

import android.app.Activity;
import android.os.Bundle;
import com.android_textbook.learnjunit.R;

public class RequiringExtraActivity extends Activity {
    public static final String EXTRA_THE_STRING = "theString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requiring_extra);
        // 追加の情報がなければ即座に終了する
        if (getIntent().getStringExtra(EXTRA_THE_STRING) == null) {
            finish();
        }
    }
}

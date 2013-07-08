
package com.android_textbook.learnjunit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.android_textbook.learnjunit.R;

public class BlankActivity extends Activity {
    private String mStr = "Something";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

        Log.d("BlankActivity", mStr);
    }
}

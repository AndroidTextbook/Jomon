
package com.android_textbook.learnjunit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.android_textbook.learnjunit.R;

public class SimpleButtonActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_button);

        findViewById(R.id.pushMeButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.pushMeButton) {
            findViewById(R.id.pushMeButton).setVisibility(View.INVISIBLE);
        }
    }
}

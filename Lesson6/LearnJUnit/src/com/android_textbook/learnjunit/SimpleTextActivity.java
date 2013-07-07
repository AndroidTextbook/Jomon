
package com.android_textbook.learnjunit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.android_textbook.learnjunit.R;

public class SimpleTextActivity extends Activity {
    public static final String EXTRA_TEXT = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_text);

        TextView textView = (TextView)findViewById(R.id.textView1);
        String text = getIntent().getStringExtra(EXTRA_TEXT);
        if (text != null) {
            textView.setText(text);
        } else {
            textView.setText("no text");
        }
    }
}

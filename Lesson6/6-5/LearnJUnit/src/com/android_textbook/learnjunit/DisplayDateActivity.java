
package com.android_textbook.learnjunit;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.android_textbook.learnjunit.R;

public class DisplayDateActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_date);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String str = format.format(date);
        TextView dateTextView = (TextView)findViewById(R.id.dateTextView);
        dateTextView.setText(str);
    }
}

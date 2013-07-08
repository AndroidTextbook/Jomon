
package com.android_textbook.learnjunit;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import com.android_textbook.learnjunit.R;

public class TouchActivity extends Activity {
    private TextView mTouchPositionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);

        mTouchPositionText = (TextView)findViewById(R.id.touchPositionText);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            String text = (int)event.getX() + "," + (int)event.getY();
            mTouchPositionText.setText(text);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            String text = mTouchPositionText.getText() + " - " + (int)event.getX() + ","
                    + (int)event.getY();
            mTouchPositionText.setText(text);
        }
        return super.onTouchEvent(event);
    }
}

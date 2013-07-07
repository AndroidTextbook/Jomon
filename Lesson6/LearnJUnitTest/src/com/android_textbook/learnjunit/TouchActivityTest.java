
package com.android_textbook.learnjunit;

import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;
import android.view.MotionEvent;
import android.widget.TextView;

import com.android_textbook.learnjunit.R;
import com.android_textbook.learnjunit.TouchActivity;

public class TouchActivityTest extends ActivityInstrumentationTestCase2<TouchActivity> {
    public TouchActivityTest() {
        super(TouchActivity.class);
    }

    public void testTouch() {
        TouchActivity activity = getActivity();
        TextView touchPositionText = (TextView)activity.findViewById(R.id.touchPositionText);
        getInstrumentation().waitForIdleSync();

        { // 座標400, 350にタッチする
            long time = SystemClock.uptimeMillis();
            MotionEvent event = MotionEvent
                    .obtain(time, time, MotionEvent.ACTION_DOWN, 400, 350, 0);
            getInstrumentation().sendPointerSync(event);
        }
        { // 座標450, 320で手を離す
            long time = SystemClock.uptimeMillis();
            MotionEvent event = MotionEvent.obtain(time, time, MotionEvent.ACTION_UP, 450, 320, 0);
            getInstrumentation().sendPointerSync(event);
        }
        //
        assertEquals("400,350 - 450,320", touchPositionText.getText().toString());
    }
}

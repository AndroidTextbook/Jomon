
package com.android_textbook.learnjunit;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.View;
import android.widget.Button;

import com.android_textbook.learnjunit.R;
import com.android_textbook.learnjunit.SimpleButtonActivity;

public class SimpleButtonActivityTest extends
        ActivityInstrumentationTestCase2<SimpleButtonActivity> {
    public SimpleButtonActivityTest() {
        super(SimpleButtonActivity.class);
    }

    public void testClickButton() throws Throwable {
        final SimpleButtonActivity activity = getActivity();

        // ボタンを押す
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                Button button = (Button)activity.findViewById(R.id.pushMeButton);
                button.performClick();
            }
        });

        // 結果としてボタンが非表示なっていることを確認する
        Button button = (Button)activity.findViewById(R.id.pushMeButton);
        assertEquals(View.INVISIBLE, button.getVisibility());
    }

    @UiThreadTest
    public void testClickButton2() {
        SimpleButtonActivity activity = getActivity();

        Button button = (Button)activity.findViewById(R.id.pushMeButton);
        button.performClick();

        assertEquals(View.INVISIBLE, button.getVisibility());
    }
}

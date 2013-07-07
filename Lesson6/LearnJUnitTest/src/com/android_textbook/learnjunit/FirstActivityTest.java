
package com.android_textbook.learnjunit;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.android_textbook.learnjunit.FirstActivity;
import com.android_textbook.learnjunit.R;
import com.android_textbook.learnjunit.SimpleTextActivity;

public class FirstActivityTest extends ActivityInstrumentationTestCase2<FirstActivity> {
    public FirstActivityTest() {
        super(FirstActivity.class);
    }

    public void testStartActivity() throws Throwable {
        final FirstActivity activity = getActivity();
        // Activityを監視するモニターを作成して登録する
        ActivityMonitor monitor = new ActivityMonitor(SimpleTextActivity.class.getName(), null,
                false);
        getInstrumentation().addMonitor(monitor);

        // ここでActivityが遷移する処理を行う
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                Button button = (Button)activity.findViewById(R.id.goToNextButton);
                button.performClick();
            }
        });

        // 次のアクティビティが検知されるまで待つ
        Activity next = getInstrumentation().waitForMonitorWithTimeout(monitor, 2000);
        // 対象アクティビティが起動したことと、それが１回であることを確認する
        assertNotNull(next);
        assertEquals(1, monitor.getHits());
        // インテントに渡されている追加情報が意図したものか確認する
        Intent nextIntent = next.getIntent();
        assertEquals("Something", nextIntent.getStringExtra(SimpleTextActivity.EXTRA_TEXT));
        // 起動したアクティビティを終了しておく
        next.finish();
    }
}


package com.android_textbook.learnjunit;

import com.android_textbook.learnjunit.LifeCycleActivity;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.UiThreadTest;

public class LifeCycleActivityTest extends ActivityUnitTestCase<LifeCycleActivity> {
    public LifeCycleActivityTest() {
        super(LifeCycleActivity.class);
    }

    @UiThreadTest
    public void testSuspend() throws Throwable {
        Intent intent = new Intent(Intent.ACTION_MAIN);

        // 起動まで行う
        LifeCycleActivity activity = startActivity(intent, null, null);
        getInstrumentation().callActivityOnStart(activity);
        getInstrumentation().callActivityOnResume(activity);

        // onPause→onResumeのフローを確認する
        getInstrumentation().callActivityOnPause(activity);
        getInstrumentation().callActivityOnResume(activity);

        // onPause→onResumeのフローを確認する
        getInstrumentation().callActivityOnPause(activity);
        getInstrumentation().callActivityOnStop(activity);
        getInstrumentation().callActivityOnRestart(activity);
        getInstrumentation().callActivityOnStart(activity);
        getInstrumentation().callActivityOnResume(activity);
    }
}

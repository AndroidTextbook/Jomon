
package com.android_textbook.learnjunit;

import com.android_textbook.learnjunit.RequiringExtraActivity;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

public class RequiringExtraActivityTest extends
        ActivityInstrumentationTestCase2<RequiringExtraActivity> {
    public RequiringExtraActivityTest() {
        super(RequiringExtraActivity.class);
    }

    /** 追加情報を与えた場合は起動することを確認する。 */
    public void testLaunch_succeed() {
        // 必要な追加情報を与える
        Intent intent = new Intent();
        intent.putExtra(RequiringExtraActivity.EXTRA_THE_STRING, "Something");
        setActivityIntent(intent);
        // getActivity()メソッド内によってActivityが起動され、その参照が取得される
        RequiringExtraActivity activity = getActivity();
        // アクティビティが作成できていることを確認する
        assertNotNull("Activity should launch successfully.", activity);
        // 終了しようとしていないことを確認する
        assertFalse("Activity should not be finishing.", activity.isFinishing());
    }

    /** 追加情報を与えた場合は起動しないことを確認する。 */
    public void testLaunch_failed() {
        // getActivity()メソッド内によってActivityが起動され、その参照が取得される
        RequiringExtraActivity activity = getActivity();
        // アクティビティが作成できていることを確認する
        assertNotNull("Activity should launch successfully.", activity);
        // 終了しようとしていることを確認する
        assertTrue("Activity should not be finishing.", activity.isFinishing());
    }
}

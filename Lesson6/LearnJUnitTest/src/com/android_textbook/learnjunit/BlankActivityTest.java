
package com.android_textbook.learnjunit;

import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;

import com.android_textbook.learnjunit.BlankActivity;
import com.android_textbook.learnjunit.util.TestUtil;

public class BlankActivityTest extends ActivityInstrumentationTestCase2<BlankActivity> {
    public BlankActivityTest() {
        super(BlankActivity.class);
    }

    public void testStartingActivity() {
        // getActivity()メソッド内によってActivityが起動され、その参照が取得される
        BlankActivity activity = getActivity();
        // もしActivityの開始に失敗した場合はここで失敗する
        assertNotNull("Activity should be launched successfully.", activity);
    }

    /** バックキーを押すとアクティビティが終了することを確認する */
    public void testBackKey() {
        // Activityを起動する
        BlankActivity activity = getActivity();

        // アクティビティが動作中であることを確認する
        assertFalse(activity.isFinishing());
        // バックキーを押す
        getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
        // アクティビティが終了中であることを確認する
        assertTrue(activity.isFinishing());
    }

    public void testPickValue() throws Exception {
        BlankActivity activity = getActivity();
        TestUtil.setValue(activity, "mStr", "Anything");
        String str = (String)TestUtil.pickValue(activity, "mStr");
        assertEquals("Anything", str);
    }
}

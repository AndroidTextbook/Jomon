
package com.android_textbook.learnjunit;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.android_textbook.learnjunit.CornerLayoutActivity;
import com.android_textbook.learnjunit.R;

public class CornerLayoutActivityTest extends
        ActivityInstrumentationTestCase2<CornerLayoutActivity> {
    public CornerLayoutActivityTest() {
        super(CornerLayoutActivity.class);
    }

    public void testOrientation() throws Throwable {
        CornerLayoutActivity activity = getActivity();
        // テスト対象となるビューを取得する
        View view1 = activity.findViewById(R.id.view1);
        View view2 = activity.findViewById(R.id.view2);
        // 交差していないことを確認する
        assertTrue(view1.getBottom() < view2.getTop());
    }
}

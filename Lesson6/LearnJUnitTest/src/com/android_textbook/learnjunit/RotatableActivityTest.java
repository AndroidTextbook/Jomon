
package com.android_textbook.learnjunit;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

import com.android_textbook.learnjunit.R;
import com.android_textbook.learnjunit.RotatableActivity;

public class RotatableActivityTest extends ActivityInstrumentationTestCase2<RotatableActivity> {
    public RotatableActivityTest() {
        super(RotatableActivity.class);
    }

    public void testRotate() throws Exception {
        final RotatableActivity activity = getActivity();
        TextView bottomView = (TextView)activity.findViewById(R.id.bottomView);

        // 初期状態をポートレートにする
        if (activity.getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        // ポートレートになっていることを確認する
        assertEquals(Configuration.ORIENTATION_PORTRAIT,
                activity.getResources().getConfiguration().orientation);
        // ビューが表示されていることを確認する
        assertEquals(View.VISIBLE, bottomView.getVisibility());

        // ランドスケープに変更する
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // ランドスケープになっていることを確認する
        assertEquals(Configuration.ORIENTATION_LANDSCAPE, activity.getResources()
                .getConfiguration().orientation);
        // ビューが表示されていないことを確認する
        assertEquals(View.GONE, bottomView.getVisibility());
    }
}

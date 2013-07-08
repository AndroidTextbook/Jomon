
package com.android_textbook.learnjunit;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.android_textbook.learnjunit.R;
import com.android_textbook.learnjunit.SimpleTextActivity;

public class SimpleTextActivityTest extends ActivityInstrumentationTestCase2<SimpleTextActivity> {
    public SimpleTextActivityTest() {
        super(SimpleTextActivity.class);
    }

    public void testDisplayedTest() {
        // 起動に必要な情報を与える
        Intent intent = new Intent();
        intent.putExtra(SimpleTextActivity.EXTRA_TEXT, "It's a great book!");
        setActivityIntent(intent);

        // Acitivityを起動する
        SimpleTextActivity activity = getActivity();

        // ActivityからTextViewを取り出し、値を確認する
        TextView textView = (TextView)activity.findViewById(R.id.textView1);
        String text = String.valueOf(textView.getText());
        assertEquals("It's a great book!", text);
    }

    public void testDisplayedTest_noText() {
        // Intentを与えずにAcitivityを起動する
        SimpleTextActivity activity = getActivity();

        // ActivityからTextViewを取り出し、値を確認する
        TextView textView = (TextView)activity.findViewById(R.id.textView1);
        String text = String.valueOf(textView.getText());
        assertEquals("no text", text);
    }
}

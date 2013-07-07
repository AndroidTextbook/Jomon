
package org.android.textbook.lesson5.singleton;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ShareActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        ShareDataManager dataManager = ShareDataManager.getInstance();
        int intValue = dataManager.getIntValue();
        String stringValue = dataManager.getStringValue();

        TextView integerText = (TextView) findViewById(R.id.text_integer_value);
        integerText.setText(String.valueOf(intValue));
        TextView stringText = (TextView) findViewById(R.id.text_string_value);
        stringText.setText(stringValue);

    }
}

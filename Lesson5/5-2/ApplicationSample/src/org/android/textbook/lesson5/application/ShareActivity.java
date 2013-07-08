
package org.android.textbook.lesson5.application;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ShareActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        
        ApplicationShare app = ((ApplicationShare) getApplication());
        int intValue = app.getIntValue();
        String stringValue = app.getStringValue();

        TextView integerText = (TextView) findViewById(R.id.text_integer_value);
        integerText.setText(String.valueOf(intValue));
        TextView stringText = (TextView) findViewById(R.id.text_string_value);
        stringText.setText(stringValue);

    }
}

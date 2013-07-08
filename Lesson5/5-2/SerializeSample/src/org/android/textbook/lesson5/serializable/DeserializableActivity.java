
package org.android.textbook.lesson5.serializable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DeserializableActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deserializable);

        SerializableData data = (SerializableData) getIntent().getSerializableExtra(
                SerializeSampleActivity.SERIALIZE_DATA);

        TextView id = (TextView) findViewById(R.id.textview_id);
        TextView text = (TextView) findViewById(R.id.textview_text);

        if (data != null) {
            id.setText(String.valueOf(data.getId()));
            text.setText(data.getText());
        } else {
            id.setText("null");
            text.setText("null");
        }
    }
}

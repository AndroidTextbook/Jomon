
package org.android.textbook.lesson5.serializable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SerializeSampleActivity extends Activity {


    public static final String SERIALIZE_DATA = "Serialize";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serialize_sample);

        Button button = (Button) findViewById(R.id.button_send_data);
        button.setOnClickListener(mOnClickListener);

    }

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_send_data:
                    // データクラスを生成しデータを追加する
                    SerializableData data = new SerializableData();
                    data.setId(64);
                    data.setText("Serializable");

                    Intent intent = new Intent(SerializeSampleActivity.this,
                            DeserializableActivity.class);

                    // Bundleにデータを追加する
                    intent.putExtra(SERIALIZE_DATA, data);

                    startActivity(intent);
                    break;
                default:
                    // ここへはこない
                    break;
            }
        }

    };
}

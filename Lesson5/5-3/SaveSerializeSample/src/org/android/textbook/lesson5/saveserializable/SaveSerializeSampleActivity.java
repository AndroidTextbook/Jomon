
package org.android.textbook.lesson5.saveserializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SaveSerializeSampleActivity extends Activity {

    private EditText mEditTextId;

    private EditText mEditTextString;

    private static final String SAVE_FILE_NAME = "SerializeSaveData.dat";

    private static final String READ_ERROR_STRING = "ERROR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_serialize_sample);

        Button save = (Button) findViewById(R.id.button_save);
        save.setOnClickListener(mOnClickListener);

        Button load = (Button) findViewById(R.id.button_load);
        load.setOnClickListener(mOnClickListener);

        mEditTextId = (EditText) findViewById(R.id.edit_input_id);
        mEditTextString = (EditText) findViewById(R.id.edit_input_string);
    }

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_save:
                    // データクラスを生成しデータを保存する
                    SerializableData saveData = new SerializableData();
                    saveData.setId(Integer.parseInt(mEditTextId.getText().toString()));
                    saveData.setText(mEditTextString.getText().toString());
                    try {
                        FileOutputStream fileOutputStream = openFileOutput(SAVE_FILE_NAME,
                                MODE_PRIVATE);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                                fileOutputStream);
                        objectOutputStream.writeObject(saveData);
                        objectOutputStream.close();

                        mEditTextId.setText("");
                        mEditTextString.setText("");
                    } catch (Exception e) {
                        mEditTextId.setText(READ_ERROR_STRING);
                        mEditTextString.setText(READ_ERROR_STRING);
                    }
                    break;
                case R.id.button_load:
                    try {
                        FileInputStream fis = openFileInput(SAVE_FILE_NAME);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        SerializableData data = (SerializableData) ois.readObject();

                        mEditTextId.setText(String.valueOf(data.getId()));
                        mEditTextString.setText(data.getText());
                        ois.close();
                    } catch (Exception e) {
                        mEditTextId.setText(READ_ERROR_STRING);
                        mEditTextString.setText(READ_ERROR_STRING);
                    }
                    break;
            }

        }

    };
}

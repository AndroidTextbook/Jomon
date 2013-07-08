
package com.example.savedinstancestatesample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class SavedInstanceStateSampleActivity extends Activity {

    private static final String SAVE_KEY = "savedInstanceState_key";

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_instance_state_sample);

        mEditText = (EditText) findViewById(R.id.edittext_input);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // SAVE_KEYをキーとしてEditTextの文字列を追加する
        outState.putString(SAVE_KEY, mEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // SAVE_KEYを使って文字列を取り出す
        mEditText.setText(savedInstanceState.getString(SAVE_KEY));
    }

}

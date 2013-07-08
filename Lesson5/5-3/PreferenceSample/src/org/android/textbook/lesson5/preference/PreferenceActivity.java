
package org.android.textbook.lesson5.preference;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PreferenceActivity extends Activity {

    private EditText mEditText;

    private TextView mTextView;

    // 保存用のキー
    private static final String SAVE_KEY = "save_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_sample);

        Button saveButton = (Button) findViewById(R.id.button_save);
        saveButton.setOnClickListener(mOnClickListener);

        Button loadButton = (Button) findViewById(R.id.button_load);
        loadButton.setOnClickListener(mOnClickListener);

        Button removeButton = (Button) findViewById(R.id.button_remove);
        removeButton.setOnClickListener(mOnClickListener);

        Button clearButton = (Button) findViewById(R.id.button_clear);
        clearButton.setOnClickListener(mOnClickListener);

        mEditText = (EditText) findViewById(R.id.edittext_save_text);

        mTextView = (TextView) findViewById(R.id.textview_saved_text);
    }

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_save:
                    saveText();
                    break;
                case R.id.button_load:
                    loadText();
                    break;
                case R.id.button_remove:
                    removeText();
                    break;
                case R.id.button_clear:
                    clearText();
                    break;
                default:
                    // ここには来ない
                    break;
            }
        }
    };

    // EditTextの内容をプリファレンスへ書き出す
    private void saveText() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);

        Editor edit = sharedPreferences.edit();
        edit.putString(SAVE_KEY, mEditText.getText().toString());
        edit.commit();
    }

    // EditTextの内容をプリファレンスから読み込む
    private void loadText() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String savedText = sharedPreferences.getString(SAVE_KEY, null);
        if (savedText != null) {
            mTextView.setText(savedText);
        } else {
            mTextView.setText("null");
        }
    }

    private void removeText() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        Editor edit = sharedPreferences.edit();
        edit.remove(SAVE_KEY);
        edit.commit();
    }

    private void clearText() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        Editor edit = sharedPreferences.edit();
        edit.clear();
        edit.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.preference_sample, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_getsharedpreference:
                Intent intent = new Intent(this, PreferenceSampleActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }

        return true;
    }

}

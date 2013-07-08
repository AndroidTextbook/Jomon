
package org.android.textbook.lesson5.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.StatFs;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalStorageSmapleActivity extends Activity {

    private EditText mEditText;

    private TextView mTextView;

    private static final String FILE_NAME = "internal.txt";

    private static final long LOW_MEMORY = 1024; // 1KB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage_smaple);

        Button save = (Button) findViewById(R.id.button_save);
        save.setOnClickListener(mOnClickListener);

        Button load = (Button) findViewById(R.id.button_load);
        load.setOnClickListener(mOnClickListener);

        mTextView = (TextView) findViewById(R.id.text_saved);
        mEditText = (EditText) findViewById(R.id.edit_input_text);
    }

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_save:
                    if (getInternalStorageEmptySize() > LOW_MEMORY) {
                        saveInternalStorage(mEditText.getText().toString());
                    } else {
                        mTextView.setText("空き領域がありません");
                    }
                    break;
                case R.id.button_load:
                    String str = loadInternalStorage();
                    if (str != null) {
                        mTextView.setText(str);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private boolean saveInternalStorage(String str) {
        try {
            FileOutputStream stream =
                    openFileOutput(FILE_NAME, MODE_PRIVATE | MODE_APPEND);
            stream.write(str.getBytes());
            stream.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private String loadInternalStorage() {
        String str;
        try {
            FileInputStream stream = openFileInput(FILE_NAME);
            byte[] readData = new byte[stream.available()];
            stream.read(readData);
            str = new String(readData);
            stream.close();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return str;
    }

    // アプリケーションで使用出来るファイルシステム空き領域を取得する(byte単位)
    private long getInternalStorageEmptySize() {
        File path = getFilesDir();
        StatFs stat = new StatFs(path.getPath());
        return stat.getAvailableBlocks();
    }
}

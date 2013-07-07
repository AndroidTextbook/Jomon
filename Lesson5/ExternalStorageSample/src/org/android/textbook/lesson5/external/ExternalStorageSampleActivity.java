
package org.android.textbook.lesson5.external;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ExternalStorageSampleActivity extends Activity {


    private EditText mEditText;

    private TextView mTextView;

    private static final String FILE_NAME = "external.txt";

    private static final long LOW_MEMORY = 1024; // 1KB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage_sample);

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
                    if (canWriteExternalStorage()) {
                        saveExternalStorage(mEditText.getText().toString());
                    } else {
                        mTextView.setText("空き領域がありません");
                    }
                    break;
                case R.id.button_load:
                    String str = loadExternalStorage();
                    if (str != null) {
                        mTextView.setText(str);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private boolean saveExternalStorage(String str) {
        if (getExternalStorageEmptySize() <= LOW_MEMORY) {
            return false;
        }

        try {
            File file = new File(getExternalFilesDir(null), FILE_NAME);
            FileOutputStream stream = new FileOutputStream(file, false);
            stream.write(str.getBytes());
            stream.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private String loadExternalStorage() {
        String str;
        try {
            File file = new File(getExternalFilesDir(null), FILE_NAME);
            FileInputStream stream = new FileInputStream(file);
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
    private long getExternalStorageEmptySize() {
        File path = getExternalFilesDir(null);
        StatFs stat = new StatFs(path.getPath());
        return stat.getAvailableBlocks();
    }

    // 外部ストレージが書き込み可能かどうかを取得する
    private boolean canWriteExternalStorage() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        // MEDIA_MOUNTED以外の状態は書き込みができない状態にあるためfalseを返す
        return false;
    }
}


package org.android.textbook.lesson5.contentproviderrequestsample;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ContentProviderRquestActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_rquest);

        Button button = (Button) findViewById(R.id.button_delete);
        button.setOnClickListener(mOnClickListener);
        button = (Button) findViewById(R.id.button_insert);
        button.setOnClickListener(mOnClickListener);
        button = (Button) findViewById(R.id.button_update);
        button.setOnClickListener(mOnClickListener);
        button = (Button) findViewById(R.id.button_query);
        button.setOnClickListener(mOnClickListener);

        mTextView = (TextView) findViewById(R.id.text_query);
    }

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            Uri uri = null;
            ContentResolver contentResolver = getContentResolver();
            ContentValues values;
            Cursor cursor = null;

            switch (v.getId()) {
                case R.id.button_insert:
                    uri = Students.CONTENT_URI;
                    values = new ContentValues();
                    values.put(Students.DB_COLUMN_NAME, "Kimura");
                    values.put(Students.DB_COLUMN_JAPANESE, 60);
                    values.put(Students.DB_COLUMN_ENGLISH, 60);
                    values.put(Students.DB_COLUMN_MATH, 60);
                    contentResolver.insert(uri, values);
                    break;
                case R.id.button_delete:
                    uri = ContentUris.withAppendedId(Students.CONTENT_URI, 2);
                    contentResolver.delete(uri, null, null);
                    break;
                case R.id.button_update:
                    uri = ContentUris.withAppendedId(Students.CONTENT_URI, 1);
                    values = new ContentValues();
                    values.put(Students.DB_COLUMN_JAPANESE, 100);
                    contentResolver.update(uri, values, null, null);
                    break;
                case R.id.button_query:
                    uri = Students.CONTENT_URI;
                    cursor = contentResolver.query(uri, null, null, null, null);
                    break;
                default:
                    break;
            }

            if (v.getId() != R.id.button_query) {
                // クエリー意外の場合はプロバイダー内の全データを取得する
                cursor = contentResolver.query(Students.CONTENT_URI, null, null, null, null);
            }
            showCursor(cursor);

        }

    };

    public void showCursor(Cursor cursor) {
        String str = new String();
        cursor.moveToFirst();
        int length = cursor.getCount();
        int index = 0;
        for (int i = 0; i < length; i++) {
            index = cursor.getColumnIndex(Students.DB_COLUMN_ID);
            if (index >= 0) {
                long id = cursor.getLong(index);
                str += "id: " + id + "\t";
            }

            index = cursor.getColumnIndex(Students.DB_COLUMN_NAME);
            if (index >= 0) {
                String name = cursor.getString(index);
                str += "name: " + name + "\t";
            }

            index = cursor.getColumnIndex(Students.DB_COLUMN_JAPANESE);
            if (index >= 0) {
                String japanese = cursor.getString(index);
                str += "japanese: " + japanese + "\t";
            }

            index = cursor.getColumnIndex(Students.DB_COLUMN_ENGLISH);
            if (index >= 0) {
                String english = cursor.getString(index);
                str += "english: " + english + "\t";
            }

            index = cursor.getColumnIndex(Students.DB_COLUMN_MATH);
            if (index >= 0) {
                String math = cursor.getString(index);
                str += "math: " + math;
            }
            str += "\n";

            cursor.moveToNext();
        }
        cursor.close();
        mTextView.setText(str);

    }

}


package org.android.textbook.lesson5.sqlite;

import java.util.Random;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SqliteSampleActivity extends Activity {

    private SqliteDataBaseHelper mSqliteDataBaseHelper;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_sample);

        mSqliteDataBaseHelper = new SqliteDataBaseHelper(this);

        Button insert = (Button) findViewById(R.id.button_insert);
        insert.setOnClickListener(mOnClickListener);

        Button delete = (Button) findViewById(R.id.button_delete);
        delete.setOnClickListener(mOnClickListener);

        Button update = (Button) findViewById(R.id.button_update);
        update.setOnClickListener(mOnClickListener);

        Button sample = (Button) findViewById(R.id.button_reset);
        sample.setOnClickListener(mOnClickListener);

        Button show = (Button) findViewById(R.id.button_show);
        show.setOnClickListener(mOnClickListener);

        Button query = (Button) findViewById(R.id.button_query);
        query.setOnClickListener(mOnClickListener);

        mTextView = (TextView) findViewById(R.id.text_db);
    }

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_insert:
                    insert();
                    show();
                    break;
                case R.id.button_delete:
                    delete();
                    show();
                    break;
                case R.id.button_update:
                    update();
                    show();
                    break;
                case R.id.button_reset:
                    reset();
                    show();
                    break;
                case R.id.button_show:
                    show();
                    break;
                case R.id.button_query:
                    SQLiteDatabase db = mSqliteDataBaseHelper.getReadableDatabase();
                    Cursor cursor = query(db);
                    show(db, cursor);
                    break;
            }

        }

    };

    private static final int MAX_SAMPLE_DATA = 3;
    private static final String[] NAME_ARRAY = new String[] {
            "Kimura", "Kaname", "Fujita"
    };

    private static final int[] ENGLISH_ARRAY = new int[] {
            55, 100, 80
    };

    private static final int[] JAPANESE_ARRAY = new int[] {
            20, 55, 100
    };

    private static final int[] MATH_ARRAY = new int[] {
            100, 80, 60
    };

    private void reset() {
        SQLiteDatabase db = mSqliteDataBaseHelper.getWritableDatabase();
        db.delete(SqliteDataBaseHelper.DB_TABLE, null, null);

        for (int i = 0; i < MAX_SAMPLE_DATA; i++) {
            ContentValues values = new ContentValues();
            values.put(SqliteDataBaseHelper.DB_COLUMN_NAME, NAME_ARRAY[i]);
            values.put(SqliteDataBaseHelper.DB_COLUMN_JAPANESE, JAPANESE_ARRAY[i]);
            values.put(SqliteDataBaseHelper.DB_COLUMN_ENGLISH, ENGLISH_ARRAY[i]);
            values.put(SqliteDataBaseHelper.DB_COLUMN_MATH, MATH_ARRAY[i]);

            db.insert(SqliteDataBaseHelper.DB_TABLE, null, values);
        }

        db.close();
    }

    private void insert() {
        SQLiteDatabase db = mSqliteDataBaseHelper.getWritableDatabase();

        // 3人のデータをランダムにデータを追加する
        Random rnd = new Random();
        int index = rnd.nextInt(MAX_SAMPLE_DATA);

        ContentValues values = new ContentValues();
        values.put(SqliteDataBaseHelper.DB_COLUMN_NAME, NAME_ARRAY[index]);
        values.put(SqliteDataBaseHelper.DB_COLUMN_JAPANESE,
                JAPANESE_ARRAY[index]);
        values.put(SqliteDataBaseHelper.DB_COLUMN_ENGLISH,
                ENGLISH_ARRAY[index]);
        values.put(SqliteDataBaseHelper.DB_COLUMN_MATH, MATH_ARRAY[index]);

        // データの追加
        db.insert(SqliteDataBaseHelper.DB_TABLE, null, values);
        db.close();
    }

    private void delete() {
        SQLiteDatabase db = mSqliteDataBaseHelper.getWritableDatabase();

        // NameがKimuraのものをすべて削除
        db.delete(SqliteDataBaseHelper.DB_TABLE,
                SqliteDataBaseHelper.DB_COLUMN_NAME + " = ?",
                new String[] {
                    NAME_ARRAY[0]
                });
        // db.delete(SqliteDataBaseHelper.DB_TABLE,
        // SqliteDataBaseHelper.DB_COLUMN_NAME + " = '"
        // + NAME_ARRAY[0] + "'", null);
        db.close();
    }

    private Cursor query(SQLiteDatabase db) {
        String[] columns = new String[] {
                SqliteDataBaseHelper.DB_COLUMN_NAME,
                SqliteDataBaseHelper.DB_COLUMN_ENGLISH
        };
        String selection = SqliteDataBaseHelper.DB_COLUMN_ENGLISH + ">=" + 60;

        Cursor cursor = db.query(SqliteDataBaseHelper.DB_TABLE, columns, selection, null, null,
                null, null);
        return cursor;
    }

    private void update() {
        ContentValues values = new ContentValues();
        values.put(SqliteDataBaseHelper.DB_COLUMN_ENGLISH, 100);
        SQLiteDatabase db = mSqliteDataBaseHelper.getWritableDatabase();

        // NameがFujitaのデータのEnglishの項目をすべて100に更新する
        // db.update(SqliteDataBaseHelper.DB_TABLE, values,
        // SqliteDataBaseHelper.DB_COLUMN_NAME + " = ?", new String[] {
        // NAME_ARRAY[2]
        // });
        db.update(SqliteDataBaseHelper.DB_TABLE, values,
                SqliteDataBaseHelper.DB_COLUMN_NAME + " = '" + NAME_ARRAY[2] + "'", null);
        db.close();
    }

    private void show() {
        SQLiteDatabase db = mSqliteDataBaseHelper.getWritableDatabase();
        Cursor cursor = db.query(SqliteDataBaseHelper.DB_TABLE, new String[] {
                SqliteDataBaseHelper.DB_COLUMN_ID,
                SqliteDataBaseHelper.DB_COLUMN_NAME,
                SqliteDataBaseHelper.DB_COLUMN_JAPANESE,
                SqliteDataBaseHelper.DB_COLUMN_ENGLISH,
                SqliteDataBaseHelper.DB_COLUMN_MATH
        }, null, null, null, null, null);

        show(db, cursor);
    }

    private void show(SQLiteDatabase db, Cursor cursor) {
        String str = new String();
        cursor.moveToFirst();
        int length = cursor.getCount();
        int index = 0;
        for (int i = 0; i < length; i++) {
            index = cursor.getColumnIndex(SqliteDataBaseHelper.DB_COLUMN_ID);
            if (index >= 0) {
                long id = cursor.getLong(index);
                str += "id: " + id + "\t";
            }

            index = cursor.getColumnIndex(SqliteDataBaseHelper.DB_COLUMN_NAME);
            if (index >= 0) {
                String name = cursor.getString(index);
                str += "name: " + name + "\t";
            }

            index = cursor.getColumnIndex(SqliteDataBaseHelper.DB_COLUMN_JAPANESE);
            if (index >= 0) {
                String japanese = cursor.getString(index);
                str += "japanese: " + japanese + "\t";
            }

            index = cursor.getColumnIndex(SqliteDataBaseHelper.DB_COLUMN_ENGLISH);
            if (index >= 0) {
                String english = cursor.getString(index);
                str += "english: " + english + "\t";
            }

            index = cursor.getColumnIndex(SqliteDataBaseHelper.DB_COLUMN_MATH);
            if (index >= 0) {
                String math = cursor.getString(index);
                str += "math: " + math;
            }
            str += "\n";

            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        mTextView.setText(str);
    }

}

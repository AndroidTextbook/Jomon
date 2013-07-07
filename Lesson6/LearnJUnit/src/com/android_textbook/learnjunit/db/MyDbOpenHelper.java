
package com.android_textbook.learnjunit.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbOpenHelper extends SQLiteOpenHelper {
    public MyDbOpenHelper(Context context) {
        super(context, "my.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE STR_ENTRY(ID KEY INTEGER PRIMARY KEY, STR TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // none
    }

    public String putStr(int id, String str) {
        SQLiteDatabase db = getWritableDatabase();
        String lastStr = null;
        try {
            db.beginTransaction();
            { // 古い値が無いか検索する
                Cursor cursor = db.query("str_entry" /* table */, new String[] {
                    "str"
                }/* columns */, "id=?"/* selection */, new String[] {
                    String.valueOf(id)
                }/* selectionArgs */, null/* groupBy */, null/* having */, null/* orderBy */);
                if (cursor.moveToNext()) {
                    lastStr = cursor.getString(0);
                }
                cursor.close();
            }
            ContentValues values = new ContentValues();
            values.put("id", id);
            values.put("str", str);
            db.insert("STR_ENTRY", null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
        return lastStr;
    }

    public String getStr(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String str = null;
        try {
            Cursor cursor = db.query("str_entry" /* table */, new String[] {
                "str"
            }/* columns */, "id=?"/* selection */, new String[] {
                String.valueOf(id)
            }/* selectionArgs */, null/* groupBy */, null/* having */, null/* orderBy */);
            if (cursor.moveToNext()) {
                str = cursor.getString(0);
            }
            cursor.close();
        } finally {
            db.close();
        }
        return str;
    }
}

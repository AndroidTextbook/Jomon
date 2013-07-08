
package com.android_textbook.learnjunit.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SimpleDbOpenHelper extends SQLiteOpenHelper {
    public static final String TABLE = "STR_ENTRY";

    public SimpleDbOpenHelper(Context context) {
        super(context, "provider.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE STR_ENTRY(_ID KEY INTEGER PRIMARY KEY, STR TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // none
    }
}

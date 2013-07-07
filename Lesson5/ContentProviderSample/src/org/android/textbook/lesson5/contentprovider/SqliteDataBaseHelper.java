package org.android.textbook.lesson5.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteDataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_FILE_NAME = "SqliteSample";

    public static final String DB_TABLE = "DataBaseTable";

    private static final int DB_VERSION = 1;



    public SqliteDataBaseHelper(Context context) {
        super(context, DB_FILE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "";
        sql += "create table " + DB_TABLE + " ( ";
        sql += Students.DB_COLUMN_ID + " integer primary key autoincrement,";
        sql += Students.DB_COLUMN_NAME + " text not null,";
        sql += Students.DB_COLUMN_ENGLISH + " integer,";
        sql += Students.DB_COLUMN_JAPANESE + " integer,";
        sql += Students.DB_COLUMN_MATH + " integer";
        sql += ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}

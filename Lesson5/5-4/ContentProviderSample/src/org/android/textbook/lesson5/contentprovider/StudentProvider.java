
package org.android.textbook.lesson5.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class StudentProvider extends ContentProvider {

    private SqliteDataBaseHelper mSqliteDataBaseHelper;

    private static final UriMatcher mUriMatcher;

    private static final int STUDENTS = 1;
    private static final int STUDENT_ID = 2;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(Students.AUTHORITY, "students", STUDENTS);
        mUriMatcher.addURI(Students.AUTHORITY, "students/#", STUDENT_ID);
    }

    @Override
    public boolean onCreate() {
        mSqliteDataBaseHelper = new SqliteDataBaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        SQLiteDatabase db = mSqliteDataBaseHelper.getWritableDatabase();
        Cursor result = null;
        switch (mUriMatcher.match(uri)) {
            case STUDENTS:
                result = db.query(SqliteDataBaseHelper.DB_TABLE, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case STUDENT_ID:
            default:
                break;
        }

        return result;
    }

    @Override
    public String getType(Uri uri) {
        switch (mUriMatcher.match(uri)) {
            case STUDENTS:
                return Students.CONTENT_TYPE;
            case STUDENT_ID:
                return Students.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mSqliteDataBaseHelper.getWritableDatabase();
        long resultId = 0;
        Uri resultUri = null;

        switch (mUriMatcher.match(uri)) {
            case STUDENTS:
                // データの追加
                resultId = db.insert(SqliteDataBaseHelper.DB_TABLE, null, values);
                break;
            case STUDENT_ID:
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        if (resultId > 0) {
            resultUri = ContentUris.withAppendedId(Students.CONTENT_URI, resultId);
            getContext().getContentResolver().notifyChange(resultUri, null);
        }

        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mSqliteDataBaseHelper.getWritableDatabase();
        int result = 0;
        switch (mUriMatcher.match(uri)) {
            case STUDENTS:
                result = db.delete(SqliteDataBaseHelper.DB_TABLE, selection,
                        selectionArgs);
                break;
            case STUDENT_ID:
                String id = uri.getPathSegments().get(1);
                result = db.delete(SqliteDataBaseHelper.DB_TABLE, Students._ID
                        + "=" + id
                        + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ")" : ""),
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        return result;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        SQLiteDatabase db = mSqliteDataBaseHelper.getWritableDatabase();
        int result = 0;
        switch (mUriMatcher.match(uri)) {
            case STUDENTS:
                // 複数件同時に更新
                result = db.update(SqliteDataBaseHelper.DB_TABLE, values, selection, selectionArgs);
                break;
            case STUDENT_ID:
                // ID指定の更新
                String id = uri.getPathSegments().get(1);
                result = db.update(SqliteDataBaseHelper.DB_TABLE, values, Students._ID
                        + "=" + id
                        + (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ")" : ""),

                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        return result;
    }

}


package com.android_textbook.learnjunit.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class SimpleProvider extends ContentProvider {
    public static final String AUTHORITY = "com.example.applearning.simpleprovider";

    private static final int TABLE_URI = 1;

    private static final int SPECIFIC_URI = 2;

    private static UriMatcher MATCHER;
    static {
        MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        MATCHER.addURI(AUTHORITY, "entry", TABLE_URI);
        MATCHER.addURI(AUTHORITY, "entry/#", SPECIFIC_URI);
    }

    private SimpleDbOpenHelper mSimpleDbOpenHelper;

    private SQLiteDatabase mDb;

    @Override
    public boolean onCreate() {
        mSimpleDbOpenHelper = new SimpleDbOpenHelper(getContext());
        mDb = mSimpleDbOpenHelper.getWritableDatabase();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(SimpleDbOpenHelper.TABLE);
        switch (MATCHER.match(uri)) {
            case SPECIFIC_URI:
                qb.appendWhere("_ID=" + uri.getPathSegments().get(1));
                break;
            case TABLE_URI:
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        // Make the query.
        Cursor c = qb.query(mDb, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (MATCHER.match(uri)) {
            case SPECIFIC_URI:
                // OK
                break;
            case TABLE_URI:
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        values.put("_ID", uri.getPathSegments().get(1));
        mDb.insert(SimpleDbOpenHelper.TABLE, null, values);
        return uri;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0; // 使用しない
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mDb.delete(SimpleDbOpenHelper.TABLE, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        return null; // none
    }

}

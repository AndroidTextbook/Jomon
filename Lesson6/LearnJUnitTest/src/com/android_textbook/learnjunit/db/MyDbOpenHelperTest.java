
package com.android_textbook.learnjunit.db;

import com.android_textbook.learnjunit.db.MyDbOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.InstrumentationTestCase;
import android.test.RenamingDelegatingContext;

public class MyDbOpenHelperTest extends InstrumentationTestCase {
    private MyDbOpenHelper mHelper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Context targetContext = getInstrumentation().getTargetContext();
        RenamingDelegatingContext rdContext = new RenamingDelegatingContext(targetContext, "test_");
        mHelper = new MyDbOpenHelper(rdContext);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mHelper.close();
        mHelper = null;
    }

    public void testGetStr() {
        // 最初に登録されていないのでnullが返されることを確認する
        assertNull(mHelper.getStr(3));
        { // テストデータをINSERTする
            SQLiteDatabase db = mHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id", 3);
            values.put("str", "test string 1");
            db.insert("STR_ENTRY", null, values);
        }
        // 登録した値が取得できることを確認する
        assertEquals("test string 1", mHelper.getStr(3));
    }

    public void testPutStr() {
        // 登録前はnullとなることを確認する
        assertNull(mHelper.getStr(4));
        // 登録し、値が取得できることを確認する
        assertNull(mHelper.putStr(4, "test string 2"));
        assertEquals("test string 2", mHelper.putStr(4, "test string 3"));
    }
}

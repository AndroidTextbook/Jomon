
package com.android_textbook.learnjunit.provider;

import com.android_textbook.learnjunit.provider.SimpleProvider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;

public class SimpleProviderTest extends ProviderTestCase2<SimpleProvider> {
    private Uri CONTENT_URI = Uri.parse("content://" + SimpleProvider.AUTHORITY + "/entry");

    public SimpleProviderTest() {
        super(SimpleProvider.class, SimpleProvider.AUTHORITY);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testSimpleProvider() {
        SimpleProvider provider = getProvider();
        Uri targetUri = ContentUris.withAppendedId(CONTENT_URI, 1);
        { // 初期状態では取得できないことを確認する
            Cursor c = provider.query(targetUri, new String[] {
                "STR"
            }, null, null, null);
            // 0件なので必ずfalseになるはず
            assertFalse(c.moveToNext());
        }
        { // INSERTを行う
            ContentValues values = new ContentValues();
            values.put("STR", "Something");
            provider.insert(targetUri, values);
        }
        { // INSERTしたものを取得できることを確認する
            Cursor c = provider.query(targetUri, new String[] {
                "STR"
            }, null, null, null);
            // 1件なので必ずtrueになるはず
            assertTrue(c.moveToNext());
            assertEquals("Something", c.getString(0));
            // 1件なので2回目は必ずtrueになるはず
            assertFalse(c.moveToNext());
        }
    }

    public void testUri() {
        SimpleProvider provider = getProvider();
        Uri badUri = Uri.parse("content://" + SimpleProvider.AUTHORITY + "/bad");
        try {
            provider.query(badUri, new String[] {
                "STR"
            }, null, null, null);
            // 上で例外が発生するのでここを通ると失敗
            fail();
        } catch (IllegalArgumentException e) {
            // OK
        }
    }
}

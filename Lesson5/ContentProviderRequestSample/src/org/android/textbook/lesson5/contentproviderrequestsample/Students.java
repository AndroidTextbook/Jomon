package org.android.textbook.lesson5.contentproviderrequestsample;

import android.net.Uri;
import android.provider.BaseColumns;

public class Students implements BaseColumns {
    public static final String AUTHORITY = "org.android.textbook.lesson5.contentprovider";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/students");

    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.lesson5.students";

    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.lesson5.students";

    public static final String DB_COLUMN_ID = "_id";
    public static final String DB_COLUMN_NAME = "Name";
    public static final String DB_COLUMN_JAPANESE = "Japanease";
    public static final String DB_COLUMN_ENGLISH = "English";
    public static final String DB_COLUMN_MATH = "Math";
}


package com.android_textbook.learnjunit.util;

import java.lang.reflect.Field;

public class TestUtil {
    /**
     * リフレクションを用いてフィールドに値を設定します。
     * 
     * @param target 対象となるオブジェクト
     * @param fieldName フィールド名
     * @param value 設定する値
     */
    public static void setValue(Object target, String fieldName, Object value)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    /**
     * リフレクションを用いてフィールドから値を取得します。
     * 
     * @param target 対象となるオブジェクト
     * @param fieldName フィールド名
     * @return フィールドの値
     */
    public static Object pickValue(Object target, String fieldName) throws NoSuchFieldException,
            IllegalAccessException {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(target);
    }
}

package org.android.textbook.lesson5.singleton;

public class ShareDataManager {

    private static ShareDataManager sInstance = new ShareDataManager();

    private int mIntValue;

    private String mStringValue;

    private ShareDataManager() {
    }

    public static ShareDataManager getInstance() {
        return sInstance;
    }

    public int getIntValue() {
        return mIntValue;
    }

    public void setIntValue(int intValue) {
        mIntValue = intValue;
    }

    public String getStringValue() {
        return mStringValue;
    }

    public void setStringValue(String stringValue) {
        mStringValue = stringValue;
    }

}

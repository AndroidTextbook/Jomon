package org.android.textbook.lesson5.application;

import android.app.Application;

public class ApplicationShare extends Application {

    private int mIntValue;

    private String mStringValue;

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

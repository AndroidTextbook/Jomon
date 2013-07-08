
package org.android.textbook.lesson5.saveserializable;

import java.io.Serializable;

public class SerializableData implements Serializable {

    private static final long serialVersionUID = -925191044669456266L;

    private int mId;

    private String mText;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

}

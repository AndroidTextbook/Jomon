package org.android.textbook.lesson4.keyeventsample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;

public class KeyView extends Button {
    private static final String TAG = "KeyView";

    public KeyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.e(TAG, "dispatchKeyEvent : ");
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e(TAG, "onKeyDown : ");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Log.e(TAG, "onKeyLongPress : ");
        return super.onKeyLongPress(keyCode, event);

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.e("KeyView", "onKeyUp : ");
        return super.onKeyUp(keyCode, event);
    }

}

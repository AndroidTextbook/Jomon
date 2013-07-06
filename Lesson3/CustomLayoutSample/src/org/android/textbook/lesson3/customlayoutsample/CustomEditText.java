package org.android.textbook.lesson3.customlayoutsample;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;

public class CustomEditText extends EditText {

    private Context mContext = null;
    private String mToastText = null;

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText);
        CharSequence s = a.getString(R.styleable.CustomEditText_toastText);

        setToastText(s.toString());
        a.recycle();
    }

    public void setToastText(String toastText) {
        mToastText = toastText;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            String message = mToastText;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(mContext, message, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        return super.onTouchEvent(event);
    }

}

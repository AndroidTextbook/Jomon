package org.android.textbook.lesson4.gesturesample;

import android.os.Bundle;
import android.app.Activity;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.Toast;

public class GestureSampleActivity extends Activity {

    private GestureDetector mDetector;
    private OnDoubleTapListener mDoubleTapListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_sample);

        // ジェスチャーディテクタの生成とリスナーの登録
        mDetector = new GestureDetector(getBaseContext(), mGestureListener);
        // ダブルタップ用のリスナーをセット
        mDetector.setOnDoubleTapListener(mDoubleTapListener);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mDetector.onTouchEvent(event)) {
            // ジェスチャーディテクタで処理を消費した
            return true;
        }
        return super.onTouchEvent(event);
    }

    private OnGestureListener mGestureListener = new OnGestureListener() {

        @Override
        public boolean onDown(MotionEvent e) {
            Toast.makeText(GestureSampleActivity.this, "onDown",
                    Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                float velocityY) {
            Toast.makeText(GestureSampleActivity.this, "onFling",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Toast.makeText(GestureSampleActivity.this, "onLongPress",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                float distanceX, float distanceY) {
            Toast.makeText(GestureSampleActivity.this, "onScroll",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Toast.makeText(GestureSampleActivity.this, "onShowPress",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Toast.makeText(GestureSampleActivity.this, "onSingleTapUp",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

    };

}

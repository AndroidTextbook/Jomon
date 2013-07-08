
package org.android.textbook.lesson3.imageanimationsample;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ImageAnimationSample extends Activity {

    private AnimationDrawable mAnimDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_animation_sample);
        
        Button startBtn = (Button) findViewById(R.id.anim_start);
        startBtn.setOnClickListener(mOnClickListener);

        Button stopBtn = (Button) findViewById(R.id.anim_stop);
        stopBtn.setOnClickListener(mOnClickListener);
        
        ImageView animImg =
                (ImageView) findViewById(R.id.anim_image);
        animImg.setBackgroundResource(R.anim.image_animation_list);
        mAnimDrawable = (AnimationDrawable) animImg.getBackground();
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            case R.id.anim_start:
                // アニメーション開始
                mAnimDrawable.start();
                break;
            case R.id.anim_stop:
                // アニメーション停止
                mAnimDrawable.stop();
                break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.image_animation_sample, menu);
        return true;
    }

}

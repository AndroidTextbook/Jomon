package org.android.textbook.lesson4.audiosample;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class AudioSampleActivity extends Activity implements OnClickListener,
        OnCompletionListener {

    private MediaPlayer mMediaPlayer;
    private Button mPlayButton;
    private Button mPauseButton;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_sample);

        mPlayButton = (Button) findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(this);

        mPauseButton = (Button) findViewById(R.id.pause_button);
        mPauseButton.setOnClickListener(this);

    }

    private Handler mSeekUpdateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int current_pos = mMediaPlayer.getCurrentPosition();
            mSeekBar.setProgress(current_pos);
            sendEmptyMessageDelayed(0, 1000);
        }
    };

    private OnSeekBarChangeListener mOnSeekBarChangeListener = new OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                boolean fromUser) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            mMediaPlayer.seekTo(seekBar.getProgress());
        }

    };

    @Override
    protected void onResume() {
        super.onResume();
        // MediaPlayerのインスタンスを取得
        mMediaPlayer = MediaPlayer.create(this, R.raw.sample);
        mMediaPlayer.setOnCompletionListener(this);

        // 曲の長さ(時間)を取得
        int duration = mMediaPlayer.getDuration();
        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        mSeekBar.setMax(duration);
        mSeekBar.setOnSeekBarChangeListener(mOnSeekBarChangeListener);

        mPlayButton.setEnabled(true);
        mPauseButton.setEnabled(false);
    };

    @Override
    protected void onPause() {
        super.onPause();
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
        mMediaPlayer.release();
        mMediaPlayer = null;
        mSeekUpdateHandler.removeMessages(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        case R.id.play_button:
            // 再生する
            mMediaPlayer.start();
            mPlayButton.setEnabled(false);
            mPauseButton.setEnabled(true);
            mSeekUpdateHandler.sendEmptyMessageDelayed(0, 1000);
            break;
        case R.id.pause_button:
            // 一時停止する
            mMediaPlayer.pause();
            mPlayButton.setEnabled(true);
            mPauseButton.setEnabled(false);
            mSeekUpdateHandler.removeMessages(0);
            break;
        default:
            break;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mPlayButton.setEnabled(true);
        mPauseButton.setEnabled(false);
        mSeekUpdateHandler.removeMessages(0);
    }

}

package com.cloud.design.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.cloud.customviews.CircleImageView;
import com.cloud.customviews.CircleProgressBar;
import com.cloud.design.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerActivity extends AppCompatActivity {

    private static final int STATE_PAUSE = 0;
    private static final int STATE_PLAY = 1;
    private static final int STATE_STOP = -1;

    private int mState = 65535;

    private MediaPlayer mMediaPlayer;
    private Button mButtonPlay;
    private CircleImageView mAlbumImage;

    private Timer mTimer;

    private ObjectAnimator mAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        initViews();
        initAnimator();

        mMediaPlayer = new MediaPlayer();

        try {
            AssetFileDescriptor descriptor = getAssets().openFd("See You Again.mp3");
            mMediaPlayer.setDataSource(descriptor);
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CircleProgressBar progressBar = findViewById(R.id.progress);

        mMediaPlayer.setOnPreparedListener(mp -> {
            progressBar.setMaxProgress(mp.getDuration());
            mState = STATE_STOP;
        });

        mMediaPlayer.setOnCompletionListener(mp -> {
            Log.d("PlayerActivity", "onCompletion");
            mState = STATE_STOP;
            progressBar.setProgress(mp.getDuration());
            mAnimator.end();
            mButtonPlay.setBackgroundResource(R.drawable.btn_play);
        });

        mTimer = new Timer();

        mButtonPlay.setOnClickListener(v -> {
            if (mState == STATE_PLAY) {
                mState = STATE_PAUSE;
                mMediaPlayer.pause();
                mAnimator.pause();
                v.setBackgroundResource(R.drawable.btn_play);
            } else {

                if (mState == STATE_PAUSE) {
                    mAnimator.resume();
                } else if (mState == STATE_STOP) {
                    mAnimator.start();
                }
                mState = STATE_PLAY;

                mMediaPlayer.start();

                v.setBackgroundResource(R.drawable.btn_pause);

                mTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (mMediaPlayer.isPlaying()) {
                            progressBar.setProgress(mMediaPlayer.getCurrentPosition());
                        }
                    }
                }, 0, 500);
            }
        });
    }

    private void initViews() {
        mButtonPlay = findViewById(R.id.button_play);
        mAlbumImage = findViewById(R.id.album_image);
    }

    private void initAnimator() {
        mAnimator = ObjectAnimator.ofFloat(mAlbumImage, "rotation", 0f, 360f);
        mAnimator.setDuration(30000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setRepeatMode(ValueAnimator.RESTART);
    }

    @Override
    protected void onDestroy() {
        mTimer.cancel();
        mTimer = null;
        mMediaPlayer.release();
        mMediaPlayer = null;
        mAnimator.end();
        mAnimator = null;
        super.onDestroy();
    }
}

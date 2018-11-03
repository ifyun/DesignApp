package com.cloud.design.activity;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cloud.design.R;
import com.cloud.design.databinding.ActivityProgressBarBinding;

import java.text.MessageFormat;

public class ProgressBarActivity extends AppCompatActivity {

    private ActivityProgressBarBinding mBinding;
    private Handler mHandler;
    private Progress mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_progress_bar);

        mHandler = new Handler();

        mBinding.buttonStartProgress.setOnClickListener(v -> {
            mProgress = new Progress(v);
            mProgress.start();
        });
    }

    private class Progress extends Thread {

        int mProgress;
        View mView;

        Progress(View v) {
            mProgress = 0;
            mView = v;
        }

        @Override
        public void run() {
            runOnUiThread(() -> mView.setClickable(false));
            while (mProgress <= 100) {
                mHandler.post(() -> {
                    mBinding.progressGreen.setProgress(mProgress);
                    mBinding.progressGreen.setText(MessageFormat.format("{0}%", mProgress));
                    mBinding.progressYellow.setProgress(mProgress);
                    mBinding.progressYellow.setText(MessageFormat.format("{0}%", mProgress));
                    mBinding.fanProgress.setProgress(mProgress);
                });

                if (mProgress == 100) {
                    break;
                }

                mProgress += 5;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            runOnUiThread(() -> mView.setClickable(true));
        }
    }
}

package com.cloud.design.activity;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cloud.customviews.ProgressButton;
import com.cloud.design.R;
import com.cloud.design.databinding.ActivityProgressButtonBinding;

import java.text.MessageFormat;

public class ProgressButtonActivity extends AppCompatActivity {

    private ActivityProgressButtonBinding mBinding;

    private Handler mHandler;

    private Progress mProgress1;
    private Progress mProgress2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_progress_button);

        mHandler = new Handler();

        mBinding.buttonProgressGreen.setOnClickListener(v -> {
            v.setClickable(false);
            ((ProgressButton) v).reset();
            mProgress1 = new Progress((ProgressButton) v);
            mProgress1.start();
        });

        mBinding.buttonProgressBlue.setOnClickListener(v -> {
            v.setClickable(false);
            ((ProgressButton) v).reset();
            mProgress2 = new Progress((ProgressButton) v);
            mProgress2.start();
        });
    }

    private class Progress extends Thread {

        ProgressButton mButton;
        int mProgress;

        Progress(ProgressButton button) {
            mProgress = 0;
            mButton = button;
        }

        @Override
        public void run() {
            while (mProgress <= 100) {
                mHandler.post(() -> {
                    mButton.setProgress(mProgress);
                    mButton.setText(MessageFormat.format("{0}%", mProgress));
                });

                if (mProgress == 100) {
                    break;
                }

                mProgress += 5;

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            runOnUiThread(() -> mButton.setClickable(true));
        }
    }
}

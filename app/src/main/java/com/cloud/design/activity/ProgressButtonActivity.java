package com.cloud.design.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cloud.customviews.ProgressButton;
import com.cloud.design.R;
import com.cloud.design.databinding.ActivityProgressButtonBinding;

import java.text.MessageFormat;

public class ProgressButtonActivity extends AppCompatActivity {

    private ActivityProgressButtonBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_progress_button);

        mBinding.buttonProgressGreen.setMaxProgress(100);
        mBinding.buttonProgressBlue.setMaxProgress(100);
        mBinding.buttonProgressGreen.setOnClickListener(v -> {
            v.setClickable(false);
            ((ProgressButton) v).reset();
            new ProgressThread((ProgressButton) v).start();
        });

        mBinding.buttonProgressBlue.setOnClickListener(v -> {
            v.setClickable(false);
            ((ProgressButton) v).reset();
            new ProgressThread((ProgressButton) v).start();
        });
    }

    private class ProgressThread extends Thread {

        ProgressButton mButton;
        int mProgress = 0;

        ProgressThread(ProgressButton button) {
            mButton = button;
        }

        @Override
        public void run() {
            while (mProgress <= 100) {
                mButton.setProgress(mProgress);
                mButton.setText(MessageFormat.format("{0}%", mProgress));
                try {
                    Thread.sleep(100);
                    mProgress += 5;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            runOnUiThread(() -> mButton.setClickable(true));
        }
    }
}

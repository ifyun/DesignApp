package com.cloud.design.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cloud.design.R;
import com.cloud.design.databinding.ActivityProgressBarBinding;

import java.text.MessageFormat;

public class ProgressBarActivity extends AppCompatActivity {

    private ActivityProgressBarBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_progress_bar);

        mBinding.buttonStartProgress.setOnClickListener(v -> new Thread(new Progress()).start());
    }

    private class Progress implements Runnable {

        public int progress = 0;

        @Override
        public void run() {
            while (progress <= 100) {
                mBinding.progressGreen.setProgress(progress);
                mBinding.progressGreen.setText(MessageFormat.format("{0}%", progress));
                mBinding.progressYellow.setProgress(progress);
                mBinding.progressYellow.setText(MessageFormat.format("{0}%", progress));
                progress += 5;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

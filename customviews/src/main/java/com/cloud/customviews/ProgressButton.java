package com.cloud.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class ProgressButton extends AppCompatButton {

    private float mCornerRadius = 0;
    private float mProgressMargin = 0;

    private boolean mFinish;

    private int mProgress;
    private int mMaxProgress = 100;
    private int mMinProgress = 0;

    private GradientDrawable mDrawableButton;
    private GradientDrawable mDrawableProgressBackground;
    private GradientDrawable mDrawableProgress;

    public ProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //Progress background drawable
        mDrawableProgressBackground = new GradientDrawable();
        //Progress drawable
        mDrawableProgress = new GradientDrawable();
        //Normal drawable
        mDrawableButton = new GradientDrawable();

        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.ProgressButton);

        try {
            mProgressMargin = attr.getDimension(R.styleable.ProgressButton_progressMargin, mProgressMargin);
            mCornerRadius = attr.getDimension(R.styleable.ProgressButton_cornerRadius, mCornerRadius);
            //Get default normal color
            int defaultButtonColor = getResources().getColor(R.color.colorGray, null);
            //Get custom normal color
            int buttonColor = attr.getColor(R.styleable.ProgressButton_normalColor, defaultButtonColor);
            //Set normal color
            mDrawableButton.setColor(buttonColor);

            //Get default progress color
            int defaultProgressColor = getResources().getColor(R.color.colorGreen, null);
            //Get default progress background color
            int defaultBackColor = getResources().getColor(R.color.colorGray, null);
            //Get custom progress background color
            int progressBackColor = attr.getColor(R.styleable.ProgressButton_progressBackColor, defaultBackColor);
            //Set progress background drawable color
            mDrawableProgressBackground.setColor(progressBackColor);
            //Get custom progress color
            int progressColor = attr.getColor(R.styleable.ProgressButton_progressColor, defaultProgressColor);
            //Set progress drawable color
            mDrawableProgress.setColor(progressColor);

            //Get default progress
            mProgress = attr.getInteger(R.styleable.ProgressButton_progress, mProgress);
            //Get max progress
            mMaxProgress = attr.getInteger(R.styleable.ProgressButton_maxProgress, mMaxProgress);

        } finally {
            attr.recycle();
        }

        //Set corner radius
        mDrawableButton.setCornerRadius(mCornerRadius);
        mDrawableProgressBackground.setCornerRadius(mCornerRadius);
        mDrawableProgress.setCornerRadius(mCornerRadius - mProgressMargin);
        setBackgroundDrawable(mDrawableButton);

        mFinish = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mProgress > mMinProgress && mProgress <= mMaxProgress && !mFinish) {
            float progressWidth =
                    (float) getMeasuredWidth() * ((float) mProgress / (float) mMaxProgress);

            if (progressWidth < mCornerRadius * 2) {
                progressWidth = mCornerRadius * 2;
            }

            mDrawableProgress.setBounds((int) mProgressMargin, (int) mProgressMargin,
                    (int) (progressWidth - mProgressMargin), getMeasuredHeight() - (int) mProgressMargin);

            mDrawableProgress.draw(canvas);

            if (mProgress == mMaxProgress) {
                setBackgroundDrawable(mDrawableButton);
                mFinish = true;
            }
        }
        super.onDraw(canvas);
    }

    public void setProgress(int progress) {
        if (!mFinish) {
            mProgress = progress;
            setBackgroundDrawable(mDrawableProgressBackground);
            invalidate();
        }
    }

    public void setMaxProgress(int maxProgress) {
        mMaxProgress = maxProgress;
    }

    public void reset() {
        mFinish = false;
        mProgress = mMinProgress;
    }
}

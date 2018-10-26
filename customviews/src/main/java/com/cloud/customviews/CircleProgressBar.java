package com.cloud.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class CircleProgressBar extends AppCompatTextView {

    private Paint mProgressPaint;
    private Paint mProgressBackPaint;

    private float mProgressWidth;

    private int mProgressColor;
    private int mProgressBackColor;

    private int mProgress = 0;
    private int mMaxProgress = 100;
    private int mMinProgress = 0;

    private RectF mBackArea;
    private RectF mProgressArea;

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeAttribute(context, attrs);
    }

    private void initializeAttribute(Context context, AttributeSet attrs) {
        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);

        mProgressColor = getResources().getColor(R.color.colorGreen, null);
        mProgressBackColor = getResources().getColor(R.color.colorGray, null);

        try {
            mProgressColor = attr.getColor(R.styleable.CircleProgressBar_progressColor, mProgressColor);
            mProgressBackColor = attr.getColor(R.styleable.CircleProgressBar_progressBackColor, mProgressBackColor);
            mProgressWidth = attr.getDimension(R.styleable.CircleProgressBar_progressWidth, mProgressWidth);

            mMinProgress = attr.getInteger(R.styleable.CircleProgressBar_minProgress, mMinProgress);
            mMaxProgress = attr.getInteger(R.styleable.CircleProgressBar_maxProgress, mMaxProgress);
            mProgress = attr.getInteger(R.styleable.CircleProgressBar_progress, mProgress);
        } finally {
            attr.recycle();
        }

        initializePaint();

        mBackArea = new RectF();
        mProgressArea = new RectF();
    }

    private void initializePaint() {
        mProgressPaint = new Paint();
        mProgressBackPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressBackPaint.setAntiAlias(true);
        mProgressPaint.setColor(mProgressColor);
        mProgressBackPaint.setColor(mProgressBackColor);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressBackPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeWidth(mProgressWidth);
        mProgressBackPaint.setStrokeWidth(mProgressWidth);
    }

    private void initializeProgressArea() {
        int width = getMeasuredWidth(), height = getMeasuredHeight();
        //Get the radius of circle;
        int radius = width < height ? width / 2 : height / 2;
        radius -= mProgressWidth / 2;

        int centerX = width / 2;
        int centerY = height / 2;

        mBackArea.left = mProgressArea.left = centerX - radius;
        mBackArea.top = mProgressArea.top = centerY - radius;
        mBackArea.right = mProgressArea.right = radius * 2 + (centerX - radius);
        mBackArea.bottom = mProgressArea.bottom = radius * 2 + (centerY - radius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initializeProgressArea();

        //Draw progress background
        canvas.drawArc(mBackArea, 0, 360, false, mProgressBackPaint);

        if (mProgress > mMinProgress && mProgress <= mMaxProgress) {
            float progressAngle = ((float) mProgress / (mMaxProgress - mMinProgress)) * 360;
            //Draw current progress
            canvas.drawArc(mProgressArea, -90, progressAngle, false, mProgressPaint);
        }

        super.onDraw(canvas);
    }

    /**
     * Set current progress
     */
    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }
}
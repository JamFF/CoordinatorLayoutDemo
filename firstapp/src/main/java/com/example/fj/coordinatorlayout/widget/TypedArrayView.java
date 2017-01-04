package com.example.fj.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

import com.example.fj.coordinatorlayout.R;

/**
 * 描述：
 * 作者：傅健
 * 创建时间：2017/1/4 19:46
 */

public class TypedArrayView extends android.view.View {

    private static final String TAG = "TypedArrayView";

    /**
     * 文本
     */
    private String mText;
    /**
     * 文本的颜色
     */
    private int mTextColor;
    /**
     * 文本的大小
     */
    private int mTextSize;

    private Paint mPaint;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;

    public TypedArrayView(Context context) {
        this(context, null);
    }

    // 默认情况下，系统调用的是这个构造函数
    public TypedArrayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

        // 获取TypedArray对象
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyCustomerView, 0, 0);

        try {
            mText = typedArray.getString(R.styleable.MyCustomerView_text);
            mTextColor = typedArray.getColor(R.styleable.MyCustomerView_textColor, Color.BLACK);
            mTextSize = typedArray.getDimensionPixelSize(R.styleable.MyCustomerView_textSize,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        } finally {
            // 一定要recycle
            typedArray.recycle();
        }

        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);

        mBound = new Rect();
        Log.d(TAG, "TypedArrayView: " + mText.length());
        mPaint.getTextBounds(mText, 0, mText.length(), mBound);
    }

    public TypedArrayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure: ");
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpec = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpec = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //宽度
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSpec;
        } else {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mBound);
            float _TextWidth = mBound.width();
            width = (int) (getPaddingLeft() + _TextWidth + getPaddingRight());
        }
        //高度
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSpec;
        } else {
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(mText, 0, mText.length(), mBound);
            float textHeight = mBound.height();
            height = (int) (getPaddingTop() + textHeight + getPaddingBottom());
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw: ");
        mPaint.setColor(Color.YELLOW);
        Log.d(TAG, "getMeasuredWidth():" + getMeasuredWidth() + "   " + getMeasuredHeight());
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTextColor);
        Log.d(TAG, "getWidth():" + getWidth() + "   " + getHeight());
        Log.d(TAG, "mBound.width():" + mBound.width() + "   " + mBound.height());
        canvas.drawText(mText, getWidth() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }
}

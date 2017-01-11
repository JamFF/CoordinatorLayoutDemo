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
     * 文本的颜色，背景颜色
     */
    private int mTextColor, mTextBackground;
    /**
     * 文本的大小
     */
    private int mTextSize;

    private Paint mPaint;

    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;

    // 代码创建TypedArrayView控件
    public TypedArrayView(Context context) {
        this(context, null);
    }

    // 布局文件中创建
    public TypedArrayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TypedArrayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 获取TypedArray对象
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyCustomerView, 0, 0);

        try {
            // 获取布局文件中自定义属性的值
            mText = typedArray.getString(R.styleable.MyCustomerView_text);
            mTextColor = typedArray.getColor(R.styleable.MyCustomerView_textColor, Color.BLACK);
            mTextSize = typedArray.getDimensionPixelSize(R.styleable.MyCustomerView_textSize,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
            mTextBackground = typedArray.getColor(R.styleable.MyCustomerView_textBackground, Color.GRAY);
        } finally {
            // 一定要recycle
            typedArray.recycle();
        }

        mPaint = new Paint();
        mBound = new Rect();
        Log.d(TAG, "TypedArrayView中text的length: " + mText.length());
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

        // 宽度
        if (widthMode == MeasureSpec.EXACTLY) {

            // MeasureSpec.EXACTLY是精确尺寸，当我们将控件的layout_width或layout_height指定为具体数值时如android:layout_width="50dip"，
            // 或者为MATCH_PARENT是，都是控件大小已经确定的情况，都是精确尺寸。

            // MeasureSpec.AT_MOST是最大尺寸，当控件的layout_width或layout_height指定为WRAP_CONTENT时，控件大小一般随着控件的子空间或内容进行变化，
            // 此时控件尺寸只要不超过父控件允许的最大尺寸即可。因此，此时的mode是AT_MOST，size给出了父控件允许的最大尺寸。

            // MeasureSpec.UNSPECIFIED是未指定尺寸，这种情况不多，一般都是父控件是AdapterView，通过measure方法传入的模式。

            width = widthSpec;
        } else {
            // 测量文字宽度，这种方式有误差
            /*mPaint.getTextBounds(mText, 0, mText.length(), mBound);
            float _TextWidth = mBound.width();*/

            // 无误差，但只能测量宽度
            float _TextWidth = mPaint.measureText(mText);
            width = (int) (getPaddingLeft() + _TextWidth + getPaddingRight());
        }

        // 高度
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSpec;
        } else {
            mPaint.getTextBounds(mText, 0, mText.length(), mBound);
            float textHeight = mBound.height();
            height = (int) (getPaddingTop() + textHeight + getPaddingBottom());
        }

        // 覆写super.onMeasure中的setMeasuredDimension方法
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw: ");

        mPaint.setColor(mTextBackground);
        Log.d(TAG, "getMeasuredWidth():" + getMeasuredWidth() + "   getMeasuredHeight():" + getMeasuredHeight());
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);


        // 文字大小
        mPaint.setTextSize(mTextSize);
        // 文字居中
        mPaint.setTextAlign(Paint.Align.CENTER);
        // 文字颜色
        mPaint.setColor(mTextColor);
        Log.d(TAG, "getWidth():" + getWidth() + "   getHeight():" + getHeight());
        Log.d(TAG, "mBound.width():" + mBound.width() + "   mBound.height():" + mBound.height());
        // 由于文字Paint.Align.CENTER，使用getWidth() / 2，如果使用Paint.Align.LEFT，使用getWidth() / 2 - mBound.width() / 2
        canvas.drawText(mText, getWidth() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }
}

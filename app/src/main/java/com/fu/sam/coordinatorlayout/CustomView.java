package com.fu.sam.coordinatorlayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 描述：
 * 作者：sam.fu
 * 创建时间：2016/11/28 14:51
 */

public class CustomView extends View {

    private int lastX;
    private int lastY;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                break;

            case MotionEvent.ACTION_MOVE:

                if (getLayoutParams() instanceof CoordinatorLayout.MarginLayoutParams) {

                    CoordinatorLayout.MarginLayoutParams marginParams = (CoordinatorLayout.MarginLayoutParams) getLayoutParams();
                    marginParams.leftMargin = marginParams.leftMargin + x - lastX;
                    marginParams.topMargin = marginParams.topMargin + y - lastY;
                    setLayoutParams(marginParams);
                    // 如果不setLayoutParams，可以使用requestLayout()重新Measure刷新，但是invalidate()不可以
                }

                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        lastX = x;
        lastY = y;

        return true;
    }
}

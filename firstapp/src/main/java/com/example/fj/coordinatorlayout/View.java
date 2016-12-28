package com.example.fj.coordinatorlayout;


import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 描述：
 * 作者：傅健
 * 创建时间：2016/12/28 17:43
 */

public class View extends android.view.View {

    private int y, lastY;

    public View(Context context) {
        this(context, null);
    }

    public View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                lastY = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                y = (int) event.getRawY();

                // 代码中获取margin的LayoutParams，也可以使用CoordinatorLayout.MarginLayoutParams
                if (getLayoutParams() instanceof CoordinatorLayout.LayoutParams) {
                    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) getLayoutParams();
                    params.topMargin = params.topMargin + y - lastY;
                    setLayoutParams(params);
                }

                lastY = y;
                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
                break;
        }

        return true;
    }
}

package com.example.fj.coordinatorlayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * 描述：
 * 作者：sam.fu
 * 创建时间：2016/11/29 23:01
 */

public class MyView extends TextView {

    private static final String TAG = "MyView";

    int x, y, lastX, lastY;

    /**
     * 代码创建的构造方法
     */
    public MyView(Context context) {
        this(context, null);
    }

    /**
     * xml布局创建的构造方法
     */
    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                x = lastX = (int) event.getRawX();
                y = lastY = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:

                x = (int) event.getRawX();
                y = (int) event.getRawY();

                // 代码中获取margin的LayoutParams，也可以使用CoordinatorLayout.MarginLayoutParams
                if (getLayoutParams() instanceof CoordinatorLayout.LayoutParams) {
                    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) getLayoutParams();
                    params.leftMargin = params.leftMargin + x - lastX;
                    params.topMargin = params.topMargin + y - lastY;
                    setLayoutParams(params);
                }
                lastX = x;
                lastY = y;
                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
                break;
        }

        // 消费事件
        return true;
    }
}

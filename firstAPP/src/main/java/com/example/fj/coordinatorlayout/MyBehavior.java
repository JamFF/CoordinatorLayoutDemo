package com.example.fj.coordinatorlayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

/**
 * 描述：
 * 作者：sam.fu
 * 创建时间：2016/11/30 0:10
 */

public class MyBehavior extends CoordinatorLayout.Behavior<TextView> {

    private int screenWidth;

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;

    }

    /**
     * 判断child的布局是否依赖dependency
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        //如果dependency是MyView的实例，说明它就是我们所需要的Dependency
        return dependency instanceof MyView;
    }

    /**
     * 当dependency发生改变时（位置、宽高等），执行这个函数
     * 返回true表示child的位置或者是宽高要发生改变，否则就返回false
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {

        //根据dependency的位置，设置TextView的位置
        int left = dependency.getLeft();
        int top = dependency.getTop();

        int x = screenWidth - left - child.getWidth();
        int y = top;

        setPosition(child, x, y);
        return true;
    }

    private void setPosition(View child, int x, int y) {

        if (child.getLayoutParams() instanceof CoordinatorLayout.LayoutParams) {

            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            params.leftMargin = x;
            params.topMargin = y;
            child.setLayoutParams(params);

        }
    }
}

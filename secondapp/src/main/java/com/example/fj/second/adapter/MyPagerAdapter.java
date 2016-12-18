package com.example.fj.second.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.LinearLayout;

import com.example.fj.second.fragment.ItemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 作者：sam.fu
 * 创建时间：2016/12/6 17:55
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    private List<String> mList = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyPagerAdapter(FragmentManager fm, List<String> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        // 创建多个ItemFragment实例

        switch (position) {

            case 0:
                return ItemFragment.newInstance(1, LinearLayout.VERTICAL, false, false);

            case 1:
                return ItemFragment.newInstance(1, LinearLayout.VERTICAL, true, false);

            case 2:
                return ItemFragment.newInstance(1, LinearLayout.HORIZONTAL, false, false);

            case 3:
                return ItemFragment.newInstance(1, LinearLayout.HORIZONTAL, true, false);

            case 4:
                return ItemFragment.newInstance(3, LinearLayout.VERTICAL, false, false);

            case 5:
                return ItemFragment.newInstance(3, LinearLayout.VERTICAL, true, false);

            case 6:
                return ItemFragment.newInstance(3, LinearLayout.HORIZONTAL, false, false);

            case 7:
                return ItemFragment.newInstance(3, LinearLayout.HORIZONTAL, true, false);

            case 8:
                return ItemFragment.newInstance(3, LinearLayout.VERTICAL, false, true);

            case 9:
                return ItemFragment.newInstance(3, LinearLayout.VERTICAL, true, true);

            case 10:
                return ItemFragment.newInstance(3, LinearLayout.HORIZONTAL, false, true);

            case 11:
                return ItemFragment.newInstance(3, LinearLayout.HORIZONTAL, true, true);

            default:
                break;
        }
        // 替他默认返回
        return ItemFragment.newInstance(1, LinearLayout.VERTICAL, false, false);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    /**
     * 重写与TabLayout配合
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position);
    }
}

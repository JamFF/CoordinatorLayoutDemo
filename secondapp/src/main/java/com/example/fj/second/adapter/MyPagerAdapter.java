package com.example.fj.second.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
        return ItemFragment.newInstance(0);
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

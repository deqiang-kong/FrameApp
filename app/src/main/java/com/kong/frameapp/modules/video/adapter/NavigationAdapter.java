package com.kong.frameapp.modules.video.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.kong.fmklibrary.controls.CacheFragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * fragment列表适配器
 * Created by kongdq on 17/11/3.
 */

public class NavigationAdapter extends CacheFragmentStatePagerAdapter {
    private ArrayList<String> labels;
    private int mScrollY;
    private ArrayList<Fragment> mFragmentList;

    public NavigationAdapter(FragmentManager fm, ArrayList<String> nameArray, ArrayList<Fragment> fragmentList) {
        super(fm);
        labels = nameArray;
        mFragmentList = fragmentList;
    }

    public void setScrollY(int scrollY) {
        mScrollY = scrollY;
    }

    @Override
    protected Fragment createItem(int position) {
        Fragment fragment = mFragmentList.get(position);

        return fragment;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return (labels != null) ? labels.get(position) : "";
    }
}


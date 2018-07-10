package com.example.wangluo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.wangluo.Fragment.ReferListFragment;
import com.example.wangluo.Fragment.ReferListFragment;

import java.util.List;

/**
 * Created by ASUS on 2018/7/10.
 */

public class ReferTabFragmentAdapter extends FragmentPagerAdapter {
    private final List<ReferListFragment> mReferFragmentList;
    private final List<String> mTabTitle;


    public ReferTabFragmentAdapter(FragmentManager fm, List<ReferListFragment> fragmentList, List<String> tabTitle) {

        super(fm);

        this.mReferFragmentList= fragmentList;

        this.mTabTitle = tabTitle;

        // mReferFragmentList = null;
    }


    @Override

    public Fragment getItem(int position) {

        return mReferFragmentList.get(position);

    }


    @Override

    public int getCount() {

        return mReferFragmentList.size();

    }


    @Override

    public CharSequence getPageTitle(int position) {

        return mTabTitle.get(position % mTabTitle.size());

    }


    @Override

    public void destroyItem(ViewGroup container, int position, Object object) {

        super.destroyItem(container, position, object);

    }

}

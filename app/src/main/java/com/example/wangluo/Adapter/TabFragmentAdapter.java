package com.example.wangluo.Adapter;

/**
 * Created by ASUS on 2018/7/5.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.wangluo.Fragment.RankListFragment;

import java.util.List;


public class TabFragmentAdapter extends FragmentPagerAdapter {

    private final List<RankListFragment> mFragmentList;

    private final List<String> mTabTitle;



    public TabFragmentAdapter(FragmentManager fm, List<RankListFragment> fragmentList, List<String> tabTitle) {

        super(fm);

        this.mFragmentList=fragmentList;

        this.mTabTitle=tabTitle;

    }



    @Override

    public Fragment getItem(int position) {

        return mFragmentList.get(position);

    }



    @Override

    public int getCount() {

        return mFragmentList.size();

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
package com.example.wangluo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.wangluo.Fragment.RankListFragment;
import com.example.wangluo.Fragment.TrackListFragment;

import java.util.List;

/**
 * Created by ASUS on 2018/7/9.
 */

public class TrackTabFragmentAdapter extends FragmentPagerAdapter {

    private final List<TrackListFragment> mTrackFragmentList;
    private final List<String> mTabTitle;


    public TrackTabFragmentAdapter(FragmentManager fm, List<TrackListFragment> fragmentList, List<String> tabTitle) {

        super(fm);

        this.mTrackFragmentList= fragmentList;

        this.mTabTitle = tabTitle;

        // mTrackFragmentList = null;
    }


    @Override

    public Fragment getItem(int position) {

        return mTrackFragmentList.get(position);

    }


    @Override

    public int getCount() {

        return mTrackFragmentList.size();

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

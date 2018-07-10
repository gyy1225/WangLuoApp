package com.example.wangluo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangluo.Activity.AddActivity;
import com.example.wangluo.Adapter.TabFragmentAdapter;
import com.example.wangluo.Adapter.TrackTabFragmentAdapter;
import com.example.wangluo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class TrackFragment extends Fragment {

    private List<TrackListFragment> mTrackList = new ArrayList<>();
    private View view;
    private TabLayout tab_track;
    private ViewPager vp_track;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_track_list, container, false);
        tab_track = view.findViewById(R.id.tab_track);
        vp_track = view.findViewById(R.id.vp_track);
        vp_track.setCurrentItem(0);
        vp_track.setOffscreenPageLimit(3);

        initViewPager(view);
        return view;
    }

    private void initViewPager(View view) {
        List<String> titles = new ArrayList<>();
        titles.add("微博博主 ");
        titles.add("知乎答主 ");
        titles.add("腾讯视频 ");
        titles.add("腾讯漫画");
        titles.add("起点小说");
        List<TrackListFragment> fragments = new ArrayList<>();
        //初始化它
        for (int i = 0; i <titles.size(); i++) {
            tab_track.addTab(tab_track.newTab().setText(titles.get(i)));
            Bundle bundle = new Bundle();
            bundle.putInt("position", i);
            TrackListFragment fragment = new TrackListFragment();
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        vp_track.setAdapter(new TrackTabFragmentAdapter(getChildFragmentManager(), fragments, titles));
        tab_track.setupWithViewPager(vp_track);

    }

}

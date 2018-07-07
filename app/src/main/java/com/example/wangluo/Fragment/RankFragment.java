package com.example.wangluo.Fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangluo.Activity.MainActivity;
import com.example.wangluo.Adapter.MyRankRecyclerViewAdapter;
import com.example.wangluo.Adapter.TabFragmentAdapter;
import com.example.wangluo.Class.Content;
import com.example.wangluo.R;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class RankFragment extends Fragment {

    private List<Content> rankList = new ArrayList<>();
    private View view;
    private TabLayout tab_rank;
    private ViewPager vp_rank;
    private RecyclerView recyclerView;
    private List<Content> rankContentList = new ArrayList<>();
    private int position=0;
    private MyRankRecyclerViewAdapter myRankRecyclerViewAdapter;
    private List<RankListFragment> fragmentsList = new ArrayList<>();
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;

    public RankFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RankFragment newInstance(int columnCount) {
        RankFragment fragment = new RankFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rank_list, container, false);
        ViewGroup parent = (ViewGroup) view.getParent();
        tab_rank = (TabLayout) view.findViewById(R.id.tab_rank);
        vp_rank = (ViewPager) view.findViewById(R.id.vp_rank);
        vp_rank.setCurrentItem(0);
        vp_rank.setOffscreenPageLimit(4);
        initRecyclerView(view);

       vp_rank.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @Override
           public void onPageSelected(int position) {
           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });
        initViewPager(view);
        return view;
    }


    private void initViewPager(View parentView) {

        //设置它的名字
        List<String> titles = new ArrayList<>();
        titles.add("互联网");
        titles.add("体育");
        titles.add("社会");
        titles.add("音乐");
        titles.add("电影");
        titles.add("微博");
        titles.add("其他");
        //初始化ViewPager的数据集
        List<RankListFragment> fragments = new ArrayList<>();
        //初始化它
        for (int i = 0; i < 7; i++) {
            tab_rank.addTab(tab_rank.newTab().setText(titles.get(i)));
            Bundle bundle = new Bundle();
            bundle.putInt("position", i);
            RankListFragment fragment=new RankListFragment();
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        vp_rank.setAdapter(new TabFragmentAdapter(getChildFragmentManager(), fragments, titles));
        tab_rank.setupWithViewPager(vp_rank);




    }

   private void initRecyclerView(View viewContent) {
        RecyclerView recyclerView = (RecyclerView) viewContent.findViewById(R.id.rv_rank_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
       myRankRecyclerViewAdapter=new MyRankRecyclerViewAdapter(rankContentList);
       recyclerView.setAdapter(myRankRecyclerViewAdapter);
       recyclerView.getAdapter().notifyDataSetChanged();



   }

    //private ViewPager.OnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_rank));

   /* private void replaceFragment(Fragment fragment){
        Fragment fragment1=getTargetFragment();
        fragment1.getChildFragmentManager().beginTransaction().replace(R.id.vp_rank,fragment).commit(); }


*/
    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {

        @Override

        public void onTabSelected(TabLayout.Tab tab) {

            int mTag = (int) tab.getTag();
            switch (mTag) {
                case 3:
                    Content content1 = new Content();
                    content1.setId("1");
                    content1.setTitle("往后余生");
                    content1.setAuthor("马良");
                    rankContentList.add(content1);
                    break;
                default:
            }
            MyRankRecyclerViewAdapter myRankRecyclerViewAdapter=new MyRankRecyclerViewAdapter(rankContentList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myRankRecyclerViewAdapter);
            recyclerView.getAdapter().notifyDataSetChanged();

        }


        @Override

        public void onTabUnselected(TabLayout.Tab tab) {


        }


        @Override

        public void onTabReselected(TabLayout.Tab tab) {


        }

        public List<Content> initList() {
            Content content1 = new Content();
            content1.setId("1");
            content1.setTitle("往后余生");
            content1.setAuthor("马良");
            rankContentList.add(content1);
            return rankContentList;
        }

    };
}

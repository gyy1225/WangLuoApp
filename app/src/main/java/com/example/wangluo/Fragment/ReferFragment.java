package com.example.wangluo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wangluo.Adapter.MyReferRecyclerViewAdapter;
import com.example.wangluo.Adapter.ReferTabFragmentAdapter;
import com.example.wangluo.Class.Content;
import com.example.wangluo.R;
import com.example.wangluo.dummy.DummyContent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class ReferFragment extends Fragment {

    private List<Content> mReferList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ViewPager vp_refer;
    private TabLayout tab_refer;
    private Context mContext;

    public ReferFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    MyReferRecyclerViewAdapter myReferRecyclerViewAdapter = new MyReferRecyclerViewAdapter(mContext,mReferList);
                    recyclerView.setAdapter(myReferRecyclerViewAdapter);
                    break;
                default:

            }
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refer_list, container, false);
        mContext = getActivity();
        vp_refer = view.findViewById(R.id.vp_refer);
        tab_refer = view.findViewById(R.id.tab_refer);
        initViewPager(view);
        return view;

    }

    private void initViewPager(View view) {
        List<String> titles = new ArrayList<>();
        titles.add("科技");
        titles.add("电影");
        titles.add("读书");
        titles.add("娱乐");
        titles.add("生活");
        titles.add("学业");
        titles.add("体育");
        titles.add("游戏");
        titles.add("动漫");
        titles.add("邮箱");
        titles.add("华科");
        titles.add("自定义");
        List<ReferListFragment> fragments = new ArrayList<>();
        //初始化它
        for (int i = 0; i < titles.size(); i++) {
            tab_refer.addTab(tab_refer.newTab().setText(titles.get(i)));
            Bundle bundle = new Bundle();
            bundle.putInt("position", i);
            ReferListFragment fragment = new ReferListFragment();
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        vp_refer.setAdapter(new ReferTabFragmentAdapter(getChildFragmentManager(), fragments, titles));
        tab_refer.setupWithViewPager(vp_refer);

    }

}





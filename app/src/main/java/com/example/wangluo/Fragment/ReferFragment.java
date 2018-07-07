package com.example.wangluo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangluo.Adapter.MyReferRecyclerViewAdapter;
import com.example.wangluo.Class.Content;
import com.example.wangluo.R;
import com.example.wangluo.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class ReferFragment extends Fragment {

    private List<Content> mReferList=new ArrayList<>();
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ReferFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refer_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.refer_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        mReferList=initRecyclerView();
        MyReferRecyclerViewAdapter myReferRecyclerViewAdapter=new MyReferRecyclerViewAdapter(mReferList);
        recyclerView.setAdapter(myReferRecyclerViewAdapter);

        return view;

    }
private List<Content> initRecyclerView(){
       Content content1=new Content();
       content1.setTitle("我不是药神");
       content1.setResource("微博");
       content1.setContent("电影我不是药神票房口碑爆棚");
       mReferList.add(content1);
        return mReferList;
}

}

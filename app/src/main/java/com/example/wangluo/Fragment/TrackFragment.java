package com.example.wangluo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangluo.Adapter.MyReferRecyclerViewAdapter;
import com.example.wangluo.Adapter.MyTrackRecyclerViewAdapter;
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
public class TrackFragment extends Fragment {

   private List<Content> mTrackList=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.track_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mTrackList=initRecyclerView();
        MyTrackRecyclerViewAdapter myTrackRecyclerViewAdapter=new MyTrackRecyclerViewAdapter(mTrackList);
        recyclerView.setAdapter(myTrackRecyclerViewAdapter);
        return view;
    }
private List<Content> initRecyclerView(){
        Content content1=new Content();
        content1.setId("微博博主");
        content1.setContent("最近更新内容");
        mTrackList.add(content1);
        return mTrackList;
}

}

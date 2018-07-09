package com.example.wangluo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangluo.Class.Content;
import com.example.wangluo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/7/9.
 */

public class TrackListFragment extends Fragment {
    private View viewContent;
    private Context mContext;
    private RecyclerView recyclerView;
    private int mPosition;
    private List<Content>trackContentList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.track_list_content, container, false);
        this.mContext = getActivity();
        recyclerView = (RecyclerView) viewContent.findViewById(R.id.rv_track_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mPosition = getArguments().getInt("position");
        initList(mPosition);
        return viewContent;
    }
    public List<Content> initList(int mPosition) {
        switch (mPosition) {
            case 0:

        }
    return trackContentList;
    }
}

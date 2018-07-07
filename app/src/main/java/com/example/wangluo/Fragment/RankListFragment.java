package com.example.wangluo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangluo.Adapter.MyRankRecyclerViewAdapter;
import com.example.wangluo.Class.Content;
import com.example.wangluo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/7/5.
 */

public class RankListFragment extends Fragment {
        private View viewContent;
        private String mTitle;
        private int mPosition;
        private List<Content> rankContentList=new ArrayList<>();

        public void setTitle(String mTitle) {
            this.mTitle = mTitle;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            viewContent = inflater.inflate(R.layout.rank_list_content,container,false);
            RecyclerView recyclerView = (RecyclerView) viewContent.findViewById(R.id.rv_rank_list);
            LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            mPosition=getArguments().getInt("position");

            initList(mPosition);
            MyRankRecyclerViewAdapter myRankRecyclerViewAdapter=new MyRankRecyclerViewAdapter(rankContentList);
            recyclerView.setAdapter(myRankRecyclerViewAdapter);

            return viewContent;
        }
        public List<Content> initList(int mPosition){
            switch (mPosition) {
                case 0:
                    Content content1 = new Content();
                    content1.setId("1");
                    content1.setTitle("往后余生");
                    content1.setAuthor("马良");
                  //  if (rankContentList==null)
                    rankContentList.add(content1);
                    break;
                default:
            }
            return  rankContentList;
        }

    }



package com.example.wangluo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        private ImageView imageView;
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

            imageView=(ImageView)viewContent.findViewById(R.id.rank_image) ;
            initList(mPosition);
            MyRankRecyclerViewAdapter myRankRecyclerViewAdapter=new MyRankRecyclerViewAdapter(rankContentList);
            recyclerView.setAdapter(myRankRecyclerViewAdapter);

            return viewContent;
        }
        public List<Content> initList(int mPosition){
            switch (mPosition) {
                case 0:
                    imageView.setImageResource(R.drawable.csdn3);

                    break;
                case 1:
                    imageView.setImageResource(R.drawable.tiyu2);

                    break;
                case 2:
                    imageView.setImageResource(R.drawable.redian3);

                    break;
                case 3:
                    Content content1 = new Content();
                    content1.setId("1");
                    imageView.setImageResource(R.drawable.wangyiyun2);
                    content1.setTitle("往后余生");
                    content1.setAuthor("马良");
                  //  if (rankContentList==null)
                    rankContentList.add(content1);
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.dianying2);

                    break;
                case 5:
                    imageView.setImageResource(R.drawable.weibo3);

                    break;
                case 6:
                    imageView.setImageResource(R.drawable.junshi2);

                    break;
                case 7:
                    imageView.setImageResource(R.drawable.tieba3);

                    break;

                default:
            }
            return  rankContentList;
        }

    }



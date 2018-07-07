package com.example.wangluo.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangluo.Class.Content;
import com.example.wangluo.R;

import java.util.List;

/**
 * Created by ASUS on 2018/7/4.
 */

public class MyRankRecyclerViewAdapter extends RecyclerView.Adapter<MyRankRecyclerViewAdapter.ViewHolder> {
    private List<Content> mContentList;
    private int mTag;

class ViewHolder extends RecyclerView.ViewHolder{
    TextView tv_grade;
    TextView tv_title;
    TextView tv_author;

    public ViewHolder(View itemView) {
        super(itemView);

        tv_grade=(TextView)itemView.findViewById(R.id.rank_grade);
        tv_title=(TextView)itemView.findViewById(R.id.rank_title);
        tv_author=(TextView)itemView.findViewById(R.id.rank_author);
    }
}
public MyRankRecyclerViewAdapter(List<Content> contentList){
    mContentList=contentList;

}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_rank_item,parent,false);
    final ViewHolder holder=new ViewHolder(view);
    view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    Content content=mContentList.get(position);
    holder.tv_grade.setText(content.getId());
    holder.tv_title.setText(content.getTitle());
    holder.tv_author.setText(content.getAuthor());
    }

    @Override
    public int getItemCount() {
        return mContentList.size();
        //return 0;
    }
}

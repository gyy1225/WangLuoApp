package com.example.wangluo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangluo.Activity.ContentActivity;
import com.example.wangluo.Class.Content;
import com.example.wangluo.R;

import java.util.List;

/**
 * Created by ASUS on 2018/7/4.
 */

public class MyRankRecyclerViewAdapter extends RecyclerView.Adapter<MyRankRecyclerViewAdapter.ViewHolder> {
    private List<Content> mContentList;
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_grade;
        TextView tv_title;
        TextView tv_author;
        TextView tv_resource;
        View rankView;

        public ViewHolder(View itemView) {
            super(itemView);

            rankView = itemView;
            tv_grade = (TextView) itemView.findViewById(R.id.rank_grade);
            tv_title = (TextView) itemView.findViewById(R.id.rank_title);
            tv_author = (TextView) itemView.findViewById(R.id.rank_author);
            tv_resource = (TextView) itemView.findViewById(R.id.rank_resource);
        }
    }

    public MyRankRecyclerViewAdapter(Context context,List<Content> contentList) {
        this.context = context;
        mContentList = contentList;

    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_rank_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position=holder.getAdapterPosition();
                Content content=mContentList.get(position);
                String contenturl=content.getContentURL();
                Intent intent=new Intent(context, ContentActivity.class);
                intent.putExtra("URL",contenturl);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        Content content = mContentList.get(position);
        holder.tv_grade.setText(content.getGrade());
        holder.tv_title.setText(content.getTitle());
        holder.tv_author.setText(content.getAuthor());
        holder.tv_resource.setText(content.getResource());
    }

    @Override
    public int getItemCount() {
        return mContentList.size();
        //return 0;
    }
}

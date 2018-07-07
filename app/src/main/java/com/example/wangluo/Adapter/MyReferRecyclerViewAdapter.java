package com.example.wangluo.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangluo.Class.Content;
import com.example.wangluo.R;

import java.util.List;

/**
 * Created by ASUS on 2018/7/4.
 */

public class MyReferRecyclerViewAdapter extends RecyclerView.Adapter<MyReferRecyclerViewAdapter.ViewHolder> {
    private List<Content> mReferList;
    public MyReferRecyclerViewAdapter(List<Content>mReferList){
        this.mReferList=mReferList;
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView referImage;
        TextView referTitle;
        TextView referContent;
        TextView referResource;


        public ViewHolder(View itemView) {
            super(itemView);
            referImage=(ImageView)itemView.findViewById(R.id.refer_image);
            referContent=(TextView)itemView.findViewById(R.id.refer_content);
            referTitle=(TextView)itemView.findViewById(R.id.refer_title);
            referResource=(TextView)itemView.findViewById(R.id.refer_resource);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_refer_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Content content=mReferList.get(position);
        holder.referImage.setImageResource(content.getImageID());
        holder.referTitle.setText(content.getTitle());
        holder.referContent.setText(content.getContent());
        holder.referResource.setText(content.getResource());
    }


    @Override
    public int getItemCount() {
        return mReferList.size();
    }
}

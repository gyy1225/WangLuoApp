package com.example.wangluo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wangluo.Activity.ContentActivity;
import com.example.wangluo.Class.Content;
import com.example.wangluo.R;

import java.util.List;

/**
 * Created by ASUS on 2018/7/4.
 */

public class MyReferRecyclerViewAdapter extends RecyclerView.Adapter<MyReferRecyclerViewAdapter.ViewHolder> {
    private List<Content> mReferList;
private Context mContext;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public MyReferRecyclerViewAdapter(Context context,List<Content> mReferList) {
        this.mContext=context;
        this.mReferList = mReferList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView referImage;
        TextView referTitle;



        public ViewHolder(View itemView) {
            super(itemView);
            referImage = (ImageView) itemView.findViewById(R.id.refer_image);
            referTitle = (TextView) itemView.findViewById(R.id.refer_title);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_refer_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Content content = mReferList.get(position);
                String contenturl = content.getContentURL();
                Intent intent = new Intent(mContext, ContentActivity.class);
                intent.putExtra("URL", contenturl);
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Content content = mReferList.get(position);
        //Glide.with(mContext).load(content.getImageID2()).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.referImage);
        holder.referTitle.setText(content.getTitle());
        holder.referImage.setImageResource(content.getImageID2());
    }


    @Override
    public int getItemCount() {
        return mReferList.size();
    }
}

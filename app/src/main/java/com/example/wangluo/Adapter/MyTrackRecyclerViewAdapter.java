package com.example.wangluo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wangluo.Class.Content;
import com.example.wangluo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/7/4.
 */

public class MyTrackRecyclerViewAdapter extends RecyclerView.Adapter<MyTrackRecyclerViewAdapter.ViewHolder> {

    private List<Content> mTrackList = new ArrayList<>();
    private Context mContext;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public MyTrackRecyclerViewAdapter(Context context,List<Content> mTrackList) {
        this.mContext=context;
        this.mTrackList = mTrackList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView trackImage;
        TextView trackAuthor;
        TextView trackTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            trackImage = (ImageView) itemView.findViewById(R.id.track_image);
            trackAuthor = (TextView) itemView.findViewById(R.id.tarck_id);
            trackTitle = (TextView) itemView.findViewById(R.id.track_title);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_track_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Content content = mTrackList.get(position);
        Glide.with(mContext).load(content.getImageID()).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.trackImage);
        holder.trackAuthor.setText(content.getAuthor());
        holder.trackTitle.setText(content.getTitle());
    }

    @Override
    public int getItemCount() {
        return mTrackList.size();
    }
}

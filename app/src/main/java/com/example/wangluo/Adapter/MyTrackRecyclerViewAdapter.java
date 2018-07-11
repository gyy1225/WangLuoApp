package com.example.wangluo.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wangluo.Activity.ContentActivity;
import com.example.wangluo.Class.Content;
import com.example.wangluo.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    public MyTrackRecyclerViewAdapter(Context context, List<Content> mTrackList) {
        this.mContext = context;
        this.mTrackList = mTrackList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView trackImage;
        TextView trackAuthor;
        TextView trackTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            trackImage = (ImageView) itemView.findViewById(R.id.track_image);
            trackAuthor = (TextView) itemView.findViewById(R.id.track_id);
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
                int position = holder.getAdapterPosition();
                Content content = mTrackList.get(position);
                String contenturl = content.getContentURL();
                Intent intent = new Intent(mContext, ContentActivity.class);
                intent.putExtra("URL", contenturl);
                mContext.startActivity(intent);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                int position = holder.getAdapterPosition();
                Content content = mTrackList.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                final String UID=content.getUID();
                final String resource=content.getResource();
                dialog.setTitle("确认不再追踪吗？");
                dialog.setMessage("删除:" + content.getAuthor());
                dialog.setCancelable(true);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (resource){
                            case "新浪微博":
                                sendOkhhtpRequest("http://haojie06.me:9999/delete?weibo,id=3,uid="+UID);
                                break;
                            case "知乎":
                                sendOkhhtpRequest("http://haojie06.me:9999/delete?zhihu,id=3,uid="+UID);
                                break;
                            case "腾讯视频":
                                sendOkhhtpRequest("http://haojie06.me:9999/delete?others,id=3,uid="+UID);
                                break;
                            case "腾讯漫画":
                                sendOkhhtpRequest("http://haojie06.me:9999/delete?others,id=3,uid="+UID);
                                break;
                            case "起点中文网":
                                sendOkhhtpRequest("http://haojie06.me:9999/delete?others,id=3,uid="+UID);
                                break;
                                default:
                        }

                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.show();
                return false;
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

    private void sendOkhhtpRequest(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient mOkHttpClient = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response = mOkHttpClient.newCall(request).execute();
                    String responseData = response.body().string();
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                 Toast.makeText(mContext, "删除成功！请下拉刷新列表", Toast.LENGTH_SHORT).show();

            }
        }
    };
}

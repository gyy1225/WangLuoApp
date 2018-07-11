package com.example.wangluo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangluo.Adapter.MyRankRecyclerViewAdapter;
import com.example.wangluo.Adapter.MyReferRecyclerViewAdapter;
import com.example.wangluo.Class.Content;
import com.example.wangluo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ASUS on 2018/7/5.
 */

public class RankListFragment extends Fragment {
    private View viewContent;
    private String mTitle;
    private int mPosition;
    private List<Content> rankContentList = new ArrayList<>();
    private List<Content> tiebaContentList = new ArrayList<>();
    private List<Content> weiboContentList = new ArrayList<>();
    private List<Content> yuleContentList = new ArrayList<>();
    private ImageView imageView;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    private String str;
    private Context mContext;
    private MyRankRecyclerViewAdapter myRankRecyclerViewAdapter = new MyRankRecyclerViewAdapter(getContext(), rankContentList);
    //private String responseData;
    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    myRankRecyclerViewAdapter.notifyDataSetChanged();
                    myRankRecyclerViewAdapter = new MyRankRecyclerViewAdapter(getContext(), rankContentList);
                    recyclerView.setAdapter(myRankRecyclerViewAdapter);
                    break;
                case 2:
                    myRankRecyclerViewAdapter.notifyDataSetChanged();
                    myRankRecyclerViewAdapter = new MyRankRecyclerViewAdapter(getContext(), weiboContentList);
                    recyclerView.setAdapter(myRankRecyclerViewAdapter);
                    break;
                case 3:
                    myRankRecyclerViewAdapter.notifyDataSetChanged();
                    myRankRecyclerViewAdapter = new MyRankRecyclerViewAdapter(getContext(), tiebaContentList);
                    recyclerView.setAdapter(myRankRecyclerViewAdapter);
                    break;
                case 4:
                    myRankRecyclerViewAdapter.notifyDataSetChanged();
                    myRankRecyclerViewAdapter = new MyRankRecyclerViewAdapter(getContext(), yuleContentList);
                    recyclerView.setAdapter(myRankRecyclerViewAdapter);
                    break;
                case 5:
                    Toast.makeText(mContext, "数据已刷新！", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                case 0:
                    Toast.makeText(mContext, "服务器连接错误", Toast.LENGTH_SHORT).show();
                    myRankRecyclerViewAdapter = new MyRankRecyclerViewAdapter(getContext(), rankContentList);
                    recyclerView.setAdapter(myRankRecyclerViewAdapter);
                    break;
                default:

            }
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewContent = inflater.inflate(R.layout.rank_list_content, container, false);
        this.mContext = getActivity();

        recyclerView = (RecyclerView) viewContent.findViewById(R.id.rv_rank_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mPosition = getArguments().getInt("position");
        swipeRefreshLayout = viewContent.findViewById(R.id.rank_refesh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        imageView = (ImageView) viewContent.findViewById(R.id.rank_image);
        removeList();
        initList(mPosition);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                removeList();
                refreshList(mPosition);
            }
        });
        return viewContent;
    }

    private void removeList() {
        rankContentList.clear();
        tiebaContentList.clear();
        yuleContentList.clear();
        weiboContentList.clear();
    }

    private void refreshList(int position) {
        initList(position);
        Message message = new Message();
        message.what = 5;
        handler.sendMessage(message);
    }

    public List<Content> initList(int mPosition) {

        switch (mPosition) {
            case 0:
                imageView.setImageResource(R.drawable.wangyiyun2);
                sendRequestWithOkHttp("http://haojie06.me:9999/get?cloudmusic", 1);

                break;
            case 1:
                imageView.setImageResource(R.drawable.tieba3);
                sendRequestWithOkHttp("http://haojie06.me:9999/get?hotnews", 3);
                break;
            case 2:
                imageView.setImageResource(R.drawable.redian3);
                sendRequestWithOkHttp("http://haojie06.me:9999/get?hotnews", 1);
                break;
            case 3:
                imageView.setImageResource(R.drawable.weibo3);
                sendRequestWithOkHttp("http://haojie06.me:9999/get?hotnews", 2);
                break;
            case 4:
                imageView.setImageResource(R.drawable.redian3);

                sendRequestWithOkHttp("http://haojie06.me:9999/get?hotmedias", 1);
                break;
            case 5:
                imageView.setImageResource(R.drawable.dongmanlogo);
                sendRequestWithOkHttp("http://haojie06.me:9999/get?hotmedias", 4);
                break;
            case 6:
                imageView.setImageResource(R.drawable.csdn3);
                // sendRequestWithOkHttp("http://www.baidu.com");
                break;


            case 7:
                imageView.setImageResource(R.drawable.tiyu2);

                break;


            default:
        }
        return rankContentList;
    }

    private void sendRequestWithOkHttp(final String url, final int i) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient mOkHttpClient = new OkHttpClient.Builder().retryOnConnectionFailure(true).build();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response = mOkHttpClient.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData, i);
                } catch (IOException e) {
                    e.printStackTrace();
                    Message message = new Message();
                    message.what = 0;
                    handler.sendMessage(message);
                }
            }
        }).start();

    }

    private void showResponse(String response, int i) {
        String content[] = response.split("', '");
        String contents[] = new String[100];
        for (String mContent : content) {
            Content content1 = new Content();
            contents = mContent.split("\\$\\$");
            String str = contents[0].replace(" ", "");
            str = str.replace("[", "");
            str = str.replace("'", "");
            switch (str) {
                //百度风云榜
                case "百度风云榜实时热点":
                    content1.setResource(str);
                    content1.setGrade(contents[1]);
                    content1.setTitle(contents[2]);
                    content1.setContentURL(contents[3]);
                    content1.setAuthor(contents[4]);
                    rankContentList.add(content1);
                    break;
                //网易云音乐
                case "网易云音乐":
                    contents = mContent.split("\\$\\$");
                    content1.setWebres(str);
                    content1.setResource(contents[1]);
                    content1.setGrade(contents[2]);
                    content1.setTitle(contents[3]);
                    content1.setAuthor(contents[4]);
                    content1.setContentURL(contents[5]);
//
                    rankContentList.add(content1);
                    break;
                //电影
                case "热门电影":
                    content1.setResource(str);
                    content1.setGrade(contents[1]);
                    content1.setTitle(contents[2]);
                    content1.setAuthor(contents[3]);
                    content1.setContentURL(contents[4]);
//            content1.setImageID(Integer.parseInt(contents[6]));
                    //  content1.setTitle(mContent);
                    rankContentList.add(content1);
                    break;
                //电视剧
                case "热门电视剧":
                    content1.setResource(str);
                    content1.setGrade(contents[1]);
                    content1.setTitle(contents[2]);
                    content1.setAuthor(contents[3]);
                    content1.setContentURL(contents[4]);
//              content1.setImageID(Integer.parseInt(contents[6]));
                    //  content1.setTitle(mContent);
                    rankContentList.add(content1);
                    break;
                //综艺动漫小说
                case "热门综艺":
                case "热门动漫":
                case "热门小说":
                    content1.setResource(str);
                    content1.setGrade(contents[1]);
                    content1.setTitle(contents[2]);
                    content1.setAuthor(contents[3]);
                    content1.setContentURL(contents[4]);
//              content1.setImageID(Integer.parseInt(contents[6]));
                    //  content1.setTitle(mContent);
                    yuleContentList.add(content1);
                    break;
                //微博
                case "微博热搜":
                    content1.setResource(str);
                    content1.setGrade(contents[1]);
                    content1.setTitle(contents[2]);
                    content1.setContentURL(contents[3]);
                    content1.setAuthor(contents[4]);
                    weiboContentList.add(content1);
                    break;
                //贴吧
                case "贴吧实时热点":
                    content1.setResource(str);
                    content1.setGrade(contents[1]);
                    content1.setTitle(contents[2]);
                    content1.setContentURL(contents[3]);
                    content1.setAuthor(contents[4]);
                    tiebaContentList.add(content1);
                    break;
                default:
            }

        }
        Message message = new Message();
        switch (i) {
            case 1:
                message.what = 1;
                break;
            case 2:
                message.what = 2;
                break;
            case 3:
                message.what = 3;
                break;
            case 4:
                message.what = 4;
                break;
            case 5:
                message.what = 5;
                break;
            default:

        }

        handler.sendMessage(message);
    }
}




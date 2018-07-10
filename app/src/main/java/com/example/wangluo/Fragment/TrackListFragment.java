package com.example.wangluo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wangluo.Adapter.MyTrackRecyclerViewAdapter;
import com.example.wangluo.Adapter.MyTrackRecyclerViewAdapter;
import com.example.wangluo.Class.Content;
import com.example.wangluo.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ASUS on 2018/7/9.
 */

public class TrackListFragment extends Fragment {
    private View viewContent;
    private Context mContext;
    private RecyclerView recyclerView;
    private int mPosition;
    private MyTrackRecyclerViewAdapter myTrackRecyclerViewAdapter;
    private List<Content> TrackContentList=new ArrayList<>();
    private List<Content> weiboTrackList=new ArrayList<>();
    private List<Content> zhihuTrackList=new ArrayList<>();
    private List<Content> shipinTrackList=new ArrayList<>();
    private List<Content> dongmanTrackList=new ArrayList<>();
    private List<Content> xiaoshuoTrackList=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    myTrackRecyclerViewAdapter = new MyTrackRecyclerViewAdapter(mContext,weiboTrackList);
                    recyclerView.setAdapter(myTrackRecyclerViewAdapter);
                    break;
                case 2:
                     myTrackRecyclerViewAdapter = new MyTrackRecyclerViewAdapter(mContext,zhihuTrackList);
                    recyclerView.setAdapter(myTrackRecyclerViewAdapter);
                    break;
                case 0:
                    Toast.makeText(mContext, "服务器连接错误", Toast.LENGTH_SHORT).show();
                    myTrackRecyclerViewAdapter = new MyTrackRecyclerViewAdapter(mContext,TrackContentList);
                    recyclerView.setAdapter(myTrackRecyclerViewAdapter);
                default:

            }
        }

    };
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
    public void initList(int mPosition) {
        switch (mPosition) {
            case 0:
                sendRequestWithOkHttp("http://haojie06.me:9999/get?sinablog,id=1",1);
                break;
            case 1:
                sendRequestWithOkHttp("http://haojie06.me:9999/get?zhihu,id=1",2);
                break;
            default:

        }

    }
    private void sendRequestWithOkHttp(final String url,final int i) {
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
                    showResponse(responseData,i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void showResponse(String response,int i) {
        String content[] = response.split("', '");
        String contents[] = new String[100];

        for (String mContent : content) {
            Content content1 = new Content();
            contents = mContent.split("\\$\\$");
            String str = contents[0].replace(" ", "");
            str = str.replace("[", "");
            str = str.replace("'", "");
            switch (str) {
                case "新浪微博":
                    content1.setResource(str);
                    content1.setId(contents[1]);

                    content1.setImageID(contents[2]);
                    content1.setAuthor(contents[3]);
                    content1.setContentURL(contents[4]);
                    content1.setTitle(contents[5]);
                    weiboTrackList.add(content1);
                    break;
                case "知乎":
                    content1.setResource(str);
                    content1.setId(contents[1]);
                    content1.setImageID(contents[2]);
                    content1.setAuthor(contents[3]);
                    content1.setContentURL(contents[4]);
                    content1.setTitle(contents[5]+"   “"+contents[6]+"”");
                    zhihuTrackList.add(content1);
                    break;
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


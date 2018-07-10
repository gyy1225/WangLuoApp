package com.example.wangluo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangluo.Adapter.MyReferRecyclerViewAdapter;
import com.example.wangluo.Class.Content;
import com.example.wangluo.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ASUS on 2018/7/10.
 */

public class ReferListFragment extends Fragment {
    private List<Content> mReferList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Context mContext;
    private int mPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:

                    break;
                default:

            }
        }

    };
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.refer_list_content, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.refer_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        this.mContext = getActivity();
        mPosition = getArguments().getInt("position");
        initList(mPosition);
        MyReferRecyclerViewAdapter myReferRecyclerViewAdapter = new MyReferRecyclerViewAdapter(mContext,mReferList);
        recyclerView.setAdapter(myReferRecyclerViewAdapter);
        return view;
    }
    public List<Content> initList(int mPosition) {
        Content content1=new Content();
        //"技术""科技"娱乐""生活""体育""军事""游戏"动漫"其他"
        switch (mPosition) {
            case 0:
                content1.setTitle("CSDN");
                content1.setImageID("R.drawable.csdn3");

        }
        return mReferList;
    }

    private void sendRequestWithOkHttp(final String url) {
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
                    showResponse(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void showResponse(String response) {
        Content content = new Content();
        content.setTitle(response);
        mReferList.add(content);

        Message message = new Message();
        message.what = 1;
        handler.sendMessage(message);
    }
}



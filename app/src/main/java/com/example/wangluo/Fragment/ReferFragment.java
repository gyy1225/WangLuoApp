package com.example.wangluo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wangluo.Adapter.MyReferRecyclerViewAdapter;
import com.example.wangluo.Class.Content;
import com.example.wangluo.R;
import com.example.wangluo.dummy.DummyContent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class ReferFragment extends Fragment {

    private List<Content> mReferList=new ArrayList<>();
    private RecyclerView recyclerView;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ReferFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private Handler handler= new Handler(){

        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    MyReferRecyclerViewAdapter myReferRecyclerViewAdapter = new MyReferRecyclerViewAdapter(mReferList);
                    recyclerView.setAdapter(myReferRecyclerViewAdapter);
                    break;
                    default:

            }
        }

    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_refer_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.refer_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        //mReferList=initRecyclerView();
        sendRequestWithOkHttp();
        return view;

    }
private List<Content> initRecyclerView(){
       Content content1=new Content();
       content1.setTitle("我不是药神");
       content1.setResource("微博");
       content1.setContent("电影我不是药神票房口碑爆棚");
       mReferList.add(content1);
        return mReferList;
}
private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient mOkHttpClient = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://haojie06.me:9999/get?cloudmusic")
                            .build();
                    Response response = mOkHttpClient.newCall(request).execute();
                    String responseData =response.body().string();
                    showResponse(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    private void showResponse(String response){
        Content content=new Content();
        content.setTitle(response);
        mReferList.add(content);

        Message message=new Message();
        message.what=1;
handler.sendMessage(message);
    }
}

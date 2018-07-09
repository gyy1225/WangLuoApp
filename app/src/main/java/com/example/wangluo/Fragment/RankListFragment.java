package com.example.wangluo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
        private List<Content> rankContentList=new ArrayList<>();
        private ImageView imageView;
        private RecyclerView recyclerView;
        public void setTitle(String mTitle) {
            this.mTitle = mTitle;
        }
        private String str;
        private Context mContext;

    //private String responseData;
private Handler handler= new Handler(){

    public void handleMessage(Message msg) {

        switch (msg.what) {
            case 1:
                MyRankRecyclerViewAdapter  myRankRecyclerViewAdapter= new MyRankRecyclerViewAdapter(rankContentList);
                recyclerView.setAdapter(myRankRecyclerViewAdapter);
                break;
            case 0:
                Toast.makeText(mContext, "服务器连接错误", Toast.LENGTH_SHORT).show();
                myRankRecyclerViewAdapter= new MyRankRecyclerViewAdapter(rankContentList);
                recyclerView.setAdapter(myRankRecyclerViewAdapter);
            default:

        }
    }

};
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            viewContent = inflater.inflate(R.layout.rank_list_content,container,false);
            this.mContext = getActivity();

            recyclerView = (RecyclerView) viewContent.findViewById(R.id.rv_rank_list);
            LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            mPosition=getArguments().getInt("position");

            imageView=(ImageView)viewContent.findViewById(R.id.rank_image) ;
            initList(mPosition);
            /*MyRankRecyclerViewAdapter myRankRecyclerViewAdapter=new MyRankRecyclerViewAdapter(rankContentList);
            recyclerView.setAdapter(myRankRecyclerViewAdapter);*/

            return viewContent;
        }
        public List<Content> initList(int mPosition){

            switch (mPosition) {
                case 0:
                    imageView.setImageResource(R.drawable.csdn3);
                   // sendRequestWithOkHttp("http://www.baidu.com");
                    break;
                case 1:
                    imageView.setImageResource(R.drawable.tiyu2);

                    break;
                case 2:
                    imageView.setImageResource(R.drawable.redian3);

                    break;
                case 3:
                    Content content1 = new Content();

                    imageView.setImageResource(R.drawable.wangyiyun2);
                    /*content1.setTitle("往后余生");content1.setId("1");
                    content1.setAuthor("马良");
                    rankContentList.add(content1);*/
                    sendRequestWithOkHttp("http://haojie06.me:9999/get?cloudmusic");
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.dianying2);

                    break;
                case 5:
                    imageView.setImageResource(R.drawable.weibo3);

                    break;
                case 6:
                    imageView.setImageResource(R.drawable.junshi2);

                    break;
                case 7:
                    imageView.setImageResource(R.drawable.tieba3);

                    break;

                default:
            }
            return  rankContentList;
        }
    private void sendRequestWithOkHttp(final String url) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                    OkHttpClient mOkHttpClient = new OkHttpClient.Builder().retryOnConnectionFailure(true).build();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response = mOkHttpClient.newCall(request).execute();
                    String responseData =response.body().string();
                    showResponse(responseData);
                } catch (IOException e) {
                        e.printStackTrace();
                        Message message=new Message();
                        message.what=0;
                        handler.sendMessage(message);
                    }
                }
            }).start();

    }
    private void showResponse(String response){
        String content[]=response.split("', '");
       for (String mContent:content) {
            Content content1 = new Content();
            String contents[]=new String[100];
            contents=mContent.split("\\$\\$");
            content1.setWebres(contents[0]);
            content1.setResource(contents[1]);
            content1.setId(contents[2]);
            content1.setTitle(contents[3]);
            content1.setAuthor(contents[4]);
            content1.setContentURL(contents[5]);
//            content1.setImageID(Integer.parseInt(contents[6]));
          //  content1.setTitle(mContent);
            rankContentList.add(content1);
        }
            Message message=new Message();
            message.what=1;
            handler.sendMessage(message);
        }
    }




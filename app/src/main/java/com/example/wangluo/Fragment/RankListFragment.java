package com.example.wangluo.Fragment;

import android.os.Bundle;
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
import com.example.wangluo.Class.Content;
import com.example.wangluo.R;

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
        public void setTitle(String mTitle) {
            this.mTitle = mTitle;
        }
        private String str;
    String responseData;

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            viewContent = inflater.inflate(R.layout.rank_list_content,container,false);
            RecyclerView recyclerView = (RecyclerView) viewContent.findViewById(R.id.rv_rank_list);
            LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            mPosition=getArguments().getInt("position");

            imageView=(ImageView)viewContent.findViewById(R.id.rank_image) ;
            initList(mPosition);
            MyRankRecyclerViewAdapter myRankRecyclerViewAdapter=new MyRankRecyclerViewAdapter(rankContentList);
            recyclerView.setAdapter(myRankRecyclerViewAdapter);

            return viewContent;
        }
        public List<Content> initList(int mPosition){

            switch (mPosition) {
                case 0:
                    imageView.setImageResource(R.drawable.csdn3);

                    break;
                case 1:
                    imageView.setImageResource(R.drawable.tiyu2);

                    break;
                case 2:
                    imageView.setImageResource(R.drawable.redian3);

                    break;
                case 3:
                    Content content1 = new Content();
                    content1.setId("1");
                    imageView.setImageResource(R.drawable.wangyiyun2);
                    content1.setTitle("往后余生");
                    content1.setAuthor("马良");
                  //  if (rankContentList==null)
                    new Thread(){
                        @Override
                        public void run() {
                            OkHttpClient client=new OkHttpClient();
                            Request request=new Request.Builder()
                                    .url("https://github.com/gyy1225/WangLuoApp/tree/master/app")
                                    .build();
                            Response response= null;
                            try {
                                response = client.newCall(request).execute();
                                responseData=response.body().string();
                                Log.i("getResponse",response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    String docContent=responseData;
                    rankContentList.add(content1);
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
    private void getAsynHttp(String url) {
        OkHttpClient mOkHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("fail","失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                str = response.body().string();
                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplication(), str, Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
        });

    }
    }



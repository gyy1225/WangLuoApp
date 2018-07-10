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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.refer_list_content, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.refer_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        this.mContext = getActivity();
        mPosition = getArguments().getInt("position");
        initList(mPosition);
        MyReferRecyclerViewAdapter myReferRecyclerViewAdapter = new MyReferRecyclerViewAdapter(mContext, mReferList);
        recyclerView.setAdapter(myReferRecyclerViewAdapter);
        return view;
    }

    public List<Content> initList(int mPosition) {
        Content content1 = new Content();
        Content content2 = new Content();
        Content content3 = new Content();
        Content content4 = new Content();
        Content content5 = new Content();
        Content content6 = new Content();
        Content content7 = new Content();
        Content content8 = new Content();
        Content content9 = new Content();
        Content content10 = new Content();
        // "科技""电影""读书""娱乐""生活""学业""体育""邮箱""华科""游戏""动漫""其他"
        switch (mPosition) {
            case 0:
                content1.setTitle("CSDN");
                content1.setImageID2(R.drawable.csdn3);
                content1.setContentURL("https://www.csdn.net/");
                mReferList.add(content1);
                content2.setTitle("V2EX");
                content2.setImageID2(R.drawable.v2ex);
                content2.setContentURL("https://www.v2ex.com/");
                mReferList.add(content2);
                content3.setTitle("51CTO");
                content3.setImageID2(R.drawable.com51cto);
                content3.setContentURL("http://www.51cto.com/");
                mReferList.add(content3);
                content4.setTitle("果壳网");
                content4.setImageID2(R.drawable.guoke);
                content4.setContentURL("https://www.guokr.com/");
                mReferList.add(content4);
                content5.setTitle("科学网");
                content5.setImageID2(R.drawable.kexuewang);
                content5.setContentURL("http://www.sciencenet.cn/");
                mReferList.add(content5);
                content6.setTitle("natureAsia");
                content6.setImageID2(R.drawable.natureasia);
                content6.setContentURL("http://www.natureasia.com/");
                mReferList.add(content6);
                content7.setTitle("中华自然科学网");
                content7.setImageID2(R.drawable.zhongguokexue);
                content7.setContentURL("http://www.scicn.net/index.html");
                mReferList.add(content7);
                break;

            case 1:
                content1.setTitle("豆瓣电影");
                content1.setImageID2(R.drawable.douban3);
                content1.setContentURL("https://movie.douban.com/");
                mReferList.add(content1);
                content2.setTitle("时光网");
                content2.setImageID2(R.drawable.shiguangwang2);
                content2.setContentURL("http://www.mtime.com/");
                mReferList.add(content2);
                content3.setTitle("淘票票");
                content3.setImageID2(R.drawable.taopp2);
                content3.setContentURL("https://www.taopiaopiao.com/");
                mReferList.add(content3);
                content4.setTitle("爱奇艺电影频道");
                content4.setImageID2(R.drawable.aiqiyi2);
                content4.setContentURL("http://www.iqiyi.com/dianying/");
                mReferList.add(content4);
break;
            case 2:
                /*每日一文 https://meiriyiwen.com/
                豆瓣读书 https://book.douban.com/
                网易云阅读http://yuedu.163.com/
                qq阅读http://book.qq.com/
                小说阅读网https://www.readnovel.com/
                中华诗词网http://www.zhsc.net/
                散文网https://www.sanwen.net/*/
                content1.setTitle("每日一文");
                content1.setImageID2(R.drawable.meiriyiwen);
                content1.setContentURL("https://meiriyiwen.com/");
                mReferList.add(content1);
                content2.setTitle("豆瓣读书");
                content2.setImageID2(R.drawable.doubandushu);
                content2.setContentURL("https://book.douban.com/");
                mReferList.add(content2);
                content3.setTitle("网易云阅读");
                content3.setImageID2(R.drawable.wangyiyunyuedu);
                content3.setContentURL("http://yuedu.163.com/");
                mReferList.add(content3);
                content4.setTitle("qq阅读");
                content4.setImageID2(R.drawable.qqyuedu);
                content4.setContentURL("http://book.qq.com/");
                mReferList.add(content4);
                content5.setTitle("小说阅读网");
                content5.setImageID2(R.drawable.xiaoshuoyueduwang);
                content5.setContentURL("https://www.readnovel.com/");
                mReferList.add(content5);
                content6.setTitle("中华诗词网");
                content6.setImageID2(R.drawable.zhonghuashici);
                content6.setContentURL("http://www.zhsc.net/");
                mReferList.add(content6);
                content7.setTitle("散文网");
                content7.setImageID2(R.drawable.sanwenwang);
                content7.setContentURL("https://www.sanwen.net/");
                mReferList.add(content7);
                break;
            case 3:
                /*橘子娱乐http://www.happyjuzi.com/
                腾讯娱乐https://new.qq.com/ch/ent/
                新浪娱乐http://ent.sina.com.cn/
                网易娱乐http://ent.163.com/*/
                content1.setTitle("腾讯娱乐");
                content1.setImageID2(R.drawable.douban3);
                content1.setContentURL("https://new.qq.com/ch/ent/");
                mReferList.add(content1);
                content2.setTitle("橘子娱乐");
                content2.setImageID2(R.drawable.shiguangwang2);
                content2.setContentURL("http://www.happyjuzi.com/");
                mReferList.add(content2);
                content3.setTitle("新浪娱乐");
                content3.setImageID2(R.drawable.taopp2);
                content3.setContentURL("http://ent.sina.com.cn/");
                mReferList.add(content3);
                content4.setTitle("网易娱乐");
                content4.setImageID2(R.drawable.aiqiyi2);
                content4.setContentURL("http://ent.163.com/");
                mReferList.add(content4);
                break;

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



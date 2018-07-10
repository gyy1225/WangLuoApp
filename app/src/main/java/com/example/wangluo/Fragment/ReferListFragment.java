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
                content1.setImageID2(R.drawable.tengxunyule);
                content1.setContentURL("https://new.qq.com/ch/ent/");
                mReferList.add(content1);
                content2.setTitle("橘子娱乐");
                content2.setImageID2(R.drawable.juziyule);
                content2.setContentURL("http://www.happyjuzi.com/");
                mReferList.add(content2);
                content3.setTitle("新浪娱乐");
                content3.setImageID2(R.drawable.xinlangyule);
                content3.setContentURL("http://ent.sina.com.cn/");
                mReferList.add(content3);
                content4.setTitle("网易娱乐");
                content4.setImageID2(R.drawable.wangyiyule);
                content4.setContentURL("http://ent.163.com/");
                mReferList.add(content4);
                break;
            case 4:/*生活
            什么值得买：https://www.smzdm.com/list/
          豆瓣同城 https://www.douban.com/location/wuhan/
           58同城 http://wh.58.com/
           美食天下https://www.meishichina.com/
           智联招聘https://www.zhaopin.com/
          大众点评http://www.dianping.com/
          口碑http://www.dianping.com/
          携程http://www.ctrip.com/
          途牛http://www.tuniu.com/
          百度糯米https://wh.nuomi.com/
            */
                content1.setTitle("什么值得买");
                content1.setImageID2(R.drawable.shenmezhidemai);
                content1.setContentURL("https://www.smzdm.com/list/");
                mReferList.add(content1);
                content2.setTitle("豆瓣同城");
                content2.setImageID2(R.drawable.doubantongcheng);
                content2.setContentURL("https://www.douban.com/location/wuhan/");
                mReferList.add(content2);
                content3.setTitle("58同城");
                content3.setImageID2(R.drawable.tongcheng58);
                content3.setContentURL("http://wh.58.com/");
                mReferList.add(content3);
                content4.setTitle("美食天下");
                content4.setImageID2(R.drawable.meishitianxia);
                content4.setContentURL("https://www.meishichina.com/");
                mReferList.add(content4);
                content5.setTitle("智联招聘");
                content5.setImageID2(R.drawable.zhilianzhaopin);
                content5.setContentURL("https://www.zhaopin.com/");
                mReferList.add(content5);
                content6.setTitle("大众点评");
                content6.setImageID2(R.drawable.dazhongdianping);
                content6.setContentURL("http://www.dianping.com/");
                mReferList.add(content6);
                content7.setTitle("口碑");
                content7.setImageID2(R.drawable.koubei);
                content7.setContentURL("http://www.dianping.com/");
                mReferList.add(content7);
                content8.setTitle("携程");
                content8.setImageID2(R.drawable.xiecheng);
                content8.setContentURL("http://www.ctrip.com/");
                mReferList.add(content8);
                content9.setTitle("途牛");
                content9.setImageID2(R.drawable.tuniu);
                content9.setContentURL("http://www.tuniu.com/");
                mReferList.add(content9);
                content10.setTitle("百度糯米");
                content10.setImageID2(R.drawable.baidunuomi);
                content10.setContentURL("https://wh.nuomi.com/");
                mReferList.add(content10);
                break;
            case 5:/*学业
              中国知网http://www.cnki.net/
           万方数据库http://www.wanfangdata.com.cn/index.html
          维基百科https://www.wikipedia.org/
          中国期刊网http://www.chinaqking.com/
          维普网http://www.cqvip.com/
          百度学术http://xueshu.baidu.com
         Web of Science http://apps.webofknowledge.com/UA_GeneralSearch_input.do?product=UA&search_mode=GeneralSearch&SID=8DYP1JypHSZIYkMvSZy&preferencesSaved=
        Springer Link https://link.springer.com/
        Engineering Village  https://www.engineeringvillage.com/search/quick.url
        Wiley Online Library https://onlinelibrary.wiley.com/*/
                content1.setTitle("中国知网");
                content1.setImageID2(R.drawable.zhongguozhiwang4);
                content1.setContentURL("http://www.cnki.net/");
                mReferList.add(content1);
                content2.setTitle("万方数据库");
                content2.setImageID2(R.drawable.wanfang);
                content2.setContentURL("http://www.wanfangdata.com.cn/index.html");
                mReferList.add(content2);
                content3.setTitle("维基百科");
                content3.setImageID2(R.drawable.wiki);
                content3.setContentURL("https://www.wikipedia.org/");
                mReferList.add(content3);
                content4.setTitle("中国期刊网");
                content4.setImageID2(R.drawable.zhongguoqikan);
                content4.setContentURL("http://www.chinaqking.com/");
                mReferList.add(content4);
                content5.setTitle("维普网");
                content5.setImageID2(R.drawable.weipu);
                content5.setContentURL("http://www.cqvip.com/");
                mReferList.add(content5);
                content6.setTitle("Web of Science");
                content6.setImageID2(R.drawable.webofscience);
                content6.setContentURL("http://apps.webofknowledge.com/UA_GeneralSearch_input.do?product=UA&search_mode=GeneralSearch&SID=8DYP1JypHSZIYkMvSZy&preferencesSaved=" );
                mReferList.add(content6);
                content7.setTitle("Springer Link");
                content7.setImageID2(R.drawable.springerlink);
                content7.setContentURL("https://link.springer.com/");
                mReferList.add(content7);
                content8.setTitle("Engineering Village");
                content8.setImageID2(R.drawable.engineeringvillage);
                content8.setContentURL("https://www.engineeringvillage.com/search/quick.url");
                mReferList.add(content8);
                content9.setTitle("Wiley Online Library ");
                content9.setImageID2(R.drawable.wiley);
                content9.setContentURL("https://onlinelibrary.wiley.com/");
                mReferList.add(content9);
                content10.setTitle("百度学术");
                content10.setImageID2(R.drawable.baiduxueshu);
                content10.setContentURL("http://xueshu.baidu.com");
                mReferList.add(content10);
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



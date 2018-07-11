package com.example.wangluo.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wangluo.Activity.AddActivity;
import com.example.wangluo.Activity.AddWebActivity;
import com.example.wangluo.Adapter.MyReferRecyclerViewAdapter;
import com.example.wangluo.Class.Content;
import com.example.wangluo.R;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ASUS on 2018/7/10.
 */

public class ReferListFragment extends Fragment {
    private List<Content> mReferList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Context mContext;
    private int mPosition;
    private Intent intent;
    private int getPosition;
    private String newWebURL;
    private FloatingActionButton addWebButton;
    private String newWebID;
    private MyReferRecyclerViewAdapter myReferRecyclerViewAdapter;
    private Content newContent = new Content();
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    Toast.makeText(mContext, "数据已刷新！", Toast.LENGTH_SHORT).show();
                    myReferRecyclerViewAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(myReferRecyclerViewAdapter);
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                default:

            }
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.refer_list_content, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.refer_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        addWebButton = view.findViewById(R.id.refer_add);
        addWebButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(view.getContext(), AddWebActivity.class);
                intent.putExtra("POSITION", mPosition);
                startActivityForResult(intent, 1);

            }
        });
        intent = getActivity().getIntent();
        getPosition = intent.getIntExtra("POSITION", getPosition);
        newWebURL = intent.getStringExtra("NEWWEB");
        newWebID = intent.getStringExtra("NEWWEBID");
        this.mContext = getActivity();
        mPosition = getArguments().getInt("position");
        mReferList.clear();
        initList(mPosition);
        loadSelfWeb();
        LitePal.getDatabase();
        if (newWebURL != null) {
            Content content = new Content();
            content.setImageID2(R.drawable.wangluo3);
            content.setContentURL("http://" + newWebURL + "/");//http://www.dangdang.com/  http://www.dangdang.com/
            content.setTitle(newWebID);
            content.setmPosition(getPosition);
            content.save();
            mReferList.add(content);
        }
        swipeRefreshLayout = view.findViewById(R.id.refer_fresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                removeList();
                refreshList(mPosition);
            }
        });
        myReferRecyclerViewAdapter = new MyReferRecyclerViewAdapter(mContext, mReferList);
        recyclerView.setAdapter(myReferRecyclerViewAdapter);
        return view;
    }

    private void removeList() {
        mReferList.clear();
    }

    private void refreshList(int position) {
        initList(position);
        loadSelfWeb();
        Message message = new Message();
        message.what = 1;
        handler.sendMessage(message);

    }
//从数据库加载自定义网页
    private void loadSelfWeb(){
        List<Content>selfContents= LitePal.findAll(Content.class);
        for (Content mContent:selfContents) {
            int mPosition=mContent.getmPosition();
            if (mContent.getContentURL() != null) {
                newContent=mContent;
                initList(mPosition);
            }
        }
    }
    //添加自定义网页用的
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    getPosition = intent.getIntExtra("POSITION", getPosition);
                    newWebURL = intent.getStringExtra("NEWWEB");
                    newWebID = intent.getStringExtra("NEWWEBID");
                    if (newWebURL != null) {
                        newContent.setImageID2(R.drawable.wangluo3);
                        newContent.setContentURL("http://" + newWebURL + "/");//http://www.dangdang.com/  http://www.dangdang.com/
                        newContent.setTitle(newWebID);
                        mReferList.clear();
                        initList(getPosition);
                        myReferRecyclerViewAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(myReferRecyclerViewAdapter);
                    }
                }
                break;

            default:
        }
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
        Content content11=new Content();
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
                content6.setImageID2(R.drawable.natureaisa1);
                content6.setContentURL("http://www.natureasia.com/");
                mReferList.add(content6);
                content7.setTitle("中华自然科学网");
                content7.setImageID2(R.drawable.zhongguokexue);
                content7.setContentURL("http://www.scicn.net/index.html");
                mReferList.add(content7);
                if (newWebURL != null) {
                mReferList.add(newContent);
            }
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
                if (newWebURL != null) {
                    mReferList.add(newContent);
                }
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
                if (newWebURL != null) {
                    mReferList.add(newContent);
                }
                break;
            case 3:
                /*橘子娱乐http://www.happyjuzi.com/
                腾讯娱乐https://new.qq.com/ch/ent/
                新浪娱乐http://ent.sina.com.cn/
                网易娱乐http://ent.163.com/*/
                content1.setTitle("搜狐娱乐");
                content1.setImageID2(R.drawable.souhuyule);
                content1.setContentURL("http://yule.sohu.com/");
                //content1.setContentURL("https://new.qq.com/ch/ent/");
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
                if (newWebURL != null) {
                    mReferList.add(newContent);
                }
                break;
            case 4:/*生活
            蝉游记 http://chanyouji.com/trips#home-trips
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
                content1.setTitle("蝉游记");
                content1.setImageID2(R.drawable.chanyouji);
                content1.setContentURL("http://chanyouji.com/");
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
                content11.setTitle("什么值得买");
                content11.setImageID2(R.drawable.shenmezhidemai);
                content11.setContentURL("https://www.smzdm.com/list/");
                mReferList.add(content11);
                if (newWebURL != null) {
                    mReferList.add(newContent);
                }
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
                content6.setContentURL("http://apps.webofknowledge.com/UA_GeneralSearch_input.do?product=UA&search_mode=GeneralSearch&SID=8DYP1JypHSZIYkMvSZy&preferencesSaved=");
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
                if (newWebURL != null) {
                    mReferList.add(newContent);
                }
                break;
            case 6:
                /*运动  腾讯体育 http://sports.qq.com/
 篮球：虎扑 https://nba.hupu.com/
 体坛网http://www.titan24.com/
 凤凰网体育http://sports.ifeng.com/
 央视网体育http://sports.cctv.com/
 新浪体育http://2018.sina.com.cn/*/
                content1.setTitle("腾讯体育");
                content1.setImageID2(R.drawable.tengxuntiyu);
                content1.setContentURL("http://sports.qq.com/");
                mReferList.add(content1);
                content2.setTitle("虎扑");
                content2.setImageID2(R.drawable.hupu);
                content2.setContentURL("https://nba.hupu.com/");
                mReferList.add(content2);
                content3.setTitle("体坛网");
                content3.setImageID2(R.drawable.titan);
                content3.setContentURL("http://www.titan24.com/");
                mReferList.add(content3);
                content4.setTitle("凤凰网体育");
                content4.setImageID2(R.drawable.fenghuangwangtiyu);
                content4.setContentURL("http://sports.ifeng.com/");
                mReferList.add(content4);
                content5.setTitle("央视网体育");
                content5.setImageID2(R.drawable.yangshitiyu);
                content5.setContentURL("http://sports.cctv.com/");
                mReferList.add(content5);
                content6.setTitle("新浪体育");
                content6.setImageID2(R.drawable.xinlangtiyu);
                content6.setContentURL("http://2018.sina.com.cn/");
                mReferList.add(content6);
                if (newWebURL != null) {
                    mReferList.add(newContent);
                }
                break;
            case 7:/*游戏板块: 游戏折扣，上架 ,游戏新闻 
               steam官网https://store.steampowered.com/
                游戏时光  http://www.vgtime.com/s/steam/
                腾讯游戏http://game.qq.com/
                17173https://www.17173.com/
                游民星空http://www.gamersky.com/
                多玩游戏网http://www.duowan.com/
                 电玩巴士http://www.tgbus.com/*/
                content1.setTitle("steam官网");
                content1.setImageID2(R.drawable.steam);
                content1.setContentURL("https://store.steampowered.com/");
                mReferList.add(content1);
                content2.setTitle("游戏时光");
                content2.setImageID2(R.drawable.youxishiguang);
                content2.setContentURL("http://www.vgtime.com/s/steam/");
                mReferList.add(content2);
                content3.setTitle("腾讯游戏");
                content3.setImageID2(R.drawable.tengxunyouxi);
                content3.setContentURL("http://game.qq.com/");
                mReferList.add(content3);
                content4.setTitle("17173");
                content4.setImageID2(R.drawable.youxi17173);
                content4.setContentURL("https://www.17173.com/");
                mReferList.add(content4);
                content5.setTitle("游民星空");
                content5.setImageID2(R.drawable.youminxibngkong3);
                content5.setContentURL("http://www.gamersky.com/");
                mReferList.add(content5);
                content6.setTitle("多玩游戏网");
                content6.setImageID2(R.drawable.duowanyouxi);
                content6.setContentURL("http://www.duowan.com/");
                mReferList.add(content6);
                content7.setTitle("电玩巴士");
                content7.setImageID2(R.drawable.dianwanbashi);
                content7.setContentURL("http://www.tgbus.com/");
                mReferList.add(content7);
                if (newWebURL != null) {
                    mReferList.add(newContent);
                }
                break;
            case 8:/*动漫 网易漫画https://manhua.163.com/
        有妖气 http://www.u17.com/
        快看漫画http://www.kuaikanmanhua.com/
        腾讯动漫http://ac.qq.com/
        动漫之家http://donghua.dmzj.com/
        bilibilihttps://www.bilibili.com/
        niconicohttp://www.nicovideo.jp/*/
                content1.setTitle("网易漫画");
                content1.setImageID2(R.drawable.wangyimanhua);
                content1.setContentURL("https://manhua.163.com/");
                mReferList.add(content1);
                content2.setTitle("有妖气");
                content2.setImageID2(R.drawable.youyaoqi1);
                content2.setContentURL("http://www.u17.com/");
                mReferList.add(content2);
                content3.setTitle("快看漫画");
                content3.setImageID2(R.drawable.kuaikanmanhua);
                content3.setContentURL("http://www.kuaikanmanhua.com/");
                mReferList.add(content3);
                content4.setTitle("腾讯动漫");
                content4.setImageID2(R.drawable.tengxundongman);
                content4.setContentURL("http://ac.qq.com/");
                mReferList.add(content4);
                content5.setTitle("动漫之家");
                content5.setImageID2(R.drawable.dongmanzhijia);
                content5.setContentURL("http://donghua.dmzj.com/");
                mReferList.add(content5);
                content6.setTitle("bilibili");
                content6.setImageID2(R.drawable.bilibili);
                content6.setContentURL("https://www.bilibili.com/");
                mReferList.add(content6);
                content7.setTitle("niconico");
                content7.setImageID2(R.drawable.niconico2);
                content7.setContentURL("http://www.nicovideo.jp/");
                mReferList.add(content7);
                if (newWebURL != null) {
                    mReferList.add(newContent);
                }
                break;
            case 9:
                /*邮箱 网易163https://mail.163.com/
       新浪邮箱https://mail.sina.com.cn/
       126邮箱https://www.126.com/
        qq邮箱https://mail.qq.com/
        Gmailhttps://www.google.com/intl/zh-CN/gmail/about/*/
                content1.setTitle("网易163");
                content1.setImageID2(R.drawable.wangyiyouxiang2);
                content1.setContentURL("https://mail.163.com/");
                mReferList.add(content1);
                content2.setTitle("新浪邮箱");
                content2.setImageID2(R.drawable.xinlangyouxiang2);
                content2.setContentURL("https://mail.sina.com.cn/");
                mReferList.add(content2);
                content3.setTitle("126邮箱");
                content3.setImageID2(R.drawable.wangyi126);
                content3.setContentURL("https://www.126.com/");
                mReferList.add(content3);
                content4.setTitle("qq邮箱");
                content4.setImageID2(R.drawable.youxiangqq2);
                content4.setContentURL("https://mail.qq.com/");
                mReferList.add(content4);
                content5.setTitle("Gmail");
                content5.setImageID2(R.drawable.gmail2);
                content5.setContentURL("https://www.google.com/intl/zh-CN/gmail/about/");
                mReferList.add(content5);
                if (newWebURL != null) {
                    mReferList.add(newContent);
                }
                break;
            case 10:
                /*
华科有关 官网http://www.hust.edu.cn/
                智慧华中大https://pass.hust.edu.cn/cas/login?service=http%3A%2F         %2Fone.hust.edu.cn%2Fdcp%2Findex.jsp
               华科图书馆 http://www.lib.hust.edu.cn/
              华科邮箱https://mail.hust.edu.cn/
              华科学工系统http://student.hust.edu.cn/
              物理预约http://115.156.215.251/
               物理实验BBShttp://115.156.215.251/bbs/*/
                content1.setTitle("官网");
                content1.setImageID2(R.drawable.huakeguanwang);
                content1.setContentURL("http://www.hust.edu.cn/");
                mReferList.add(content1);
                content2.setTitle("智慧华中大");
                content2.setImageID2(R.drawable.zhihuihuazhongda);
                content2.setContentURL("https://pass.hust.edu.cn/cas/login?service=http%3A%2F%2Fone.hust.edu.cn%2Fdcp%2Findex.jsp");
                mReferList.add(content2);
                content3.setTitle("华科图书馆");
                content3.setImageID2(R.drawable.huaketushuguan);
                content3.setContentURL("http://www.lib.hust.edu.cn/");
                mReferList.add(content3);
                content4.setTitle("华科邮箱");
                content4.setImageID2(R.drawable.huakeyouxiang);
                content4.setContentURL("https://mail.hust.edu.cn/");
                mReferList.add(content4);
                content5.setTitle("华科学工系统");
                content5.setImageID2(R.drawable.huakexuegong);
                content5.setContentURL("http://student.hust.edu.cn/");
                mReferList.add(content5);
                content6.setTitle("物理预约");
                content6.setImageID2(R.drawable.huakedawu3);
                content6.setContentURL("http://115.156.215.251/");
                mReferList.add(content6);
                content7.setTitle("物理实验BBS");
                content7.setImageID2(R.drawable.huakewulibbs);
                content7.setContentURL("http://115.156.215.251/bbs/");
                mReferList.add(content7);
                if (newWebURL != null) {
                    mReferList.add(newContent);
                }
                break;
            case 11://自定义
                if (newWebURL != null) {
                    mReferList.add(newContent);
                }
                mReferList.add(newContent);
                break;
                 /*
医药 万方医学网http://med.wanfangdata.com.cn/
        好大夫在线https://www.haodf.com/
        医学论坛网http://www.cmt.com.cn/
        丁香园http://www.dxy.cn/*/
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



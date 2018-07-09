package com.example.wangluo.Presenter;

import com.example.wangluo.Class.Content;
import com.example.wangluo.Utils.CallBack;
import com.example.wangluo.Utils.OkhttpUtils;
import com.example.wangluo.View.IView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ASUS on 2018/7/7.
 */

public class Presenter {
    private IView iv;

    //???
    public void attachView(IView iv){
        this.iv = iv;
    }

    public void getContent(String url){

        OkhttpUtils.getInstance().get(url, Content.class, new CallBack() {
            @Override
            public void onSuccess(Object o) {
                List<Content> contentList= (List<Content>) o;
                iv.success(contentList);
            }

            @Override
            public void onFailed(Exception e) {
                iv.failed(e);
            }
        });





    }


}

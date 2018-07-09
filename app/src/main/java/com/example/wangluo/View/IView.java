package com.example.wangluo.View;

import com.example.wangluo.Class.Content;

import java.util.List;

/**
 * Created by ASUS on 2018/7/7.
 */

public interface IView {
    void success(List<Content> contentList);
    void failed(Exception e);

}

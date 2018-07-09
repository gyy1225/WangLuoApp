package com.example.wangluo.Utils;

import android.os.Handler;
import android.text.TextUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ASUS on 2018/7/7.
 */

public class OkhttpUtils {
    private static volatile OkhttpUtils instance;
    private Handler handler = new Handler();

    public OkhttpUtils() {

    }

    public static OkhttpUtils getInstance() {
        if (null == instance) {
            synchronized (OkhttpUtils.class) {
                if (instance == null) {
                    instance = new OkhttpUtils();
                }
            }
        }
        return instance;
    }

    //get方法
    public void get(String url, final Class cls, final CallBack callBack) {
        //判断是否有路径，若没有，直接返回
        if (TextUtils.isEmpty(url)) {
            return;
        }

        StringBuffer sb = new StringBuffer();
        sb.append(url);

        /*if (url.contains("?")) {
            if (url.indexOf("?") == sb.length() - 1) {
            } else {
                sb.append("&");
            }
        } else {
            sb.append("?");
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }

        if (sb.indexOf("&") != -1) {
            sb.deleteCharAt(sb.lastIndexOf("&"));
        }
*/
        OkHttpClient client = new OkHttpClient.Builder().build();
        final Request request = new Request.Builder()
                .get()
                .url(sb.toString())
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String ss = response.body().string();
                final Object o = GsonUtils.getInstance().fromJson(ss, cls);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(o);
                    }
                });

            }
        });
    }
}





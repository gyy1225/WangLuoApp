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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wangluo.Activity.AddActivity;
import com.example.wangluo.Adapter.MyTrackRecyclerViewAdapter;
import com.example.wangluo.Adapter.MyTrackRecyclerViewAdapter;
import com.example.wangluo.Class.Content;
import com.example.wangluo.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ASUS on 2018/7/9.
 */

public class TrackListFragment extends Fragment {
    private View viewContent;
    private Context mContext;
    private RecyclerView recyclerView;
    private int mPosition;
    private String KEY = new String();

    private MyTrackRecyclerViewAdapter myTrackRecyclerViewAdapter;
    private List<Content> TrackContentList = new ArrayList<>();
    private List<Content> weiboTrackList = new ArrayList<>();
    private List<Content> zhihuTrackList = new ArrayList<>();
    private List<Content> shipinTrackList = new ArrayList<>();
    private List<Content> dongmanTrackList = new ArrayList<>();
    private List<Content> xiaoshuoTrackList = new ArrayList<>();
    private FloatingActionButton addButton;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    myTrackRecyclerViewAdapter = new MyTrackRecyclerViewAdapter(mContext, weiboTrackList);
                    recyclerView.setAdapter(myTrackRecyclerViewAdapter);
                    break;
                case 2:
                    myTrackRecyclerViewAdapter = new MyTrackRecyclerViewAdapter(mContext, zhihuTrackList);
                    recyclerView.setAdapter(myTrackRecyclerViewAdapter);
                    break;
                case 3:
                    myTrackRecyclerViewAdapter = new MyTrackRecyclerViewAdapter(mContext, shipinTrackList);
                    recyclerView.setAdapter(myTrackRecyclerViewAdapter);
                    break;
                case 4:
                    myTrackRecyclerViewAdapter = new MyTrackRecyclerViewAdapter(mContext, dongmanTrackList);
                    recyclerView.setAdapter(myTrackRecyclerViewAdapter);
                    break;
                case 5:
                    myTrackRecyclerViewAdapter = new MyTrackRecyclerViewAdapter(mContext, xiaoshuoTrackList);
                    recyclerView.setAdapter(myTrackRecyclerViewAdapter);
                    break;
                case 6:
                    Toast.makeText(mContext, "成功添加用户！", Toast.LENGTH_SHORT).show();
                    break;
                case 7:
                    Toast.makeText(mContext, "数据已刷新！", Toast.LENGTH_SHORT).show();
                    myTrackRecyclerViewAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(myTrackRecyclerViewAdapter);
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                case 0:
                    Toast.makeText(mContext, "服务器连接错误！", Toast.LENGTH_SHORT).show();
                    myTrackRecyclerViewAdapter = new MyTrackRecyclerViewAdapter(mContext, TrackContentList);
                    recyclerView.setAdapter(myTrackRecyclerViewAdapter);
                default:

            }
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.track_list_content, container, false);
        this.mContext = getActivity();
        recyclerView = (RecyclerView) viewContent.findViewById(R.id.rv_track_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout = viewContent.findViewById(R.id.track_fresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        KEY="3";
        addButton = viewContent.findViewById(R.id.track_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(viewContent.getContext(), AddActivity.class);
                intent.putExtra("POSITION", mPosition);
                startActivity(intent);

            }
        });
        mPosition = getArguments().getInt("position");
        saveKey();
        removeList();
        judgeKey();
        initList(mPosition);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                removeList();
                refreshList(mPosition);
            }
        });
       /* if(viewContent.getParent()==null) {
            initList(mPosition);
        }*/
        return viewContent;
    }

    public void judgeKey() {
        if (KEY == null) {
            sendKeyOkhhtpRequest();
            saveKey();
        } else ;
    }

    public void removeList() {
        weiboTrackList.clear();
        zhihuTrackList.clear();
        shipinTrackList.clear();
        dongmanTrackList.clear();
        xiaoshuoTrackList.clear();
    }

    public void initList(int mPosition) {
        switch (mPosition) {
            case 0:
                sendRequestWithOkHttp("http://haojie06.me:9999/get?sinablog,id=3", 1);
                break;
            case 1:
                sendRequestWithOkHttp("http://haojie06.me:9999/get?zhihu,id=3", 2);
                break;
            case 2:
                sendRequestWithOkHttp("http://haojie06.me:9999/get?followmedia,id=3", 3);
                break;
            case 3:
                sendRequestWithOkHttp("http://haojie06.me:9999/get?followmedia,id=3", 4);
                break;
            case 4:
                sendRequestWithOkHttp("http://haojie06.me:9999/get?followmedia,id=3", 5);
                break;
            default:

        }

    }

    private void refreshList(int position) {
        initList(position);
        Message message = new Message();
        message.what = 7;
        handler.sendMessage(message);

    }

    private void sendRequestWithOkHttp(final String url, final int i) {
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
                    showResponse(responseData, i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void showResponse(String response, int i) {
        String content[] = response.split("', '");
        String contents[] = new String[100];

        for (String mContent : content) {
            Content content1 = new Content();
            contents = mContent.split("\\$\\$");
            String str = contents[0].replace(" ", "");
            str = str.replace("[", "");
            str = str.replace("'", "");
            switch (str) {
                case "新浪微博":
                    content1.setResource(str);
                    content1.setUID(contents[1]);
                    content1.setImageID(contents[2]);
                    content1.setAuthor(contents[3]);
                    content1.setContentURL(contents[4]);
                    content1.setTitle("更新时间："+contents[6]+"\n"+contents[5]);
                    weiboTrackList.add(content1);
                    break;
                case "知乎":
                    content1.setResource(str);
                    content1.setUID(contents[1]);
                    content1.setImageID(contents[2]);
                    content1.setAuthor(contents[3]);
                    content1.setContentURL(contents[4]);
                    content1.setTitle(contents[5] + "   “" + contents[6] + "”"+ "\n" + contents[7]);
                    zhihuTrackList.add(content1);
                    break;
                case "腾讯视频":
                    content1.setResource(str);
                    //     content1.setGrade(contents[1]);
                    content1.setAuthor(contents[1]);
                    content1.setImageID(contents[2]);
                    content1.setContentURL(contents[3]);
                    content1.setTitle(contents[5]);
                    content1.setUID(contents[10]);
                    shipinTrackList.add(content1);
                    break;
                case "腾讯漫画":
                    content1.setResource(str);
                    content1.setGrade(contents[1]);
                    content1.setImageID(contents[2]);
                    content1.setTitleURL(contents[3]);
                    content1.setContentURL(contents[4]);
                    content1.setUID(contents[10]);
                    content1.setAuthor(contents[1] + "    by" + contents[6]);
                    content1.setTitle("最近更新:  " + contents[5] + "\n" + "更新时间：  " + contents[7]);
                    dongmanTrackList.add(content1);
                    break;
                case "起点中文网":
                    content1.setResource(str);
                    // content1.setGrade(contents[1]);
                    String str2 = contents[2].replace("\\r", "");
                    content1.setImageID(str2);
                    content1.setAuthor(contents[1] + "  by  " + contents[6]);
                    content1.setContentURL(contents[4]);
                    content1.setUID(contents[10]);
                    content1.setTitle("最近更新：  " + contents[5]);
                    xiaoshuoTrackList.add(content1);
                    break;
                default:
            }

        }
        Message message = new Message();
        switch (i) {
            case 1:
                message.what = 1;
                break;
            case 2:
                message.what = 2;
                break;
            case 3:
                message.what = 3;
                break;
            case 4:
                message.what = 4;
                break;
            case 5:
                message.what = 5;
                break;
            default:

        }

        handler.sendMessage(message);
    }

    public void saveKey() {
        FileOutputStream outputStream = null;
        BufferedWriter writer = null;
        try {
            outputStream = mContext.openFileOutput("KEY", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            try {
                writer.write(KEY);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void loadKey() {
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            inputStream = mContext.openFileInput("KEY");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            KEY = reader.readLine();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendKeyOkhhtpRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient mOkHttpClient = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://haojie06.me:9999/get?newkey")
                            .build();
                    Response response = mOkHttpClient.newCall(request).execute();
                    String responseData = response.body().string();
                    KEY = responseData;
                    Message message = new Message();
                    message.what = 6;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}


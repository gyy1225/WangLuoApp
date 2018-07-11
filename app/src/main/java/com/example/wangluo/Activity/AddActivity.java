package com.example.wangluo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wangluo.Adapter.MyReferRecyclerViewAdapter;
import com.example.wangluo.Class.Content;
import com.example.wangluo.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddActivity extends AppCompatActivity {
    private EditText et_add;
    private Button button_add;
    private int position;
    private ImageView add_image;
    private String mResponse;
    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    Toast.makeText(AddActivity.this, mResponse, Toast.LENGTH_SHORT).show();

                    AddActivity.this.finish();

                    break;
                case 0:
                    Toast.makeText(AddActivity.this, "添加失败！", Toast.LENGTH_SHORT).show();
                    break;
                default:

            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Intent intent = getIntent();
        position = intent.getIntExtra("POSITION", position);
        et_add = findViewById(R.id.add_edit);
        button_add = findViewById(R.id.add_button);
        add_image=findViewById(R.id.add_image);
        initView();
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UID = et_add.getText().toString();
//position    0:微博，1：知乎，2：腾讯视频，3：腾讯漫画，4：起点小说
                switch (position) {
                    case 0:
                        sendRequestWithOkHttp("http://haojie06.me:9999/add?sinablog,id=3,uid=" + UID);
                        break;
                    case 1:
                        sendRequestWithOkHttp("http://haojie06.me:9999/add?zhihu,id=3,uid=" + UID);
                        break;
                    case 2:
                        sendRequestWithOkHttp("http://haojie06.me:9999/add?tv,id=3,uid=" + UID);
                        break;
                    case 3:
                        sendRequestWithOkHttp("http://haojie06.me:9999/add?anime,id=3,uid=" + UID);
                        break;
                    case 4:
                        sendRequestWithOkHttp("http://haojie06.me:9999/add?novel,id=3,uid=" + UID);
                        break;
                }
            }
        });

    }
private void initView(){
        switch (position){
            case 0:
                add_image.setImageResource(R.drawable.weibo6);
                break;
            case 1:
                add_image.setImageResource(R.drawable.zhihu4);
                break;
            case 2:
                add_image.setImageResource(R.drawable.shipin1);
                break;
            case 3:
                add_image.setImageResource(R.drawable.dongman2);
                break;
            case 4:
                add_image.setImageResource(R.drawable.qidian2);
                break;
                default:
        }
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
        mResponse = response;
        Message message = new Message();
        message.what = 1;
        handler.sendMessage(message);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                AddActivity.this.finish();
        }
        return true;
    }
}



package com.example.wangluo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wangluo.Class.Content;
import com.example.wangluo.R;

public class AddWebActivity extends AppCompatActivity {
    private EditText et_add;
    private Button button_addweb;
    private EditText et_addID;
    private int position;
    private String mResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_web);
        Intent intent = getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.content_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        position = intent.getIntExtra("POSITION", position);
        et_add = findViewById(R.id.addweb_edit);
        et_addID=findViewById(R.id.addwebID_edit);
        button_addweb = findViewById(R.id.addweb_button);
        button_addweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent();
                intent2.putExtra("POSITION",position);
                String newWeb=et_add.getText().toString();
                String newWebID=et_addID.getText().toString();
                intent2.putExtra("NEWWEB",newWeb);
                intent2.putExtra("NEWWEBID",newWebID);
                Content content = new Content();
                content.setImageID2(R.drawable.wangluo3);
                content.setContentURL("http://" + newWeb + "/");//http://www.dangdang.com/  http://www.dangdang.com/
                content.setTitle(newWebID);
                content.setmPosition(position);
                content.save();
                Toast.makeText(AddWebActivity.this, "添加网站成功！", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK, intent2);
                finish();
//position    "科技""电影""读书""娱乐""生活""学业""体育""邮箱""华科""游戏""动漫""其他"

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                AddWebActivity.this.finish();
        }
        return true;
    }
    }


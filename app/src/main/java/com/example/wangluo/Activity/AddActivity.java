package com.example.wangluo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wangluo.R;

public class AddActivity extends AppCompatActivity {
private EditText et_add;
private Button button_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        et_add=findViewById(R.id.add_edit);
        button_add=findViewById(R.id.add_button);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UID=et_add.getText().toString();

            }
        });
    }
}

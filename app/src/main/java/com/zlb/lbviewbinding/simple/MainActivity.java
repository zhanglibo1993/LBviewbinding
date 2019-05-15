package com.zlb.lbviewbinding.simple;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnContentview;
    private Button btnBindView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnContentview = findViewById(R.id.btn_contentview);
        btnBindView = findViewById(R.id.btn_bindview);
        btnContentview.setOnClickListener(this);
        btnBindView.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_contentview:
                intent = new Intent(this, ContentViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_bindview:
                intent = new Intent(this, ViewInjectActivity.class);
                startActivity(intent);
                break;
        }

    }
}

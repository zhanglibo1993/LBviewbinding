package com.zlb.lbviewbinding.simple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zlb.lbviewbinding.ViewBinding;
import com.zlb.lbviewbinding.anno.ContentView;

@ContentView(R.layout.activity_content_view)
public class ContentViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewBinding.initWithSetcontentView(this);
    }
}

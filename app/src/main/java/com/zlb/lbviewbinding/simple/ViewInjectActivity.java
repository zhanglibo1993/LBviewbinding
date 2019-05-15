package com.zlb.lbviewbinding.simple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zlb.lbviewbinding.ViewBinding;
import com.zlb.lbviewbinding.anno.BindView;
import com.zlb.lbviewbinding.anno.OnClick;

public class ViewInjectActivity extends AppCompatActivity {
    @BindView(R.id.tv)
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inject);
        ViewBinding.init(this);
        tv.setText("lb bindview success");
    }


    @OnClick(R.id.btn)
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.btn:
                tv.setText("button click");
                break;
        }
    }
}

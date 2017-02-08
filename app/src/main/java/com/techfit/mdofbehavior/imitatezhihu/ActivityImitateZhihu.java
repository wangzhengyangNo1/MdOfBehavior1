package com.techfit.mdofbehavior.imitatezhihu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.techfit.mdofbehavior.R;

public class ActivityImitateZhihu extends AppCompatActivity {

    private EditText mBarEtInput;
    private Toolbar mToolbar;
    private ImageView mBarIvSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imitate_zhihu);

        initToolBar();
    }

    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mBarEtInput = (EditText) findViewById(R.id.bar_et_input);
        mBarIvSearch = (ImageView) findViewById(R.id.bar_iv_search);
        mBarIvSearch.setOnClickListener(clickListener);
        setSupportActionBar(mToolbar);
    }


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.bar_iv_search:
                    String input = mBarEtInput.getText().toString();
                    Toast.makeText(ActivityImitateZhihu.this, TextUtils.isEmpty(input)?"请输入内容":"正在搜索：" + input, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

}

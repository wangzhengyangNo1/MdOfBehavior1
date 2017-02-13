package com.techfit.mdofbehavior.imitatezhihu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.techfit.mdofbehavior.ListRecyclerAdapter;
import com.techfit.mdofbehavior.R;
import com.techfit.mdofbehavior.utils.Utils;

import java.util.ArrayList;

public class ActivityImitateZhihu extends AppCompatActivity {

    private EditText mBarEtInput;
    private Toolbar mToolbar;
    private ImageView mBarIvSearch;
    private RecyclerView mRvList;

    private static final int ITEM_COUNT = 20;
    ArrayList<String> mList = new ArrayList<String>();
    private LinearLayoutManager mLinearLayoutManager;
    private ListRecyclerAdapter mRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imitate_zhihu);

        initToolBar();

        initData();
        initView();
        setEvents();

    }

    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mBarEtInput = (EditText) findViewById(R.id.bar_et_input);
        mBarIvSearch = (ImageView) findViewById(R.id.bar_iv_search);
        mBarIvSearch.setOnClickListener(clickListener);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Utils.closeInputPopup(this);
    }

    private void initView() {
        mRvList = (RecyclerView) findViewById(R.id.rv_list);
        mRvList.setHasFixedSize(true);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setSmoothScrollbarEnabled(true);

        mRvList.setLayoutManager(mLinearLayoutManager);
        mRvList.setItemAnimator(new DefaultItemAnimator());

        mRvAdapter = new ListRecyclerAdapter(mList);
        mRvList.setAdapter(mRvAdapter);


    }
    private void initData() {
        for (int i = 0; i < ITEM_COUNT; i++) {
            mList.add("我是第" + i + "个Item");
        }
    }

    private void setEvents() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
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

package com.techfit.mdofbehavior.returntop;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.techfit.mdofbehavior.R;
import com.techfit.mdofbehavior.utils.AnimatorUtil;

import java.util.ArrayList;

public class ActivityReturnTop extends AppCompatActivity {

    private Toolbar mToolbar;
    public static final String TITLE = "回到顶部";
    public static final int ITEM_COUNT = 20;
    private boolean isFabInitialized = false;

    private RecyclerView mRvList;
    private LinearLayoutManager mLinearLayoutManager;
    private ListRecyclerAdapter mListAdapter;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_top);

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle(TITLE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        mRvList = (RecyclerView) findViewById(R.id.rv_list);
        mRvList.setHasFixedSize(true);

        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < ITEM_COUNT; i++) {
            list.add("我是第" + i + "个item");
        }

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setSmoothScrollbarEnabled(true);
        mRvList.setLayoutManager(mLinearLayoutManager);
        mRvList.setItemAnimator(new DefaultItemAnimator());
        mListAdapter = new ListRecyclerAdapter(list);
        mRvList.setAdapter(mListAdapter);
        mFab = (FloatingActionButton) findViewById(R.id.fab_return_top);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinearLayoutManager.scrollToPosition(0);
                hideFab();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!isFabInitialized) {
            isFabInitialized = true;
            hideFab();
        }
    }

    private void hideFab() {
        mFab.postDelayed(new Runnable() {
            @Override
            public void run() {
                AnimatorUtil.scaleHide(mFab, new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {

                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        mFab.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                });
            }
        },500);
    }
}

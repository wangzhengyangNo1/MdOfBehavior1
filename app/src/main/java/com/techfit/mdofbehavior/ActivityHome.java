package com.techfit.mdofbehavior;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.techfit.mdofbehavior.bottombehavior.ActivityBottomPopup;
import com.techfit.mdofbehavior.imitatezhihu.ActivityImitateZhihu;
import com.techfit.mdofbehavior.returntop.ActivityReturnTop;
import com.techfit.mdofbehavior.slidingdel.ActivitySlidingDelete;

public class ActivityHome extends AppCompatActivity {

    private Button btnBottomPopup;
    private Button btnReturnTop;
    private Button btnImitateZhihu;
    private Button btnSlidingDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

        setEvents();

    }

    /**
     * 初始化控件
     */
    private void initView() {
        btnBottomPopup = (Button) findViewById(R.id.btn_bottom_popup);
        btnReturnTop = (Button) findViewById(R.id.btn_return_top);
        btnImitateZhihu = (Button) findViewById(R.id.btn_imitate_zhihu);
        btnSlidingDelete = (Button) findViewById(R.id.btn_sliding_delete);
    }

    /**
     * 为控件注册监听
     */
    private void setEvents() {
        btnBottomPopup.setOnClickListener(clickListener);
        btnReturnTop.setOnClickListener(clickListener);
        btnImitateZhihu.setOnClickListener(clickListener);
        btnSlidingDelete.setOnClickListener(clickListener);
    }

    /**
     * 点击监听
     */
    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_bottom_popup:
                    startActivity(ActivityBottomPopup.class);
                    break;
                case R.id.btn_return_top:
                    startActivity(ActivityReturnTop.class);
                    break;
                case R.id.btn_imitate_zhihu:
                    startActivity(ActivityImitateZhihu.class);
                    break;
                case R.id.btn_sliding_delete:
                    startActivity(ActivitySlidingDelete.class);
                    break;
            }
        }
    };

    /**
     * 启动Activity
     * @param pActivityClass
     */
    private void startActivity(Class pActivityClass){
        startActivity(new Intent(this, pActivityClass));
    }
}

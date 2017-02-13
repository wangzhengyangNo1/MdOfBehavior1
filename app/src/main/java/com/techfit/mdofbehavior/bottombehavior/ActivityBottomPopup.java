package com.techfit.mdofbehavior.bottombehavior;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.techfit.mdofbehavior.R;

public class ActivityBottomPopup extends AppCompatActivity {

    private BottomSheetBehavior botmSheetBehavior;
    private Button btnDialogCtrl;
    private Button btnSheetCtrl;
    private LayoutInflater mInflater;
    private BottomSheetDialog mBottomSheetDialog;
    private BottomSheetBehavior mDialogBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_popup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        sheetCtrl();
        dialogCtrl();
    }

    private void sheetCtrl() {
        btnSheetCtrl = (Button) findViewById(R.id.btn_sheet_control);
        //通过BottomSheetbehavior.from(view),获得view的behavior（BottomSheetBehavior）
        botmSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.tab_layout));
        botmSheetBehavior.setHideable(true);//只有设置这个属性（设置为可隐藏），才能切换隐藏显示
        //botmSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);//设置为可隐藏后，可以设置一下状态
        btnSheetCtrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int state = botmSheetBehavior.getState();
                switch (state) {
                    //如果状态（state）是展开（显示），点击按钮后，状态设为隐藏；
                    case BottomSheetBehavior.STATE_EXPANDED:
                        state = BottomSheetBehavior.STATE_HIDDEN;
                        break;
                    //如果状态是折叠或隐藏，点击按钮后，状态设为展开。
                    case BottomSheetBehavior.STATE_COLLAPSED://xml中设置好behavior后，默认是是折叠（collapsed）的。
                    case BottomSheetBehavior.STATE_HIDDEN:
                        state = BottomSheetBehavior.STATE_EXPANDED;
                        break;
                }
                Log.i("state", "----> state = " + state);
                botmSheetBehavior.setState(state);
                Log.i("state", "--1--> state = " + botmSheetBehavior.getState());
            }
        });
    }

    private void dialogCtrl() {
        initBottomSheetDialog();
        btnDialogCtrl = (Button) findViewById(R.id.btn_dialog_control);
        btnDialogCtrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBottomSheetDialog.isShowing()) {
                    mBottomSheetDialog.dismiss();
                } else {
                    mBottomSheetDialog.show();
                }
            }
        });
        setBottomSheetCallback();
    }

    public static final int ITEM_COUNT = 12;

    private void initBottomSheetDialog() {
        mBottomSheetDialog = new BottomSheetDialog(this, android.R.style.Theme_Material_Dialog_Alert);
        //① setContentView(View view);
        RecyclerView rv = (RecyclerView) mInflater.inflate(R.layout.dialog_sheet_rv, null, false);
        //如果是一个普通的View（非RecyclerView、NestedScrollView）将不会继续往上滑动，
        // 下面的内容会继续跟着出来，但是同样可以向下滑动隐藏，也可以调用dismiss和close关闭。
        mBottomSheetDialog.setContentView(rv);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                TextView tv = (TextView) mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                VHolder vHolder = new VHolder(tv);
                return vHolder;
            }

            @SuppressWarnings("deprecation")
            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                VHolder vHolder = (VHolder) holder;
                TextView tv = (TextView) vHolder.itemView;
                tv.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                StringBuilder builder = new StringBuilder();
                int color = 0xFF0000;
                builder.append("我是第<font color='"+color+"'>");
                builder.append(position);
                builder.append("</font>个");
                tv.setText(Html.fromHtml(builder.toString()));
            }

            @Override
            public int getItemCount() {
                return ITEM_COUNT;
            }

            class VHolder extends RecyclerView.ViewHolder {

                public VHolder(View itemView) {
                    super(itemView);
                }
            }

        });

    }

    private void setBottomSheetCallback() {
        FrameLayout bottomSheet = (FrameLayout) mBottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);

       /* View touchOutsideView = mBottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.touch_outside);
        touchOutsideView.setBackgroundColor(Color.argb(0x60,0x00,0x00,0x20));
        bottomSheet.setBackgroundColor(Color.argb(0x50,0x00,0x00,0x20));*/

        mDialogBehavior = BottomSheetBehavior.from(bottomSheet);
        mDialogBehavior.setHideable(true);
        mDialogBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    mBottomSheetDialog.dismiss();
                    mDialogBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

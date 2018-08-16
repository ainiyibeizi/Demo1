package com.briup.pgc.anew.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.briup.pgc.anew.R;

public class BasePager {
    public Activity mActivity;
    public TextView tv_title;
    public FrameLayout flContent;//空的帧布局
    public View mRootView;//当前布局对象

    public BasePager(Activity activity) {
        mActivity = activity;
        mRootView = initView();
    }
    //初始化布局
    public View initView(){
        View view=View.inflate(mActivity, R.layout.base_pager,null);
        tv_title=view.findViewById(R.id.tv_title);
        flContent=view.findViewById(R.id.fl_content);
        return view;
    }
    //初始化数据
    public void initData(){

    }
}

package com.briup.pgc.anew.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;
import com.briup.pgc.anew.base.BasePager;

public class VipPager extends BasePager {
    public VipPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        //要给帧布局填充对象
        TextView view=new TextView(mActivity);
        view.setText("VIP");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);
        flContent.addView(view);
        //修改页面标题
        tv_title.setText("VIP标题");
    }
}

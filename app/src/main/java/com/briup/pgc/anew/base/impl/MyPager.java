package com.briup.pgc.anew.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.briup.pgc.anew.R;
import com.briup.pgc.anew.adapter.RecycleAdapter;
import com.briup.pgc.anew.base.BasePager;
import com.briup.pgc.anew.bean.NewBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyPager extends BasePager implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recycle_view;
    private LinearLayoutManager mLayoutManager;
    public List<NewBean.NewsBean> list=new ArrayList<>();
    private RecycleAdapter mAdapter;
    private SwipeRefreshLayout swipe_refresh;
    private int mLastVisibleItemPosition;
    public MyPager(Activity activity) {
        super(activity);
        listnew();
    }

    @Override
    public void initData() {
        //要给帧布局填充对象
        View view=View.inflate(mActivity, R.layout.recycle_layout,null);
        recycle_view = view.findViewById(R.id.recycle_view);
        swipe_refresh = view.findViewById(R.id.swipe_refresh);
        swipe_refresh.setColorSchemeColors(Color.BLUE);//设置进度颜色可以写多个
        swipe_refresh.setOnRefreshListener(this);

        flContent.addView(view);
        //修改页面标题
        tv_title.setText("运动新闻");
        initView();
        recycle();
        swipe_refresh.setColorSchemeColors(Color.RED, Color.BLUE);

    }

    public void recycle() {
        recycle_view.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        mLayoutManager = new LinearLayoutManager(mActivity);
        recycle_view.setLayoutManager(mLayoutManager);

        mAdapter = new RecycleAdapter(list);
        recycle_view.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecycleAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mActivity, position + "短点击了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(mActivity, position + "长点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void listnew(){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://10.0.3.2:8888/runssm1/new.action").build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                gson(s);
            }
        });
    }
    public void gson(String json){
        Gson gson=new Gson();
        NewBean newBean = gson.fromJson(json, NewBean.class);
        List<NewBean.NewsBean> news = newBean.getNews();
        list.addAll(news);
    }
    @Override
    public void onRefresh() {
        initPullRefresh();
    }
    //下拉刷新
    private void initPullRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<NewBean.NewsBean> headDatas = new ArrayList<>();
                /*for (int i = 20; i <30 ; i++) {

                    headDatas.add("Heard Item "+i);
                }*/
                mAdapter.addHeaderItem(headDatas);
                 //mAdapter.notifyDataSetChanged();
                //刷新完成
                swipe_refresh.setRefreshing(false);
                Toast.makeText(mActivity, "更新了 "+headDatas.size()+" 条目数据", Toast.LENGTH_SHORT).show();
            }
        },3000);
    }
}

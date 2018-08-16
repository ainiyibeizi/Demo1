package com.briup.pgc.anew.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.briup.pgc.anew.R;
import com.briup.pgc.anew.base.BasePager;
import com.briup.pgc.anew.base.impl.FindPager;
import com.briup.pgc.anew.base.impl.MyPager;
import com.briup.pgc.anew.base.impl.VipPager;

import java.util.ArrayList;

public class ContentFragment extends Fragment {
    private ViewPager mViewPager;
    private RadioGroup main_rg_choose;
    private RadioButton main_rb_recom,main_rb_find,main_rb_vip;
    private ArrayList<BasePager> mPagers;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, null);
        initView(view);
        return view;
    }

    public void initView(View view) {

        mViewPager=view.findViewById(R.id.vp_content);
        main_rg_choose=view.findViewById(R.id.main_rg_choose);
        main_rb_recom=view.findViewById(R.id.main_rb_recom);
        main_rb_find=view.findViewById(R.id.main_rb_find);
        main_rb_vip=view.findViewById(R.id.main_rb_vip);
        initDate();
    }

    public void initDate() {
        mPagers=new ArrayList<>();
        //添加标签页
        mPagers.add(new MyPager(getActivity()));
        mPagers.add(new FindPager(getActivity()));
        mPagers.add(new VipPager(getActivity()));
        mViewPager.setAdapter(new ContentAdapter());
        main_rb_recom.setChecked(true);
        //给低栏标签添加监听切换事件
        main_rg_choose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_rb_recom:
                        mViewPager.setCurrentItem(0,false);//表示是否有切换动画

                        break;
                    case R.id.main_rb_find:
                        mViewPager.setCurrentItem(1,false);//表示是否有切换动画
                        break;
                    case R.id.main_rb_vip:
                        mViewPager.setCurrentItem(2,false);//表示是否有切换动画
                        break;

                    default:
                        break;
                }

            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                BasePager pager = mPagers.get(position);
                pager.initData();//初始化数据

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // TODO Auto-generated method stub

            }
        });
        //手动加载第一页数据
        mPagers.get(0).initData();
    }
    ////给低栏标签添加监听切换事件

    class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            // TODO Auto-generated method stub
            return view==object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager = mPagers.get(position);

            View view=pager.mRootView;//获得当前页面对象的布局
            container.addView(view);
            return view;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }
}

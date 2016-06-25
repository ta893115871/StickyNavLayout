package com.gxz.stickynavlayout.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.gxz.PagerSlidingTabStrip;
import com.gxz.library.StickyNavLayout;
import com.gxz.stickynavlayout.R;
import com.gxz.stickynavlayout.adapter.FragmentsViewPagerAdapter;
import com.gxz.stickynavlayout.fragments.BaseFragment;
import com.gxz.stickynavlayout.fragments.EmptyListViewFragment;
import com.gxz.stickynavlayout.fragments.GridViewWithHeaderAndFooterFragment;
import com.gxz.stickynavlayout.fragments.ListViewFragment;
import com.gxz.stickynavlayout.fragments.RecycleViewFragment;
import com.gxz.stickynavlayout.fragments.ScrollViewFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 顾修忠-guxiuzhong@youku.com/gfj19900401@163.com
 */
public class TopOperateActivity extends AppCompatActivity {

    @Bind(R.id.id_stickynavlayout_indicator)
    PagerSlidingTabStrip pagerSlidingTabStrip;
    @Bind(R.id.id_stickynavlayout_viewpager)
    ViewPager viewPager;
    @Bind(R.id.show)
    Button showButton;
    @Bind(R.id.hide)
    Button hideButton;
    @Bind(R.id.id_stick)
    StickyNavLayout stickyNavLayout;
    @Bind(R.id.id_btn_1)
    Button button1;
    @Bind(R.id.id_btn_2)
    Button button2;
    @Bind(R.id.id_name_layout)
    LinearLayout linearLayoutLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_operate_stick);
        ButterKnife.bind(this);

        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(ListViewFragment.newInstance());
        fragments.add(RecycleViewFragment.newInstance());
        fragments.add(EmptyListViewFragment.newInstance());
        fragments.add(ScrollViewFragment.newInstance());
        fragments.add(GridViewWithHeaderAndFooterFragment.newInstance());

        FragmentsViewPagerAdapter adapter = new FragmentsViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        pagerSlidingTabStrip.setViewPager(viewPager);
        pagerSlidingTabStrip.setOnPageChangeListener(mPageChangeListener);

        stickyNavLayout.setStickNavAndScrollToNav();
    }

    @OnClick(R.id.show)
    public void show() {
        button1.setVisibility(View.VISIBLE);
//        button2.setVisibility(View.VISIBLE);
        linearLayoutLayout.setVisibility(View.VISIBLE);
        stickyNavLayout.updateTopViews();
    }

    @OnClick(R.id.hide)
    public void hide() {
        button1.setVisibility(View.GONE);
//        button2.setVisibility(View.GONE);
        linearLayoutLayout.setVisibility(View.GONE);
        stickyNavLayout.updateTopViews();
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

package com.gxz.stickynavlayout.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class TopViewOverOneScreenActivity extends AppCompatActivity {

    @Bind(R.id.id_stickynavlayout_indicator)
    PagerSlidingTabStrip pagerSlidingTabStrip;
    @Bind(R.id.id_stickynavlayout_viewpager)
    ViewPager viewPager;
    @Bind(R.id.store_house_ptr_frame)
    PtrClassicFrameLayout mPtrFrame;
    @Bind(R.id.id_stick)
    StickyNavLayout stickyNavLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_view_over_one_screen);
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


        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                new Work().execute();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                //设置下拉刷新的条件
                return stickyNavLayout.getScrollY() == 0;
//                return false;
            }
        });
        // the following are default settings
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrame.setPullToRefresh(false);
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 150);
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

    private class Work extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(2000);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mPtrFrame.refreshComplete();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

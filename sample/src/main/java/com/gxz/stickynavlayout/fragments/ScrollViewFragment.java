package com.gxz.stickynavlayout.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gxz.stickynavlayout.R;

/**
 * @author 顾修忠-guxiuzhong@youku.com/gfj19900401@163.com
 * @Title: ScrollViewFragment
 * @Package com.gxz.stickynavlayout.fragments
 * @Description:
 * @date 15/12/31
 * @time 下午2:22
 */
public class ScrollViewFragment extends BaseFragment {
    public static ScrollViewFragment newInstance() {

        Bundle args = new Bundle();

        ScrollViewFragment fragment = new ScrollViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scroll_view, container, false);
    }

    @Override
    public String getTitle() {
        return " ScrollView";
    }
}

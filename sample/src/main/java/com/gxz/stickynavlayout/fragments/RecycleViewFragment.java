package com.gxz.stickynavlayout.fragments;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gxz.stickynavlayout.R;
import com.gxz.stickynavlayout.adapter.RecycleViewAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RecycleViewFragment extends BaseFragment {


    @Bind(R.id.id_stickynavlayout_innerscrollview)
    RecyclerView mRecycleView;

    public static RecycleViewFragment newInstance() {
        RecycleViewFragment fragment = new RecycleViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycle_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
//        mRecycleView.setLayoutManager(gridLayoutManager);//这里用线性宫格显示 类似于grid view
//
//        if (mRecycleView != null) {//设置头部及底部View占据整行空间
//            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    return
//                    position%9==0 ? gridLayoutManager.getSpanCount() : 1;
//                }
//            });
//        }


        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false));
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("RecycleView----item-->>>>" + i);
        }

        final RecycleViewAdapter adapter = new RecycleViewAdapter(list);
        mRecycleView.setAdapter(adapter);
        adapter.setOnItemOnClickListener(new RecycleViewAdapter.OnItemOnClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(getActivity(), "click " + position, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(getActivity(), "Long-click " + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public String getTitle() {
        return "RecycleView";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}

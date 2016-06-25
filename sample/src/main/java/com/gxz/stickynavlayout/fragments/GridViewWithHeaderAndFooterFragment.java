package com.gxz.stickynavlayout.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gxz.stickynavlayout.R;
import com.gxz.stickynavlayout.adapter.GridViewAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.GridViewWithHeaderAndFooter;

/**
 * @author 顾修忠-guxiuzhong@youku.com/gfj19900401@163.com
 * @Title: GridViewWithHeaderAndFooterFragment
 * @Package com.gxz.stickynavlayout.fragments
 * @Description:
 * @date 16/1/1
 * @time 下午4:18
 */
public class GridViewWithHeaderAndFooterFragment extends BaseFragment {

    private int visibleLastIndex = 0; // 最后的可视项索引
    @Bind(R.id.id_stickynavlayout_innerscrollview)
    GridViewWithHeaderAndFooter gridView;
    @Bind(R.id.empty)
    View emptView;

    private GridViewAdapter adapter;

    public static GridViewWithHeaderAndFooterFragment newInstance() {

        Bundle args = new Bundle();

        GridViewWithHeaderAndFooterFragment fragment = new GridViewWithHeaderAndFooterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setGridViewHeaderAndFooter();

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("-----<>>>>>" + i);
        }

        adapter = new GridViewAdapter(getActivity(), list);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), " Clicked " + position, Toast.LENGTH_SHORT).show();
            }
        });
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int itemsLastIndex = adapter.getCount() - 1;

                System.out.println("-------itemsLastIndex>>>>" + itemsLastIndex);
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && visibleLastIndex >= itemsLastIndex) {
                    // 如果是自动加载,可以在这里放置异步加载数据的代码
                    System.out.println("------------ok load more--");
//                    new Work().execute();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                System.out.println("--------firstVisibleItem:" + firstVisibleItem);
                System.out.println("--------visibleItemCount:" + visibleItemCount);
                visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
                System.out.println("-------visibleLastIndex>>>" + visibleLastIndex);
            }
        });

        gridView.setEmptyView(emptView);
    }

    private void setGridViewHeaderAndFooter() {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

        View headerView = layoutInflater.inflate(R.layout.layout_header, gridView, false);
        View footerView = layoutInflater.inflate(R.layout.layout_footer, gridView, false);

        //locate views
        TextView headerText = (TextView) headerView.findViewById(R.id.text);
        TextView footerText = (TextView) footerView.findViewById(R.id.text);

        headerText.setText("GridView Header");
        footerText.setText("GridView Footer");

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 200);
        headerView.setLayoutParams(lp);

        gridView.addHeaderView(headerView);
        gridView.addFooterView(footerView);
    }

    private class Work extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            SystemClock.sleep(2000);
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add("-----<>>>>>" + i);
            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> list) {
            super.onPostExecute(list);
            adapter.addAll(list);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public String getTitle() {
        return GridViewWithHeaderAndFooterFragment.class.getSimpleName();
    }
}

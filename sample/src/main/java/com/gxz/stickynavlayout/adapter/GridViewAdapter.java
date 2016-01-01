package com.gxz.stickynavlayout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.gxz.stickynavlayout.R;

import java.util.ArrayList;

/**
 * @author 顾修忠-guxiuzhong@youku.com/gfj19900401@163.com
 * @Title: MyAdapter
 * @Package com.example.guxiuzhong.grideviewaddfooter
 * @Description:
 * @date 15/12/19
 * @time 上午12:19
 */
public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> list = new ArrayList<>();

    public GridViewAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.gv_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        return convertView;
    }

    public void addAll(ArrayList<String> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    private static final class ViewHolder {
        private ImageView imageView;

        public ViewHolder(View convertView) {
            this.imageView = (ImageView) convertView.findViewById(R.id.id_image);
        }
    }
}

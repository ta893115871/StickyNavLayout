package com.gxz.stickynavlayout.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gxz.stickynavlayout.R;


/**
 * @author 顾修忠-guxiuzhong@youku.com/gfj19900401@163.com
 * @Title: MainActivity
 * @Package com.gxz.stickynavlayout.activity
 * @Description: MainActivity
 * @date 15/12/29
 * @time 下午12:17
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void SimpleStick(View view) {
        startActivity(new Intent(this, SimpleStickActivity.class));
    }

    public void StickPullRefresh(View view) {
        startActivity(new Intent(this, PullToRefreshStickActivity.class));
    }

    public void TopViewOverOne(View view) {
        startActivity(new Intent(this, TopViewOverOneScreenActivity.class));
    }

    public void SwipeRefresh(View view) {
        startActivity(new Intent(this, SwipeRefreshLayoutActivity.class));
    }

    public void onClickTopOperate(View view) {
        startActivity(new Intent(this, TopOperateActivity.class));
    }
}

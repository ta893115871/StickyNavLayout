# StickyNavLayout 悬浮控件
该项目是在一个开源项目的基础上修改的</p>
原项目地址:
<https://github.com/hongyangAndroid/Android-StickyNavLayout/>
修改如下:</p>
1.在原基础上支持对内容listview等控件空布局的滑动支持</p>
2.修改当刚刚悬浮的时候抬起手触发onItemClick事件的bug</p>
3.修改手快速滑动的时候触发onItemClick事件的bug</p>
4.增加一些自定义属性,后文有介绍</p>
5.增加是否处于悬浮的回调接口,和滑动到悬浮状态的比例:0~1 ;1~0 之间变化的数字</p>
6.增加对GridView-GridViewWithHeaderAndFooterFragment(支持header和footer的GridView)</p>
7.修改其它一些bug</p>
8.将原文迁移到AS中</p>
9.将此项目发布到JCenter,方便大家的使用哦</p>

#GridViewWithHeaderAndFooterFragment
<https://github.com/liaohuqiu/android-GridViewWithHeaderAndFooter/>
#声明
本项目纯属开源,只是为了更好的提高Android的开发技术,小宝贝们~~~~~

# 效果图
<img src="/screenshots/stick_0.gif"/></p>
<img src="/screenshots/stick_1.gif"/></p>
<img src="/screenshots/stick_2.gif"/>
#自定义属性
```java
<resources>
    <declare-styleable name="StickNavLayout">
        <!--默认是否悬停tab-->
        <attr name="isStickNav" format="boolean" />
        <!--距离悬浮的位置-->
        <attr name="stickOffset" format="dimension" />
    </declare-styleable>
</resources>
```
#stickOffset 属性细说
现在悬浮是当top区域完全滑动到屏幕外面时,中间的悬浮区域悬浮起来了,如果你有这么一种需求是距离悬浮区域有一定的距离比如(40dp)可以用这个属性,满足需求.

#使用时的注意事项
注意控件id的设置</p>
top区域:id必须为: android:id="@+id/id_stickynavlayout_topview"</p>
悬浮区域:id必须为: android:id="@+id/id_stickynavlayout_indicator"</p>
内容区域:id必须为:  android:id="@+id/id_stickynavlayout_viewpager"</p>
内容区域</p>
1.比需为ViewPager或者其子类</p>
2.ViewPager的内容可以是Fragment,如果Fragment想用ListView,RecycleView等需要设置其id为:</p>
 android:id="@+id/id_stickynavlayout_innerscrollview"</p>
比如:</p>
```java
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.gxz.stickynavlayout.fragments.RecycleViewFragment">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/id_stickynavlayout_innerscrollview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</FrameLayout>
```
or
```java
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">


    <ScrollView
        android:id="@+id/id_stickynavlayout_innerscrollview"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:text="@string/msg"
            android:textSize="20sp" />

    </ScrollView>
</LinearLayout>
```
#使用
```java
    <com.gxz.library.StickyNavLayout
        android:id="@+id/id_stick"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        stick:isStickNav="true">

        <LinearLayout
            android:id="@id/id_stickynavlayout_topview"
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:background="#fee"
            android:orientation="vertical">

            <TextView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:gravity="center"
                android:text="top-view"
                android:textAllCaps="true"
                android:textSize="30sp" />
        </LinearLayout>

        <com.gxz.PagerSlidingTabStrip
            android:id="@+id/id_stickynavlayout_indicator"
            android:layout_width="match_parent"
            android:layout_height="48dp"
            android:textSize="16sp"
            pst:pstsDividerColor="@color/colorPrimary"
            pst:pstsIndicatorColor="@color/colorPrimary"
            pst:pstsIndicatorHeight="5dp"
            pst:pstsScaleZoomMax="0"
            pst:pstsShouldExpand="false"
            pst:pstsTextSelectedColor="#303F9F"
            pst:pstsUnderlineColor="@color/colorPrimary" />

        <android.support.v4.view.ViewPager
            android:id="@id/id_stickynavlayout_viewpager"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

    </com.gxz.library.StickyNavLayout>
```
#TopView(顶部区域大于一屏)
```java
<?xml version="1.0" encoding="utf-8"?>
<in.srain.cube.views.ptr.PtrClassicFrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:cube_ptr="http://schemas.android.com/apk/res-auto"
    xmlns:pst="http://schemas.android.com/apk/res-auto"
    xmlns:stick="http://schemas.android.com/apk/res-auto"
    android:id="@+id/store_house_ptr_frame"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    cube_ptr:ptr_duration_to_close="300"
    cube_ptr:ptr_duration_to_close_header="2000"
    cube_ptr:ptr_keep_header_when_refresh="true"
    cube_ptr:ptr_pull_to_fresh="false"
    cube_ptr:ptr_ratio_of_header_height_to_refresh="1.2"
    cube_ptr:ptr_resistance="1.7">

    <com.gxz.library.StickyNavLayout
        android:id="@+id/id_stick"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        stick:isStickNav="false">
            <!--顶部区域为ScrollView-->
        <ScrollView
            android:id="@id/id_stickynavlayout_topview"
            android:layout_width="match_parent"
            android:layout_height="wrap_content">
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:background="#fee"
                android:orientation="vertical">

                <TextView
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:gravity="center"
                    android:text="@string/msg"
                    android:textAllCaps="true"
                    android:textSize="20sp" />
            </LinearLayout>
        </ScrollView>

        <com.gxz.PagerSlidingTabStrip
            android:id="@+id/id_stickynavlayout_indicator"
            android:layout_width="match_parent"
            android:layout_height="48dp"
            android:textSize="16sp"
            pst:pstsDividerColor="@color/colorPrimary"
            pst:pstsIndicatorColor="@color/colorPrimary"
            pst:pstsIndicatorHeight="5dp"
            pst:pstsScaleZoomMax="0"
            pst:pstsShouldExpand="false"
            pst:pstsTextSelectedColor="#303F9F"
            pst:pstsUnderlineColor="@color/colorPrimary" />

        <android.support.v4.view.ViewPager
            android:id="@id/id_stickynavlayout_viewpager"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

    </com.gxz.library.StickyNavLayout>
</in.srain.cube.views.ptr.PtrClassicFrameLayout>

```
# Java  代码使用
相信你可以点一下就知道什么用了 .... 是不是呢?小宝贝~~~!!

#接口回调
```java
 stickyNavLayout.setOnStickStateChangeListener(new StickyNavLayout.onStickStateChangeListener() {
            @Override
            public void isStick(boolean isStick) {
                
            }

            @Override
            public void scrollPercent(float percent) {

            }
        });
```
#Maven

```java
<dependency>
        <groupId>com.gxz.stickynavlayout</groupId>
        <artifactId>library</artifactId>
        <version>1.3.1</version>
        <type>jar</type>
        <classifier>sources</classifier>
</dependency>
```
# Gradle

```java

    compile 'com.gxz.stickynavlayout:library:1.3.1'
    
```
#Demo 
Demo中的导航控件是:PagerSlidingTabStrip</p>
是我一个开源项目:
<https://github.com/ta893115871/PagerSlidingTabStrip/>

#V1.1版本
1.增加如果TopView大于一屏TopView无法显示的问题,比如顶部区域为ScrollView</p>
2.支持顶部区域可以是ScrollView

#V1.2版本
1.修复TOP区域中不支持控件动态GONE/VISIBLE的BUG</p>
使用用法,设置完GONE/VISIBLE后调用updateTopViews();方法</p>
```java
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
```

再次 如果你动态修改了top区域中有关view(比如TextView的文本-这时高度就变了),这是你需要在修改完后重新调用如下方法,更新高度</p>
比如:</p>
```java
 textViewMsg.setText(getResources().getString(R.string.msg));
 stickyNavLayout.updateTopViews();
```
从而可以避免topView有空白或者高度不够的问题</p>

2.增加setStickNavAndScrollToNav方法.</p>
该方法的意思是:java代码动态设置悬浮,并自动滚动到悬浮位置(即把top区域滚动上去),详细见demo中的TopOperateActivity

#V1.3版本
修改因1.2版本造成的stickOffset不起作用的问题
#V1.3.1版本
修改stickOffset造成内容区域底部显示不全问题,最近丢三落四的!

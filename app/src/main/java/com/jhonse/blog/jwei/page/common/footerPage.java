package com.jhonse.blog.jwei.page.common;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jhonse.blog.jwei.MainActivity;
import com.jhonse.blog.jwei.R;
import com.jhonse.blog.jwei.page.tool.indexPage;

import java.util.ArrayList;
import java.util.List;

public class footerPage extends Page {
    private static MainActivity ma;
    // 用来放置界面切换
    private static ViewPager mViewPager;
    // 初始化View适配器
    private static PagerAdapter mPagerAdapter;
    // 用来存放Tab01-04
    private static List<View> mViews = new ArrayList<View>();
    // 四个Tab，每个Tab包含一个按钮
    private static LinearLayout mTabTool;
    private static LinearLayout mTabChat;
    private static LinearLayout mTabFind;
    private static LinearLayout mTabMy;
    // 四个图标
    private static TextView mToolIcon;
    private static TextView mChatIcon;
    private static TextView mFindIcon;
    private static TextView mMyIcon;
    //四个标题
    private static TextView mToolTitle;
    private static TextView mChatTitle;
    private static TextView mFindTitle;
    private static TextView mMyTitle;
    //初始化变量
    private static boolean positionValue[] = new boolean[4];

    public static void init(MainActivity mas){
        ma = mas;
        initView();
    }

    /**
     * 初始化视图
     */
    private static void initView(){
        mViewPager = (ViewPager) ma.findViewById(R.id.id_viewpage);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             *ViewPage左右滑动时
             */
           @Override
            public void onPageSelected(int arg0) {
                int currentItem = mViewPager.getCurrentItem();
                switch (currentItem) {
                    case 0:
                        resetImg();
                        mToolIcon.setTextColor(Color.parseColor("#00aaee"));
                        mToolTitle.setTextColor(Color.parseColor("#00aaee"));
                        break;
                    case 1:
                        resetImg();
                        mChatIcon.setTextColor(Color.parseColor("#00aaee"));
                        mChatTitle.setTextColor(Color.parseColor("#00aaee"));
                        break;
                    case 2:
                        resetImg();
                        mFindIcon.setTextColor(Color.parseColor("#00aaee"));
                        mFindTitle.setTextColor(Color.parseColor("#00aaee"));
                        break;
                    case 3:
                        resetImg();
                        mMyIcon.setTextColor(Color.parseColor("#00aaee"));
                        mMyTitle.setTextColor(Color.parseColor("#00aaee"));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        // 初始化四个LinearLayout
        mTabTool = (LinearLayout) ma.findViewById(R.id.id_tab_tool);
        mTabTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
                resetImg();
                mToolIcon.setTextColor(Color.parseColor("#00aaee"));
                mToolTitle.setTextColor(Color.parseColor("#00aaee"));
            }
        });
        mTabChat = (LinearLayout) ma.findViewById(R.id.id_tab_chat);
        mTabChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1);
                resetImg();
                mChatIcon.setTextColor(Color.parseColor("#00aaee"));
                mChatTitle.setTextColor(Color.parseColor("#00aaee"));
            }
        });
        mTabFind = (LinearLayout) ma.findViewById(R.id.id_tab_find);
        mTabFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(2);
                resetImg();
                mFindIcon.setTextColor(Color.parseColor("#00aaee"));
                mFindTitle.setTextColor(Color.parseColor("#00aaee"));
            }
        });
        mTabMy = (LinearLayout) ma.findViewById(R.id.id_tab_my);
        mTabMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(3);
                resetImg();
                mMyIcon.setTextColor(Color.parseColor("#00aaee"));
                mMyTitle.setTextColor(Color.parseColor("#00aaee"));
            }
        });
        //兼容字体
        Typeface font = Typeface.createFromAsset(ma.getAssets(), "fontawesome-webfont.ttf");
        // 初始化四个图标
        mToolIcon = (TextView) ma.findViewById(R.id.id_tab_icon_tool);
        mToolIcon.setTypeface(font);
        mChatIcon = (TextView) ma.findViewById(R.id.id_tab_icon_chat);
        mChatIcon.setTypeface(font);
        mFindIcon = (TextView) ma.findViewById(R.id.id_tab_icon_find);
        mFindIcon.setTypeface(font);
        mMyIcon = (TextView) ma.findViewById(R.id.id_tab_icon_my);
        mMyIcon.setTypeface(font);
        // 初始化四个标题
        mToolTitle = (TextView) ma.findViewById(R.id.id_tab_title_tool);
        mChatTitle = (TextView) ma.findViewById(R.id.id_tab_title_chat);
        mFindTitle = (TextView) ma.findViewById(R.id.id_tab_title_find);
        mMyTitle = (TextView) ma.findViewById(R.id.id_tab_title_my);
        // 初始化四个布局
        LayoutInflater mLayoutInflater = LayoutInflater.from(ma);
        View view_tab_tool = mLayoutInflater.inflate(R.layout.nav_bottom_tool, null);
        View view_tab_chat = mLayoutInflater.inflate(R.layout.nav_bottom_chat, null);
        View view_tab_find = mLayoutInflater.inflate(R.layout.nav_bottom_find, null);
        View view_tab_my = mLayoutInflater.inflate(R.layout.nav_bottom_my, null);

        mViews.add(view_tab_tool);
        mViews.add(view_tab_chat);
        mViews.add(view_tab_find);
        mViews.add(view_tab_my);

        // 适配器初始化并设置
        mPagerAdapter = new PagerAdapter() {

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(mViews.get(position));

            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view);
                if(positionValue[position] == false){
                    initTabPage(position);
                    positionValue[position] = true;
                }
                return view;
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return mViews.size();
            }
        };

        mViewPager.setAdapter(mPagerAdapter);
    }

    private static void initTabPage(int position){
        if(position == 0){
            indexPage.init(ma);
            resetImg();
            mToolIcon.setTextColor(Color.parseColor("#00aaee"));
            mToolTitle.setTextColor(Color.parseColor("#00aaee"));
        }
    }

    /**
     * 把所有图片变暗
     */
    private static void resetImg() {
        mToolIcon.setTextColor(Color.parseColor("#a9a9a9"));
        mToolTitle.setTextColor(Color.parseColor("#a9a9a9"));
        mChatIcon.setTextColor(Color.parseColor("#a9a9a9"));
        mChatTitle.setTextColor(Color.parseColor("#a9a9a9"));
        mFindIcon.setTextColor(Color.parseColor("#a9a9a9"));
        mFindTitle.setTextColor(Color.parseColor("#a9a9a9"));
        mMyIcon.setTextColor(Color.parseColor("#a9a9a9"));
        mMyTitle.setTextColor(Color.parseColor("#a9a9a9"));
    }

}

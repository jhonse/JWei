package com.jhonse.blog.jwei.page.tool;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.jhonse.blog.jwei.MainActivity;
import com.jhonse.blog.jwei.R;
import com.jhonse.blog.jwei.activity.tool.WeixinActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class indexPage {

    private static MainActivity ma;

    //九宫格试图对象
    private static GridView mGridView;
    //定义图标数组
    private static int[] imageRes = {
            R.drawable.icon_weixin
    };
    //定义标题数组
    private static String[] itemName = {
            "微信"
    };

    /**
     * 初始化
     */
    public static void init(MainActivity mas){
        ma = mas;
        initView();
    }

    private static void initView(){
        //初始化九宫格
        mGridView = (GridView) ma.findViewById(R.id.contentMainMenu);
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        int length = itemName.length;
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("contentMainMenuImageView", imageRes[i]);
            map.put("contentMainMenuTextView", itemName[i]);
            data.add(map);
        }
        //为content_main_menu.xml添加适配器
        SimpleAdapter simpleAdapter = new SimpleAdapter(ma,
                data, R.layout.nav_bottom_tool_menu, new String[] { "contentMainMenuImageView","contentMainMenuTextView" }, new int[] { R.id.contentMainMenuImageView,R.id.contentMainMenuTextView });
        mGridView.setAdapter(simpleAdapter);
        //为mGridView添加点击事件监听器
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent=new Intent(ma,WeixinActivity.class);
                    ma.startActivity(intent);
                }
            }
        });
    }

}

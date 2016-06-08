package com.jhonse.blog.jwei.activity.tool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jhonse.blog.jwei.R;
import com.jhonse.blog.jwei.page.tool.weixinPage;

public class WeixinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin);

        //初始化
        weixinPage.init(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_weixin);
        toolbar.setNavigationIcon(R.drawable.icon_weixin_min);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_weixin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.activity_weixin_menu_settings_return) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

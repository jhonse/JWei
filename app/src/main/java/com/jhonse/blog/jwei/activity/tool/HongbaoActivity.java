package com.jhonse.blog.jwei.activity.tool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jhonse.blog.jwei.R;

public class HongbaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hongbao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_weixin_hongbao);
        toolbar.setNavigationIcon(R.drawable.icon_weixin_hongbao_min);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_weixin_hongbao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.activity_weixin_menu_hongbao_settings_return) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

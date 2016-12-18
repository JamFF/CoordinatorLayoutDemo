package com.example.fj.second;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fj.second.adapter.MyPagerAdapter;
import com.example.fj.second.dummy.DummyContent;
import com.example.fj.second.fragment.ItemFragment;

import java.util.ArrayList;
import java.util.List;

public class AppBarLayoutActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    private TabLayout tab;

    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_layout);
        initView();
        initData();
    }

    private void initView() {
        initToolbar();
        initTabLayout();
    }

    private void initToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // 设置toolbar，注意theme不能使用带有actionbar的样式，否则会报错
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            // 4.0以上Navigation默认false
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String msg = null;
                switch (item.getItemId()) {

                    case R.id.action_settings:
                        msg = "Click setting";
                        break;

                    default:
                        break;
                }

                if (msg != null) {
                    Toast.makeText(AppBarLayoutActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    private void initTabLayout() {

        tab = (TabLayout) findViewById(R.id.tab);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void initData() {
        List<String> mList = new ArrayList<>();

        if (mList.size() > 0) {
            mList.clear();
        }
        mList.add("Tab 1");
        mList.add("Tab 2");
        mList.add("Tab 3");
        mList.add("Tab 4");
        mList.add("Tab 5");

        // 设置ViewPager的Adapter
        viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mList));
        // 关键一行代码，将TabLayout与ViewPager关联
        tab.setupWithViewPager(viewpager);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Toolbar上返回键的点击事件，也可以单独设置，
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

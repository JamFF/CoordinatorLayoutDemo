package com.example.fj.second;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fj.second.adapter.MyPagerAdapter;
import com.example.fj.second.fragment.ItemFragment;

import java.util.ArrayList;
import java.util.List;

public class AppBarLayoutActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    private TabLayout tab;

    private ViewPager viewpager;

    private List<String> mList = new ArrayList<>();

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

        Toolbar toolbar = findViewById(R.id.toolbar);

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

                    case R.id.home:
                        // 这里是不会走的，如果要设置返回键
                        // toolbar.setNavigationOnClickListener，或者onOptionsItemSelected
                        msg = "返回_Toolbar";
                        onBackPressed();
                        break;

                    case R.id.action_list:// List样式
                        msg = "List样式_Toolbar";
                        break;

                    case R.id.action_grid:// Grid样式
                        msg = "Grid样式_Toolbar";
                        break;

                    case R.id.action_staggered:// 瀑布流样式
                        msg = "瀑布流样式_Toolbar";

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

        tab = findViewById(R.id.tab);
        viewpager = findViewById(R.id.viewpager);
    }

    private void initData() {

        if (mList.size() > 0) {
            mList.clear();
        }
        mList.add("List垂直");
        mList.add("List垂直反向");
        mList.add("List水平");
        mList.add("List水平反向");

        mList.add("Grid垂直");
        mList.add("Grid垂直反向");
        mList.add("Grid水平");
        mList.add("Grid水平反向");

        mList.add("瀑布流垂直");
        mList.add("瀑布流垂直反向");
        mList.add("瀑布流水平");
        mList.add("瀑布流水平反向");

        // 设置ViewPager的Adapter
        viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mList));
        // 关键一行代码，将TabLayout与ViewPager关联
        tab.setupWithViewPager(viewpager);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog(DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(this)
                .setTitle("确认删除")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", listener)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_abl, menu);
        return true;
    }

    // 注意：如果toolbar.setOnMenuItemClickListener了，那么这里就收不到MenuItem的点击事件了
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_list:// List样式
                Toast.makeText(this, "List样式_MenuItem", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_grid:// Grid样式
                Toast.makeText(this, "Grid样式_MenuItem", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_staggered:// 瀑布流样式
                Toast.makeText(this, "瀑布流样式_MenuItem", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.home:// Toolbar上返回键的点击事件，也可以单独设置
                Toast.makeText(this, "返回_MenuItem", Toast.LENGTH_SHORT).show();
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

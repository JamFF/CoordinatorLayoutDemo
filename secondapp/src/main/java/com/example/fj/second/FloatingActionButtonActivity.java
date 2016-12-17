package com.example.fj.second;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.fj.second.util.ColoredSnackbar;

/**
 * 描述：FloatingActionButton和Toolbar
 * 作者：傅健
 * 创建时间：2016/12/17 16:28
 */
public class FloatingActionButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);
        initView();
    }

    private void initView() {

        initToolbar();
        initFloatingActionButton();
    }

    private void initToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // APP Logo
        toolbar.setLogo(R.mipmap.ic_launcher);

        // Title
        toolbar.setTitle(getString(R.string.tool_bar_title));

        // Sub Title
        toolbar.setSubtitle(getString(R.string.tool_bar_sub_title));

        // 最左侧Navigation，这里设置之后不需要再getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 参照CollapsingToolbarLayoutActivity
        toolbar.setNavigationIcon(R.mipmap.ic_return);

        // 上面这些设置均可在布局文件中设置
        setSupportActionBar(toolbar);

        // toolbar的点击事件监听都要放在setSupportActionBar之后才会生效
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String msg = null;
                switch (item.getItemId()) {
                    case R.id.action_edit:
                        msg = "Click edit";
                        break;
                    case R.id.action_share:
                        msg = "Click share";
                        break;
                    case R.id.action_settings:
                        msg = "Click setting";
                        break;
                }

                if (msg != null) {
                    Toast.makeText(FloatingActionButtonActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        // 返回键的点击事件还可以onOptionsItemSelected中写，参照AppBarLayoutActivity
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initFloatingActionButton() {

        // FloatingActionButton默认使用FloatingActionButton.Behavior
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 外层CoordinatorLayout才能使FloatingActionButton跟随Snackbar上下移动
                Snackbar snackbar = Snackbar.make(v, "it is Snackbar", Snackbar.LENGTH_LONG);

                // 修改Snackbar的背景颜色
                // snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));

                // 或者使用工具类，按照不同等级，修改背景颜色
                ColoredSnackbar.alert(snackbar)

                        // 设置右侧取消文字的颜色
                        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                        .setAction(getString(R.string.show_toast), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // 这里的单击事件代表点击消除Action后的响应事件
                                Toast.makeText(FloatingActionButtonActivity.this, "it is Toast", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
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
            Toast.makeText(FloatingActionButtonActivity.this, getString(R.string.action_settings), Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

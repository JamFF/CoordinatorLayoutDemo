package com.example.fj.second;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.fj.second.util.ColoredSnackbar;

/**
 * 描述：FloatingActionButton和Toolbar
 * 作者：JamFF
 * 创建时间：2016/12/17 16:28
 */
public class FloatingActionButtonActivity extends AppCompatActivity implements TextWatcher {

    private TextInputLayout til_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);
        initView();
    }

    private void initView() {

        initToolbar();
        initFloatingActionButton();
        initTextInputLayout();
    }

    private void initToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // APP Logo
        toolbar.setLogo(R.mipmap.ic_logo);

        // Title
        toolbar.setTitle(getString(R.string.tool_bar_title));

        // Sub Title
        toolbar.setSubtitle(getString(R.string.tool_bar_sub_title));

        // 最左侧Navigation，在这里设置图片后，不需要再getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 如果在布局中设置了图片，参照CollapsingToolbarLayoutActivity
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

    private void initTextInputLayout() {
        til_name = (TextInputLayout) findViewById(R.id.til_name);
        til_name.getEditText().addTextChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fab, menu);
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 5) {
            if (s.toString().startsWith("fujian")) {
                til_name.setHint("帐号正确");
                til_name.setErrorEnabled(false);
                til_name.setHintEnabled(true);
            } else {
                til_name.setError("帐号错误");
                til_name.setErrorEnabled(true);
                til_name.setHintEnabled(false);
            }
        } else {
            til_name.setHint(getString(R.string.input_name));
            til_name.setErrorEnabled(false);
            til_name.setHintEnabled(true);
        }
    }
}

package com.example.fj.second;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fj.second.util.ColoredSnackbar;

public class FloatingActionButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);

        // FloatingActionButton默认使用FloatingActionButton.Behavior
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 外层CoordinatorLayout才能使FloatingActionButton跟随Snackbar上下移动
                Snackbar snackbar = Snackbar.make(v, "it is Snackbar", Snackbar.LENGTH_LONG);

                // 修改Snackbar的背景颜色
                // snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));

                // 使用工具类，按照不同等级，修改背景颜色
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
}

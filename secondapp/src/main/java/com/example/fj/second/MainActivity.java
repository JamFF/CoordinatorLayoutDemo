package com.example.fj.second;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_fab).setOnClickListener(this);
        findViewById(R.id.bt_appbar).setOnClickListener(this);
        findViewById(R.id.bt_collapsing).setOnClickListener(this);
        findViewById(R.id.bt_behavior).setOnClickListener(this);
        findViewById(R.id.bt_model).setOnClickListener(this);
        findViewById(R.id.bt_card_view).setOnClickListener(this);
        findViewById(R.id.btn_notification).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bt_fab:
                startActivity(new Intent(this, FloatingActionButtonActivity.class));
                break;

            case R.id.bt_appbar:
                startActivity(new Intent(this, AppBarLayoutActivity.class));
                break;

            case R.id.bt_collapsing:
                startActivity(new Intent(this, CollapsingToolbarLayoutActivity.class));
                break;

            case R.id.bt_behavior:
                startActivity(new Intent(this, MyBehaviorActivity.class));
                break;

            case R.id.bt_model:
                if ((v.getSystemUiVisibility() & View.SYSTEM_UI_FLAG_FULLSCREEN) == View.SYSTEM_UI_FLAG_FULLSCREEN) {
                    // &的含义是，如果包含View.SYSTEM_UI_FLAG_FULLSCREEN属性，例如设置两个属性
                    v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    ((Button) v).setText(getString(R.string.gone_statusBar));
                } else {
                    // v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                    v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
                    ((Button) v).setText(getString(R.string.visible_statusBar));
                }
                break;

            case R.id.bt_card_view:
                startActivity(new Intent(this, CardViewActivity.class));
                break;

            case R.id.btn_notification:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
        }
    }
}

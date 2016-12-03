package com.example.fj.second;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_fab).setOnClickListener(this);
        findViewById(R.id.bt_appbar).setOnClickListener(this);
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
        }
    }
}

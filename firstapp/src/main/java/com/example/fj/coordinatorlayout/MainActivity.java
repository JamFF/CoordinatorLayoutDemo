package com.example.fj.coordinatorlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        findViewById(R.id.bt_first).setOnClickListener(this);
        findViewById(R.id.bt_second).setOnClickListener(this);
        findViewById(R.id.bt_typed_array).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_first:
                startActivity(new Intent(this, FirstCoordinatorLayoutActivity.class));
                break;

            case R.id.bt_second:
                startActivity(new Intent(this, SecondCoordinatorLayoutActivity.class));
                break;

            case R.id.bt_typed_array:
                startActivity(new Intent(this, TypedArrayActivity.class));
                break;

            default:
                break;
        }
    }
}

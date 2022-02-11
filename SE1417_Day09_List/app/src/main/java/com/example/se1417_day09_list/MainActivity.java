package com.example.se1417_day09_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickToSingle(View view) {
        Intent intent = new Intent(this, SingleListActivity.class);
        startActivity(intent);
    }

    public void clickToMultiple(View view) {
        Intent intent = new Intent(this, MultipleListActivity.class);
        startActivity(intent);
    }
}
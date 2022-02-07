package com.example.se1417_day02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult = findViewById(R.id.txtResult);
//        Intent intent = this.getIntent();
//        String result = intent.getStringExtra("info");
//        if (result != null) txtResult.setText(result);
    }

    public void clickToCreate(View view) {
        // chuyển đến activity mới
        Intent intent = new Intent(this, CreateProductActivity.class);
//        startActivity(intent);
        startActivityForResult(intent, 6789);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 6789) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("info");
                txtResult.setText(result);
            }
        }
    }
}
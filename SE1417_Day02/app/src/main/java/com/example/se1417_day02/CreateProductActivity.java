package com.example.se1417_day02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateProductActivity extends AppCompatActivity {

    private EditText edtID, edtName, edtPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        edtID = findViewById(R.id.edtID);
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);

    }

    public void clickToDone(View view) {
        String id = edtID.getText().toString();
        String name = edtName.getText().toString();
        String price = edtPrice.getText().toString();
        String result = "ID: " + id + " - Name: " + name + " - Price: " + price;
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("info", result);
//        startActivity(intent);

        Intent intent = this.getIntent();
        intent.putExtra("info", result);
        this.setResult(RESULT_OK, intent);
        //câu này sẽ tự release activity này
        finish();
    }
}
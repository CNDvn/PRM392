package com.example.se1417_day06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DynamicUIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView txtTitle = new TextView(this);
        txtTitle.setText("Creating Dynamic UI");
        txtTitle.setLayoutParams(params);

        TextView txtProductName = new TextView(this);
        txtProductName.setText("Product Name: ");
        txtProductName.setLayoutParams(params);

        EditText edtName = new EditText(this);
        edtName.setLayoutParams(params);
        edtName.setId(R.id.edtProductName);

        TextView txtProductPrice = new TextView(this);
        txtProductPrice.setText("Price: ");

        EditText edtPrice = new EditText(this);
        edtPrice.setLayoutParams(params);
        edtPrice.setId(R.id.edtProductPrice);
        edtPrice.setInputType(InputType.TYPE_CLASS_NUMBER);

        Button btnSave = new Button(this);
        btnSave.setText("Save");
        btnSave.setLayoutParams(params);

        linearLayout.addView(txtTitle);
        linearLayout.addView(txtProductName);
        linearLayout.addView(edtName);
        linearLayout.addView(txtProductPrice);
        linearLayout.addView(edtPrice);
        linearLayout.addView(btnSave);

        this.addContentView(linearLayout, params);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edtName = findViewById(R.id.edtProductName);
                EditText edtPrice = findViewById(R.id.edtProductPrice);
                String result = "Name: " + edtName.getText().toString() + " - Price: " + edtPrice.getText().toString();
                Toast.makeText(DynamicUIActivity.this, result,Toast.LENGTH_LONG).show();
            }
        });
    }
}
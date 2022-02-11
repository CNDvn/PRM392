package com.example.se1417_day09_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView txtSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txtSubject = findViewById(R.id.txtSubject);
        Intent intent = this.getIntent();
        String subject = intent.getStringExtra("subject");
        txtSubject.setText(subject);
    }
}
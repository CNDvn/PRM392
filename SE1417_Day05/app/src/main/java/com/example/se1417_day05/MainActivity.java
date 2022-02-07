package com.example.se1417_day05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextView txtBirthday;
    private Spinner spNationality;
    private String selectedSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtBirthday = findViewById(R.id.txtBirthday);
        spNationality = findViewById(R.id.spNationality);
        List<String> dataSrc = new ArrayList<>();
        dataSrc.add("Kinh");
        dataSrc.add("It nguoi");
        dataSrc.add("Nuoc ngoai");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dataSrc);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNationality.setAdapter(dataAdapter);
        spNationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSpinner = spNationality.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void clickToChangeDate(View view) {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    public void clickToRegister(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("birthday", txtBirthday.getText().toString());
        bundle.putString("nationality", selectedSpinner);
        Intent intent = new Intent(this, ShowActivity.class);
        intent.putExtra("INFO", bundle);
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        int month = i1 + 1;
        String date = i2 + " " + month + "-" + i;
        txtBirthday.setText(date);
    }
}
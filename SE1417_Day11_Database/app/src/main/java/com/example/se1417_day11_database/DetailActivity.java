package com.example.se1417_day11_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.se1417_day11_database.daos.StudentDAO;
import com.example.se1417_day11_database.dtos.StudentDTO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private EditText edtID, edtName, edtMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        edtID = findViewById(R.id.edtID);
        edtName = findViewById(R.id.edtName);
        edtMark = findViewById(R.id.edtMark);

        Intent intent = this.getIntent();
        StudentDTO dto = (StudentDTO) intent.getSerializableExtra("dto");
        edtID.setText(dto.getId());
        edtName.setText(dto.getName());
        edtMark.setText(dto.getMark() + "");
    }

    public void clickToSaveToInternal(View view) {
        String id = edtID.getText().toString();
        String name = edtName.getText().toString();
        float mark = Float.parseFloat(edtMark.getText().toString());

        try {
            FileInputStream fis = openFileInput("hieubd.txt");
            StudentDAO dao = new StudentDAO();
            List<StudentDTO> result = dao.loadFromInternal(fis);
            for (StudentDTO dto : result) {
                if (dto.getId().equals(id)) {
                    dto.setMark(mark);
                    dto.setName(name);
                    break;
                }
            }
            FileOutputStream fos = openFileOutput("hieubd.txt", MODE_PRIVATE);
            dao.saveToInternal(fos, result);
            Toast.makeText(this, "Save internal success", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
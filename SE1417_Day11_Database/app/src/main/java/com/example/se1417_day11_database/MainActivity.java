package com.example.se1417_day11_database;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.se1417_day11_database.daos.StudentDAO;
import com.example.se1417_day11_database.db.StudentAdapter;
import com.example.se1417_day11_database.dtos.StudentDTO;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.se1417_day11_database.databinding.ActivityMainBinding;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewStudent;
    private TextView txtTitle;
    private StudentAdapter adapter;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void clickToLoadFromRAW(MenuItem item) {
        listViewStudent = findViewById(R.id.listViewStudent);
        txtTitle = findViewById(R.id.txtTitle);
        adapter = new StudentAdapter();

        try {
            txtTitle.setText("List student from RAW");
            StudentDAO dao = new StudentDAO();
            InputStream is = getResources().openRawResource(R.raw.data);
            List<StudentDTO> listStudent = dao.loadFromRAW(is);
            adapter.setStudentDTOList(listStudent);
            listViewStudent.setAdapter(adapter);
            listViewStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    StudentDTO dto = (StudentDTO) listViewStudent.getItemAtPosition(i);
                    Toast.makeText(MainActivity.this, dto.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("dto", dto);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickToSaveFromRAWToInternal(MenuItem item) {
        try {
            StudentDAO dao = new StudentDAO();
            InputStream is = getResources().openRawResource(R.raw.data);
            List<StudentDTO> list = dao.loadFromRAW(is);
            FileOutputStream fos = openFileOutput("hieubd.txt", MODE_PRIVATE);
            dao.saveToInternal(fos, list);
            Toast.makeText(this, "Save to internal success", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickToLoadFromInternal(MenuItem item) {
        listViewStudent = findViewById(R.id.listViewStudent);
        txtTitle = findViewById(R.id.txtTitle);
        adapter = new StudentAdapter();
        txtTitle.setText("List student from internal");

        try {
            FileInputStream fis = openFileInput("hieubd.txt");
            StudentDAO dao = new StudentDAO();
            List<StudentDTO> result = dao.loadFromInternal(fis);
            adapter.setStudentDTOList(result);
            listViewStudent.setAdapter(adapter);
            listViewStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    StudentDTO dto = (StudentDTO) listViewStudent.getItemAtPosition(i);
                    Toast.makeText(MainActivity.this, dto.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("dto", dto);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickToLoadFromExternal(MenuItem item) {
        listViewStudent = findViewById(R.id.listViewStudent);
        txtTitle = findViewById(R.id.txtTitle);
        adapter = new StudentAdapter();
        txtTitle.setText("List student from SD Card (External)");

        try {
            StudentDAO dao = new StudentDAO();
            List<StudentDTO> list = dao.loadFromExternal();
            adapter.setStudentDTOList(list);
            listViewStudent.setAdapter(adapter);
            listViewStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    StudentDTO dto = (StudentDTO) listViewStudent.getItemAtPosition(i);
                    Toast.makeText(MainActivity.this, dto.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("dto", dto);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickToSaveToExternal(MenuItem item) {
        try {
            FileInputStream fis = openFileInput("hieubd.txt");
            StudentDAO dao = new StudentDAO();
            List<StudentDTO> list = dao.loadFromInternal(fis);
            if (dao.saveToExternal(list)) {
                Toast.makeText(this, "Save to SD Card success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Save to SD Card fail", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickToStudentPreference(MenuItem item) {
        Intent intent = new Intent(this, StudentPreferenceActivity.class);
        startActivity(intent);
    }
}
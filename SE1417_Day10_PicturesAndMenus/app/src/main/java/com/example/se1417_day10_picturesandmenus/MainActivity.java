package com.example.se1417_day10_picturesandmenus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnContext = findViewById(R.id.btnContext);
        btnContext.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        createMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return menuChoice(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        createMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return menuChoice(item);
    }

    private void createMenu(Menu menu) {
        MenuItem item1 = menu.add(0, 0, 0, "Gallery");
        MenuItem item2 = menu.add(0, 1, 1, "Item 1");
        MenuItem item3 = menu.add(0, 2, 2, "Item 2");
        MenuItem item4 = menu.add(0, 3, 3, "Item 3");
    }

    private boolean menuChoice(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Intent intent = new Intent(this, GalleryHieuActivity.class);
                startActivity(intent);
                return true;
            case 1:
                Toast.makeText(this, "Item 1 clicked", Toast.LENGTH_SHORT).show();
                return true;
            case 2:
                Toast.makeText(this, "Item 2 clicked", Toast.LENGTH_SHORT).show();
                return true;
            case 3:
                Toast.makeText(this, "Item 3 clicked", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    public void clickToGallery(View view) {
        Intent intent = new Intent(this, GalleryHieuActivity.class);
        startActivity(intent);
    }
}
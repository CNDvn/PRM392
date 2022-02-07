package com.example.se1417_day07;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    private List<Fragment> fragmentsList = new ArrayList<>();
    private TabFragment1 tabFragment1;
    private TabFragment2 tabFragment2;
    String productName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab1 = bar.newTab();
        tab1.setText("Input");
        tab1.setTabListener(this);
        bar.addTab(tab1);

        ActionBar.Tab tab2 = bar.newTab();
        tab2.setText("Register");
        tab2.setTabListener(this);
        bar.addTab(tab2);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (tab.getPosition() == 0) {
            if (tabFragment1 == null) {
                tabFragment1 = new TabFragment1();
                Bundle bundle = new Bundle();
                bundle.putString("default", "HieuBD");
                tabFragment1.setArguments(bundle);
                fragmentsList.add(tabFragment1);
            } else {
                tabFragment1 = (TabFragment1) fragmentsList.get(tab.getPosition());
            }
            ft.replace(android.R.id.content, tabFragment1);
        } else {
            if (tabFragment2 == null) {
                tabFragment2 = new TabFragment2();
                fragmentsList.add(tabFragment2);
            } else {
                tabFragment2 = (TabFragment2) fragmentsList.get(tab.getPosition());
            }
            ft.replace(android.R.id.content, tabFragment2);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (tab.getPosition() == 0) {
            EditText edtName = findViewById(R.id.edtName);
            productName = edtName.getText().toString();
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public void clickToDone(View View) {
        EditText edtPrice = findViewById(R.id.edtPrice);
        String result = "Name: " + productName + " - Price: " + edtPrice.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("result", result);
        tabFragment1.setArguments(bundle);
        Toast.makeText(this, "Save success", Toast.LENGTH_SHORT).show();
    }
}
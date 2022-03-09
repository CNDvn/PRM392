package com.example.se1417_day11_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.widget.EditText;

public class StudentPreferenceActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.studentpreference);
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.se1417_day11_database_preferences", MODE_PRIVATE);
        for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++){
            initData(getPreferenceScreen().getPreference(i), sharedPreferences);
        }
    }

    private void initData(Preference p, SharedPreferences sharedPreferences){
        if(p instanceof PreferenceCategory){
            PreferenceCategory category = (PreferenceCategory) p;
            for(int i = 0; i < category.getPreferenceCount(); i ++){
                initData(category.getPreference(i), sharedPreferences);
            }
        }else {
            updatePerf(p, sharedPreferences);
        }
    }

    private void updatePerf(Preference p, SharedPreferences sharedPreferences){
        if(p instanceof EditTextPreference){
            EditTextPreference edt = (EditTextPreference) p;
            p.setSummary(edt.getText());
        }else if(p instanceof CheckBoxPreference){
            CheckBoxPreference chk = (CheckBoxPreference) p;
            String s = "Male";
            if(!chk.isChecked()) s = "Female";
            p.setSummary(s);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        updatePerf(findPreference(s), sharedPreferences);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
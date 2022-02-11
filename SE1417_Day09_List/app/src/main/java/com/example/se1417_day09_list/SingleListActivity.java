package com.example.se1417_day09_list;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SingleListActivity extends ListActivity {
    private String[] listSubject = {"PRJ301","PRX301", "PRM301"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listSubject);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(this,DetailActivity.class);
        String subject = listSubject[position];
        intent.putExtra("subject",subject);
        startActivity(intent);
    }
}
package com.example.se1417_day07;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragment1 extends Fragment {

    public TabFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        TextView txtResult = view.findViewById(R.id.txtResult);
        Bundle bundle = getArguments();
        if (bundle.getString("result") != null) {
            txtResult.setText(bundle.getString("result"));
        } else {
            txtResult.setText(bundle.getString("default"));
        }
        return view;
    }
}
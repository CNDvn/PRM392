package com.example.se1417_day04_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {
    private EditText edtNum1, edtNum2;
    private TextView txtResult;
    private Button btnAdd;

    @Override
    public void onStart() {
        super.onStart();
        edtNum1 = getActivity().findViewById(R.id.edtNum1);
        edtNum2 = getActivity().findViewById(R.id.edtNum2);
        txtResult = getActivity().findViewById(R.id.txtResult);
        btnAdd = getActivity().findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1 = Integer.parseInt(edtNum1.getText().toString());
                int num2 = Integer.parseInt(edtNum2.getText().toString());
                txtResult.setText("Result = " + (num1 + num2));
            }
        });
    }

    public Fragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_2, container, false);
    }
}
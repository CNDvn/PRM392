package com.example.se1417_day11_database.ui.gallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.se1417_day11_database.R;
import com.example.se1417_day11_database.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private EditText edtId, edtName, edtMark;
    private Button btnLoad, btnSave;
    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        edtId = root.findViewById(R.id.edtID);
        edtName = root.findViewById(R.id.edtName);
        edtMark = root.findViewById(R.id.edtMark);
        btnLoad = root.findViewById(R.id.btnLoad);
        btnSave = root.findViewById(R.id.btnSave);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.se1417_day11_database_preferences", Context.MODE_PRIVATE);
                String id = sharedPreferences.getString("IDPerf","");
                String name = sharedPreferences.getString("NamePref","");
                String mark = sharedPreferences.getString("MarkPerf","");
                edtId.setText(id);
                edtName.setText(name);
                edtMark.setText(mark);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.se1417_day11_database_preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("IDPref",edtId.getText().toString());
                editor.putString("NamePref",edtName.getText().toString());
                editor.putString("MarkPref",edtMark.getText().toString());
                editor.commit();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package ru.mirea.nosenkovtm.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    EditText editText;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences sharedPref = requireContext().getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editText = view.findViewById(R.id.editText);
        button = view.findViewById(R.id.button2);

        editText.setText(sharedPref.getString("NAME", "Введите имя"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("NAME", editText.getText().toString());
                editor.apply();
            }
        });

        return view;
    }
}
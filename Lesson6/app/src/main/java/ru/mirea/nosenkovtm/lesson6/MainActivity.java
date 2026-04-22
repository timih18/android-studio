package ru.mirea.nosenkovtm.lesson6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.mirea.nosenkovtm.lesson6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPref = getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        EditText group = binding.groupEditText;
        EditText number = binding.numberEditText;
        EditText film = binding.filmEditText;
        Button button = binding.button;

        group.setText(sharedPref.getString("GROUP", "Введите группу"));
        number.setText(String.valueOf(sharedPref.getInt("NUMBER", 0)));
        film.setText(sharedPref.getString("FILM", "Введите фильм"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("GROUP", group.getText().toString());
                editor.putInt("NUMBER", Integer.parseInt(number.getText().toString()));
                editor.putString("FILM", film.getText().toString());
                editor.apply();
            }
        });
    }
}
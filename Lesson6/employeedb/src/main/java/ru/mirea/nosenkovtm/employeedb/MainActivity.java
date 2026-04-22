package ru.mirea.nosenkovtm.employeedb;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.mirea.nosenkovtm.employeedb.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppDatabase db = App.getInstance().getDatabase();
        SuoerheroDao suoerheroDao = db.suoerheroDao();

        Superhero superhero = new Superhero();
        superhero.id = 1;
        superhero.age = 18;
        superhero.name = "AAA";
        superhero.superpower = "flying";

        suoerheroDao.insert(superhero);
    }
}
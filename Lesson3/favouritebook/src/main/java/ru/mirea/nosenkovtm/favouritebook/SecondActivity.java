package ru.mirea.nosenkovtm.favouritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        // Получение данных из MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TextView bookView = findViewById(R.id.textView2);
            TextView quoteView = findViewById(R.id.textView3);
            String book_name = extras.getString(MainActivity.BOOK_NAME_KEY);
            String quotes_name = extras.getString(MainActivity.QUOTES_KEY);
            bookView.setText("Книга: " + book_name);
            quoteView.setText("Цитата: " + quotes_name);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onButtonClick(View v) {
        Intent data = new Intent();

        EditText name = findViewById(R.id.editTextText);
        EditText quote = findViewById(R.id.editTextText2);
        data.putExtra(MainActivity.NAME, name.getText().toString());
        data.putExtra(MainActivity.QUOTE, quote.getText().toString());
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}
package ru.mirea.nosenkovtm.data_thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        final String result = "Post thread";
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(result);
            }
        });

        mainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText(result + " + delay");
            }
        }, 2000);
    }
}
package ru.mirea.nosenkovtm.multiactivity;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.TextView;

import ru.mirea.nosenkovtm.multiactivity.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() - Activity 2");

        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView textView = findViewById(R.id.textView);

        String text = (String) getIntent().getSerializableExtra("key");
        if (text != null && !text.isEmpty()) { textView.setText("Hello " + text + "!"); }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() - Activity 2");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState() - Activity 2");
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i(TAG, "onPostCreate() - Activity 2");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() - Activity 2");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG, "onPostResume() - Activity 2");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedWindow() - Activity 2");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() - Activity 2");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState() - Activity 2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() - Activity 2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() - Activity 2");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow() - Activity 2");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart() - Activity 2");
    }
}
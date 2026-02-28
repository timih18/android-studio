package ru.mirea.nosenkovtm.buttonclicker;

import android.os.Bundle;import android.view.View;import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textView  = (TextView) findViewById(R.id.tvOut);
        Button btnWhoAmI = (Button) findViewById(R.id.btnWhoAmI);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);

        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) { textView.setText("My number on the list is 10");
            checkBox.setChecked(true);}
        };
        btnWhoAmI.setOnClickListener(oclBtnWhoAmI);
    }
    public void btnItIsNotMeClick(View v) {
        TextView textView  = (TextView) findViewById(R.id.tvOut);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        textView.setText("It's not me");
        checkBox.setChecked(true);
    }
}
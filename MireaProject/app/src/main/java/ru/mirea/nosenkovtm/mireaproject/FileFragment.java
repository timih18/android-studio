package ru.mirea.nosenkovtm.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileFragment extends Fragment {

    Button uploadButton;
    Button saveButton;
    EditText textEditText;
    EditText fileEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file, container, false);

        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) { return view; }

        uploadButton = view.findViewById(R.id.uploadButton);
        saveButton = view.findViewById(R.id.saveButton);
        textEditText = view.findViewById(R.id.textEditText);
        fileEditText = view.findViewById(R.id.fileEditText);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                File file = new File(path, fileEditText.getText().toString());

                try {
                    FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);

                    List<String> lines = new ArrayList<>();
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    String line = reader.readLine();
                    textEditText.setText(line);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                File file = new File(path, fileEditText.getText().toString());

                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile());
                    OutputStreamWriter output = new OutputStreamWriter(fileOutputStream);

                    output.write(textEditText.getText().toString());
                    output.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }
}
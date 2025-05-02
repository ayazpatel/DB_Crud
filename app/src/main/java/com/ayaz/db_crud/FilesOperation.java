package com.ayaz.db_crud;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FilesOperation extends AppCompatActivity {
    Utils utils;
    EditText tvFileContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_operation);

        utils = new Utils(this);
        tvFileContent = findViewById(R.id.tvFileContent);
    }

    private void writeFile(String fileName) {
        String data = tvFileContent.getText().toString();

        FileOutputStream fOut;
        try {
            fOut = openFileOutput(fileName, MODE_PRIVATE);
            fOut.write(data.getBytes());
            fOut.close();
            utils.showAlert("Alert: File Write", "Success");
        } catch (FileNotFoundException e) {
            utils.showAlert("Alert: File Write", e.getMessage());
        } catch (IOException e) {
            utils.showAlert("Alert: File Write", e.getMessage());
        }
    }

    private void readFile(String fileName) {

        FileInputStream fIn;
        try {
            fIn = openFileInput(fileName);
            int c;
            String temp = "";
            while ((c = fIn.read()) != -1) {
                temp = temp + Character.toString(c);
            }
            tvFileContent.setText(temp);
            fIn.close();
            utils.showAlert("Alert: File Read", "Success");
        } catch (FileNotFoundException e) {
            utils.showAlert("Alert: File Read", e.getMessage());
        } catch (IOException e) {
            utils.showAlert("Alert: File Read", e.getMessage());
        }
    }

    public void onRead(View view) {
        readFile("sample.txt");
    }

    public void onWrite(View view) {
        writeFile("sample.txt");
    }

    public void onCancel(View view) {
        finish();
    }
}
package com.ayaz.db_crud;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayOne extends AppCompatActivity {
    Utils utils;
    DBhelper dBhelper;
    SQLiteDatabase sqLiteDatabase;
    EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_one);

        utils = new Utils(this);
        dBhelper = new DBhelper(this);

        id = findViewById(R.id.txtId);
    }

    public void display() {

    }

    public void onSubmit(View view) {
    }

    public void onCancel(View view) {
    }
}
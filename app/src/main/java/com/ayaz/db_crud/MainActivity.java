package com.ayaz.db_crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Insert(View view) {
        startActivity(new Intent(this, Insert.class));
    }

    public void Update(View view) {
        startActivity(new Intent(this, Update.class));
    }

    public void Delete(View view) {
        startActivity(new Intent(this, Delete.class));
    }

    public void DisplayAll(View view) {
        startActivity(new Intent(this, DisplayAll.class));
    }

    public void DisplayOne(View view) {
        startActivity(new Intent(this, DisplayOne.class));
    }

    public void checkbox_radio(View view) {
        startActivity(new Intent(this, Checkbox_RadioButton.class));
    }
}
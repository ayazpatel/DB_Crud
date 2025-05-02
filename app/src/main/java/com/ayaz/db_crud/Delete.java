package com.ayaz.db_crud;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Delete extends AppCompatActivity {
    Utils utils;
    DBhelper dBhelper;
    SQLiteDatabase db;

    EditText id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        utils = new Utils(this);
        dBhelper = new DBhelper(this);

        id = findViewById(R.id.txtId);
    }

    public void delete() {
        db = dBhelper.getWritableDatabase();

        int res = db.delete("student", "_id = " + Integer.parseInt(id.getText().toString()), null);

        if (res > 0) {
            utils.showAlert("Alert", "Delete Success");
        } else {
            utils.showAlert("Alert", "Delete Failed");
        }
    }

    public void onSubmit(View view) {
        delete();
    }

    public void onCancel(View view) {
        finish();
    }
}
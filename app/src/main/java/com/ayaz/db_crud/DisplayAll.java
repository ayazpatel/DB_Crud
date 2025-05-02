package com.ayaz.db_crud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayAll extends AppCompatActivity {
    Utils utils;
    DBhelper dBhelper;
    SQLiteDatabase db;
    SimpleCursorAdapter simpleCursorAdapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);
        utils = new Utils(this);
        dBhelper = new DBhelper(this);

        list = findViewById(R.id.list);
        diplay();
    }

    public void diplay() {
        try {
            db = dBhelper.getReadableDatabase();

            Cursor cursor = db.rawQuery("SELECT * FROM student", null);

            String[] from = new String[] {
                    cursor.getColumnName(0),
                    cursor.getColumnName(1),
                    cursor.getColumnName(2),
                    cursor.getColumnName(3),
                    cursor.getColumnName(4)
            };

            int[] to = new int[] {
                    R.id.tv_id,
                    R.id.tv_name,
                    R.id.tv_age,
                    R.id.tv_dob,
                    R.id.tv_tob
            };

            simpleCursorAdapter = new SimpleCursorAdapter( this, R.layout.list_template, cursor, from, to, 0);

            list.setAdapter(simpleCursorAdapter);
        } catch (Exception ex) {
            utils.showAlert("Alert", "Something Went Wrong");
        }
        db.close();
    }

    public void onSubmit(View view) {
        utils.showToast("No Usage");
    }

    public void onCancel(View view) {
        finish();
    }
}
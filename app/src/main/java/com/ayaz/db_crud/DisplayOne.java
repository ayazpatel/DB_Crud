package com.ayaz.db_crud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayOne extends AppCompatActivity {
    Utils utils;
    DBhelper dBhelper;
    SQLiteDatabase db;
    EditText id;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_one);

        utils = new Utils(this);
        dBhelper = new DBhelper(this);

        id = findViewById(R.id.txtId);
        tv = findViewById(R.id.txtDisplay);
    }

    public void display() {
        db = dBhelper.getReadableDatabase();

        try {
            Cursor cursor = db.rawQuery("SELECT * FROM student WHERE _id = " + Integer.parseInt(id.getText().toString()), null);

            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                tv.setText("");
                while (cursor.isAfterLast() == false) {
                    String row = String.format(
                            "\n%d %s %d %s %s",
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getString(3),
                            cursor.getString(4)
                    );
                    tv.append(row);

//                    or

//                    tv.append("\n" +
//                            cursor.getInt(0) + " " +
//                            cursor.getString(1) + " " +
//                            cursor.getInt(2) + " " +
//                            cursor.getString(3) + " " +
//                            cursor.getString(4));

                    cursor.moveToNext();
                }
            } else {
                utils.showAlert("Alert", "No Records Found");
                tv.setText("No Records Found");
            }

            db.close();

        } catch (Exception ex) {
            utils.showAlert("Alert", "Something went wrong!");
        }

    }

    public void onSubmit(View view) {
        display();
    }

    public void onCancel(View view) {
    }
}
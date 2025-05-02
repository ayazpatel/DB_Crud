package com.ayaz.db_crud;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Insert extends AppCompatActivity {
    Utils utils;
    DBhelper dBhelper;
    SQLiteDatabase db;
    EditText name, age, dob, tob;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        utils = new Utils(this);

        dBhelper = new DBhelper(this);

        name = findViewById(R.id.txtName);
        age = findViewById(R.id.txtAge);
        dob = findViewById(R.id.txtDob);
        tob = findViewById(R.id.txtTob);

        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dob.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        };

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                tob.setText(hourOfDay + ":" + minute);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get((Calendar.DAY_OF_MONTH)) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        datePickerDialog = new DatePickerDialog(this, onDateSetListener, year, month, dayOfMonth);
        timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hourOfDay, minute, true);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        tob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

    }

    public void insert() {
        ContentValues cv = new ContentValues();
        cv.put("name", name.getText().toString());
        cv.put("age", Integer.parseInt(age.getText().toString()));
        cv.put("dob", dob.getText().toString());
        cv.put("tob", tob.getText().toString());

        db = dBhelper.getWritableDatabase();

        long res = db.insert("student", null, cv);

        if (res != 1) {
            utils.showAlert("Alert", "Record Inserted");
        } else{
            utils.showAlert("Alert", "Record Not Inserted");
        }
//        db.close();
    }

    public void onSubmit(View view) {
        insert();
    }

    public void onCancel(View view) {
//        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void showAlert() {

    }
}
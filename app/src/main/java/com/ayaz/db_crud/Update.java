package com.ayaz.db_crud;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class Update extends AppCompatActivity {
    Utils utils;
    DBhelper dBhelper;
    SQLiteDatabase db;
    ArrayAdapter<Integer> arrayAdapter;
    ArrayList<Integer> arrayList;
    Spinner spId;

    EditText edtName, edtAge, edtDob, edtTob;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    Integer _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        utils = new Utils(this);
        dBhelper = new DBhelper(this);

        arrayList = new ArrayList<Integer>();

        spId = findViewById(R.id.spId);
        edtName = findViewById(R.id.txtName);
        edtAge = findViewById(R.id.txtAge);
        edtDob = findViewById(R.id.txtDob);
        edtTob = findViewById(R.id.txtTob);

        loadSpinner();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get((Calendar.DAY_OF_MONTH)) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edtDob.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, dayOfMonth);
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                edtTob.setText(hourOfDay + ":" + minute);
            }
        }, hourOfDay, minute, true);
        edtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        edtTob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        spId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                _id = arrayList.get(position);

                db = dBhelper.getReadableDatabase();

                try {
                    Cursor cursor = db.rawQuery("SELECT * FROM student WHERE _id = " + _id, null);
                    if (cursor != null) {
                        if (cursor.getCount() != 0) {
                            cursor.moveToFirst();
                            edtName.setText(cursor.getString(1));
                            edtAge.setText(String.valueOf(cursor.getInt(2)));
                            edtDob.setText(cursor.getString(3));
                            edtTob.setText(cursor.getString(4));
                        } else {
                            utils.showAlert("Alert", "Record not found in student table");
                        }
                    } else {
                        utils.showAlert("Alert", "Cursor is not initialized");
                    }
                } catch (Exception ex) {
                    utils.showAlert("Alert", ex.getMessage());
                }

                db.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    private void loadSpinner() {
        db = dBhelper.getReadableDatabase();

        try {
            Cursor cursor = db.rawQuery("SELECT _id FROM student", null);
            if (cursor != null) {
                if (cursor.getCount() != 0) {
                    cursor.moveToFirst();
                    while (cursor.isAfterLast() == false) {
                        arrayList.add(cursor.getInt(0));
                        cursor.moveToNext();
                    }
                } else {
                    utils.showAlert("Alert", "Record not found in student table");
                }
            } else {
                utils.showAlert("Alert", "Cursor is not initialized");
            }
        } catch (Exception ex) {
            utils.showAlert("Alert", ex.getMessage());
        }

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);

        spId.setAdapter(arrayAdapter);

//        db.close();
    }

    private void update() {
        db = dBhelper.getWritableDatabase();

        try {
            ContentValues cv = new ContentValues();
            cv.put("name", edtName.getText().toString());
            cv.put("age", Integer.parseInt(edtAge.getText().toString()));
            cv.put("dob", edtDob.getText().toString());
            cv.put("tob", edtTob.getText().toString());
            long res = db.update("student", cv, "_id = " + _id, null);
            if (res > 0) {
                utils.showAlert("Alert", "Update Success");
            } else {
                utils.showAlert("Alert", "Update Failed");
            }
        }catch (Exception ex) {
            utils.showAlert("Alert", ex.getMessage());
        }

//        db.close();
    }

    public void onSubmit(View view) {
        update();
    }

    public void onCancel(View view) {
    }
}
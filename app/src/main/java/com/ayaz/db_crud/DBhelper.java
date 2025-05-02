package com.ayaz.db_crud;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class DBhelper extends SQLiteOpenHelper {

    static class Constants {

    }

    Context context;

    public DBhelper(@Nullable Context context) {
        super(context, "student", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL("CREATE TABLE student (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "age TEXT," +
                    "dob TEXT," +
                    "tob TEXT" +
                    ");");
        } catch (Exception ex) {
            showToastError(ex.getMessage());
            showAlertDialogError("onCreate() - Error Alert", ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS student");
        } catch (Exception ex) {
            showToastError(ex.getMessage());
            showAlertDialogError("onUpgrade() - Error Alert", ex.getMessage());

        }
    }
    
    public void showToastError(String Message) {
        Toast.makeText(context, Message, Toast.LENGTH_SHORT).show();
    }

    public void showAlertDialogError(String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .show();
    }
}

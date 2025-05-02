package com.ayaz.db_crud;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class Utils {
    Context context;

    Utils(@Nullable Context context){
        this.context = context;
    };

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void showAlert(String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton("OK", null)
                .show();
    }

    public void showCustomAlert(String title, String message,
                                @Nullable String positiveText, @Nullable Runnable onPositive,
                                @Nullable String negativeText, @Nullable Runnable onNegative,
                                @Nullable String neutralText, @Nullable Runnable onNeutral) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false);

        if (positiveText != null) {
            builder.setPositiveButton(positiveText, (dialog, which) -> {
                if (onPositive != null) onPositive.run();
            });
        }

        if (negativeText != null) {
            builder.setNegativeButton(negativeText, (dialog, which) -> {
                if (onNegative != null) {
                    onNegative.run();
                } else {
                    dialog.dismiss();
                }
            });
        }

        if (neutralText != null) {
            builder.setNeutralButton(neutralText, (dialog, which) -> {
                if (onNeutral != null) {
                    onNeutral.run();
                } else if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            });
        }

        builder.show();
    }
}

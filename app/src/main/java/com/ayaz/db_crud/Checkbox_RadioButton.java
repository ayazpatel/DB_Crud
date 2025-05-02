package com.ayaz.db_crud;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Checkbox_RadioButton extends AppCompatActivity {
    CheckBox cbJava, cbPython, cbAndroid;
    RadioGroup radioGroupGender;
    TextView tvResult;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox_radio_button);

        cbJava = findViewById(R.id.cbJava);
        cbPython = findViewById(R.id.cbPython);
        cbAndroid = findViewById(R.id.cbAndroid);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        tvResult = findViewById(R.id.tvResult);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbJava.isChecked()) {
                    tvResult.append("Java\t");
                }
                if (cbPython.isChecked()) {
                    tvResult.append("Python\t");
                }
                if (cbAndroid.isChecked()) {
                    tvResult.append("Android\t");
                }

                int SeclectedRadioButtonID = radioGroupGender.getCheckedRadioButtonId();
                if (SeclectedRadioButtonID != -1) {
                    RadioButton radioButton = findViewById(SeclectedRadioButtonID);
                    tvResult.append(radioButton.getText() + "\t");
                } else {
                    Toast.makeText(Checkbox_RadioButton.this, "Gender Not Selected", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
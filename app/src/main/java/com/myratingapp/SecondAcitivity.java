package com.myratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SecondAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_acitivity);
       Intent intent =getIntent();
        EditText rating = findViewById(R.id.Rating);
        EditText date = findViewById(R.id.date);
        EditText seek = findViewById(R.id.seek);
        EditText spin  = findViewById(R.id.spin);
        EditText radioBtn = findViewById(R.id.Radiobtn);
        EditText descr = findViewById(R.id.descr);

        String Rating = intent.getStringExtra("Rating");
        String Date = intent.getStringExtra("Date");
        String SeekBar = intent.getStringExtra("SeekBar");
        String Spinner = intent.getStringExtra("Spiner");
        String Radiobtn = intent.getStringExtra("RadioButton");
        String descri = intent.getStringExtra("desc");

        rating.setText("Rating = "+ Rating);
        date.setText("Date = "+ Date);
        seek.setText("SeekBar Value = "+ SeekBar);
        spin.setText("Spinner Value = "+ Spinner);
        radioBtn.setText("Gender  = "+ Radiobtn);
        descr.setText("Description = " + descri);


    }
}

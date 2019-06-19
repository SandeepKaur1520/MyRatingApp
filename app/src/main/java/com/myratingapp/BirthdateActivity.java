package com.myratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

public class BirthdateActivity extends AppCompatActivity {
    Spinner spinnerdate, spinneryear,spinnermonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthdate);
        /*spinnerdate = findViewById(R.id.spinnerdate);
        spinneryear = findViewById(R.id.spinneryear);
        spinnermonth=findViewById(R.id.spinnermonth);

        spinnerdate.setOnItemSelectedListener(this );
        spinneryear.setOnItemSelectedListener(this);
        spinnermonth.setOnItemSelectedListener(this);

        adate = new Integer[31];
        year = new Integer[101];
        month= new Integer[12];

        for (int i = 0; (i < 31); i++) {

            adate[i] = i;
        }

        for (int i = 0; (i < 101); i++) {
            a1 = i + 1950;
            year[i] = a1;
        }
        for (int i = 0; (i <12); i++) {

            month[i] =i+1;
        }

        ArrayAdapter<Integer> arrayAdapter1 = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,adate);
        arrayAdapter1.setDropDownViewResource(simple_spinner_dropdown_item);
        spinnerdate.setAdapter(arrayAdapter1);

        ArrayAdapter<Integer> arrayAdapter3 = new ArrayAdapter<Integer>(this, simple_spinner_item,year);
        arrayAdapter3.setDropDownViewResource(simple_spinner_dropdown_item);
        spinneryear.setAdapter(arrayAdapter3);

        ArrayAdapter<Integer> array = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,month);
        array.setDropDownViewResource(simple_spinner_dropdown_item);
        spinnermonth.setAdapter(array);


        finaldate = getIntent().getStringExtra("date");

        builder = new AlertDialog.Builder(this);*/

    }
}

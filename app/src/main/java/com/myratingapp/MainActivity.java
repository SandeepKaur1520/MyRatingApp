package com.myratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar.*;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.sql.BatchUpdateException;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    final String[] Storage = {"","","","","","","","",""};
    @SuppressLint("NewApi")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar seekBar = findViewById(R.id.seekBar);
        final ProgressBar progressBar =findViewById(R.id.progressBar);
        final RatingBar ratingBar =findViewById(R.id.ratingBar);
        ToggleButton toggleButton =findViewById(R.id.toggleButton);
        RadioButton male=findViewById(R.id.male);
        RadioButton female=findViewById(R.id.female);
        final Spinner spinner =findViewById(R.id.spinner);
        final LinearLayout linearLayout = findViewById(R.id.descLayout);
        linearLayout.setVisibility(View.GONE);
        Switch switchbtn = findViewById(R.id.switchBtn);
        Button btnSubmit = findViewById(R.id.btmSubmit);
        int startYear= 0 ,starthMonth = 0, startDay =0;
        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this, MainActivity.this, startYear, starthMonth, startDay);
       // DatePicker datePicker = findViewById(R.id.datePicker);
        final Boolean[] status = {false};
        final EditText desc = findViewById(R.id.edDesc);

        Button btnDate = findViewById(R.id.btnDtae);

        btnDate.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    datePickerDialog.show();
                }
            });

     /*   datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
               String date = (i2 +" " +  i1 +" "+i);
               Storage[1]=date;
                Toast.makeText(MainActivity.this,date, Toast.LENGTH_SHORT).show();
            }
        });*/
        switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    linearLayout.setVisibility(View.VISIBLE);
                    status[0] =false;
                }
                else {
                    linearLayout.setVisibility(View.GONE);
                    status[0] = true;
                }

            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getSelectedItem().toString();
                Storage[3]=item;
                Toast.makeText(MainActivity.this,item, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this,"Nothing Changed", Toast.LENGTH_SHORT).show();

            }


        });
        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Storage[4]="Male";
                    Toast.makeText(MainActivity.this,"Male = " + Boolean.toString(b), Toast.LENGTH_SHORT).show();
                }
                else {
                    Storage[4]="Female";
                    Toast.makeText(MainActivity.this,"Female =  " + Boolean.toString(b), Toast.LENGTH_SHORT).show();
                }
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(MainActivity.this,"True " + Boolean.toString(b), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"False "+ Boolean.toString(b), Toast.LENGTH_SHORT).show();
                }
            }
        });
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener(){

            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Storage[0] =Float.toString(v);
                Toast.makeText(MainActivity.this, Float.toString(v), Toast.LENGTH_SHORT).show();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int Value = seekBar.getProgress();
                progressBar.setProgress(Value);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                int Value = seekBar.getProgress();
                progressBar.setProgress(Value);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int Value = seekBar.getProgress();
                Storage[2] = (Value+" ");
                progressBar.setProgress(Value);

            }
        });

        btnSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecondAcitivity.class);
                intent.putExtra("Rating",Storage[0]);
                intent.putExtra("Date",Storage[1]);
                intent.putExtra("SeekBar",Storage[2]);
                intent.putExtra("Spiner",Storage[3]);
                intent.putExtra("RadioButton",Storage[4]);
                intent.putExtra("desc",desc.getText().toString());
                linearLayout.setVisibility(View.GONE);
                status[0] = true;

                startActivity(intent);

            }
        });

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String date = ("Date:" +i2 +"Month: " +  i1 +"Year: "+i);
        Storage[1]=date;
        Toast.makeText(MainActivity.this,date, Toast.LENGTH_SHORT).show();
    }
}

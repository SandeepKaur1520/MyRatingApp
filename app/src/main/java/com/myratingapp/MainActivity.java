package com.myratingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
    ProgressDialog progressDialog;
    int progressDialogStatus =0;
    long fileSize = 0;
    Handler progressDialogHandler =new Handler();
    //@SuppressLint("NewApi")
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
        int startYear= 2019 ,starthMonth = 06, startDay =20;
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

                progressDialog = new ProgressDialog(view.getContext()) ;
                progressDialog.setCancelable(true);
                progressDialog.setMessage("Loading .....");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setProgress(0);
                progressDialog.setMax(100);
                progressDialogStatus = 0 ;
                fileSize = 0;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressDialogStatus < 100) {
                            progressDialogStatus = downloadFile();
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            final boolean post = progressDialogHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.setProgress(progressDialogStatus);
                                }
                            });
                        }
                        if(progressDialogStatus >=100){
                            try {
                                Thread.sleep(2000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                        }
                    }
                }).start();


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
    public  int downloadFile(){
        while (fileSize <=1000000){
            fileSize++;
            if(fileSize == 100000){
                return  10;
            }
            if(fileSize == 200000){
                return  20;
            }
            if(fileSize == 200000){
                return  20;
            }
            if(fileSize == 300000){
                return  30;
            }
            if(fileSize == 400000) {
                return 40;
            }
            if(fileSize == 500000){
                return  50;}
            if(fileSize == 600000){
                return  60;}
            if(fileSize == 700000){
                return  70;}

        }
        return 100;
    }
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String date = ("Date:" +i2 +"Month: " +  i1 +"Year: "+i);
        Storage[1]=date;
        Toast.makeText(MainActivity.this,date, Toast.LENGTH_SHORT).show();
    }
}

package com.example.lab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class Picker extends AppCompatActivity {

    DatePicker datepicker;
    Button displayDate;
    TextView textview1;
    TimePicker timepicker;
    Button changetime;
    TextView textview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);

        textview1 =  findViewById(R.id.textView1);
        datepicker =  findViewById(R.id.datePicker);
        displayDate = findViewById(R.id.button1);
        textview1.setText("Current Date: " + getCurrentDate());

        textview2=findViewById(R.id.textView2);
        timepicker=findViewById(R.id.timepicker);
        timepicker.setIs24HourView(true);
        changetime=findViewById(R.id.button2);
        textview2.setText(getCurrentTime());

        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textview1.setText("Change Date: " + getCurrentDate());
            }

        });

        changetime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textview2.setText(getCurrentTime());
            }
        });
    }

    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();;
        builder.append((datepicker.getMonth() + 1)+"/");//month is 0 based
        builder.append(datepicker.getDayOfMonth()+"/");
        builder.append(datepicker.getYear());
        return builder.toString();
    }

    public String getCurrentTime(){
        String currentTime="Current Time: "+timepicker.getCurrentHour()+":"+timepicker.getCurrentMinute();
        return currentTime;
    }
}
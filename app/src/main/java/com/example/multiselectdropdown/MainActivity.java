package com.example.multiselectdropdown;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    //declaration of variables
    TextView tvday;
    boolean[] selectedDay;
    ArrayList<Integer> dayList = new ArrayList<Integer>();
    String[] dayArray = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday","Sunday"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialization of variables
        tvday = findViewById(R.id.tv_day);

        //initializing selected day array
        selectedDay = new boolean[dayArray.length];

        tvday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initialize alert Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        MainActivity.this
                );
                //set Title
                builder.setTitle("Select Day");

                //set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(dayArray, selectedDay, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        //check Condition
                        if (b){
                            //when checkbox is selected
                            //add position in day list
                            dayList.add(i);
                            //sort daylist
                            Collections.sort(dayList);
                        }else {
                            //when checkbox is unselected
                            //remove position
                            dayList.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //initialize string builder
                        StringBuilder stringBuilder = new StringBuilder();
                        //using a for loop
                        for (int j = 0; j<dayList.size(); j++){
                            //Concat array value
                            stringBuilder.append(dayArray[dayList.get(j)]);
                            //check condition
                            if (j !=dayList.size()-1){
                                //when value of j is not equal to daylist size -1
                                //Add comma
                                stringBuilder.append(",");
                            }
                        }
                        //set text on textview
                        tvday.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //dismiss Dialog
                        dialogInterface.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //use for loop
                        for (int j = 0; j<selectedDay.length;  j++){
                            //Remove All selected
                            selectedDay[j] = false;
                            //clear dayList
                            dayList.clear();
                            //clear TextView Value
                            tvday.setText("");
                        }
                    }
                });
                //Show Dialog
                builder.show();
            }
        });
    }
}
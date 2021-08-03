package com.example.timestable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView multiplicationTable;
    SeekBar tableNumber;

    //****
    public void Table(int progress) {

        ArrayList<String> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbers.add(String.valueOf(progress * i));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numbers);
        multiplicationTable.setAdapter(arrayAdapter);
    }

    //*****

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //**********************

        multiplicationTable = findViewById(R.id.listView);
        tableNumber = findViewById(R.id.seekBar2);
        tableNumber.setMax(20);
        tableNumber.setProgress(10);
        Table(10);

        tableNumber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(progress==0) {
                    progress = 1;
                    seekBar.setProgress(1);
                    Table(1);
                }
                Table(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
package com.example.higherorlower;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random r = new Random();
    int random;

    public void guess (View view)
    {
        Log.i("number", Integer.toString(random));
        EditText number = findViewById(R.id.editTextNumber);
        int guessValue = Integer.parseInt(number.getText().toString());
        Log.i("no entered", Integer.toString(guessValue));
        String message;

        if(guessValue < random) {
            message = "Higher";
        }

        else if(guessValue > random) {
            message = "Lower";
        }
        
        else {
            message = "You got it right!\nTry with new number";
            random = r.nextInt(20) + 1;
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        random = r.nextInt(20) + 1;

    }
}
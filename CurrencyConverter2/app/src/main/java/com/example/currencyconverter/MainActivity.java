package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convert(View view)
    {
        EditText currency = (EditText) findViewById(R.id.editTextAmount);
        double cad;
        cad = Integer.parseInt(currency.getText().toString()) * 60.31;
        Toast.makeText(this, currency.getText().toString() + "cad in inr = " + cad, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
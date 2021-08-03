package com.example.multiscreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    TextView name;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        name = findViewById(R.id.textView);
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            s = extras.getString("guess") + " is " + extras.getString("looks");
            name.setText(s);
        }

        name.setOnClickListener(v -> {
            Intent intent = getIntent();
            intent.putExtra("message_back", s);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
package com.example.multiscreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private final int   REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enterButton = findViewById(R.id.button);
        name = findViewById(R.id.editTextTextPersonName);

        enterButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Activity2.class);
            intent.putExtra("guess", name.getText().toString());
            intent.putExtra("age", 20);
            intent.putExtra("looks", "Handsome");
            if(name.getText().toString().isEmpty())
                Toast.makeText(this, "Enter a name please", Toast.LENGTH_SHORT).show();
            else {
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE ) {
            assert data != null;
            Toast.makeText(MainActivity.this, data.getStringExtra("message_back"), Toast.LENGTH_SHORT).show();
        }
    }
}
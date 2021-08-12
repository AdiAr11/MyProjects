package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactmanager.model.Contact;
import com.example.contactmanager.model.ContactViewModel;

public class NewContact extends AppCompatActivity {

    private EditText enterName, enterOccupation;
    private Button saveButton;
    private ContactViewModel contactViewModel;
    public static final String NAME_REPLY = "name_reply";
    public static final String OCCUPATION_REPLY = "occupation_reply";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        enterName = findViewById(R.id.enter_name_editText);
        enterOccupation = findViewById(R.id.enter_occupation_editText);
        saveButton = findViewById(R.id.save_button);
        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(NewContact.this.getApplication()).create(ContactViewModel.class);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent reply_intent = new Intent();

                if(!TextUtils.isEmpty(enterName.getText()) && !TextUtils.isEmpty(enterOccupation.getText())) {
                    String name = enterName.getText().toString();
                    String occupation = enterOccupation.getText().toString();
                    reply_intent.putExtra(NAME_REPLY, name);
                    reply_intent.putExtra(OCCUPATION_REPLY, occupation);
                    setResult(RESULT_OK, reply_intent);
                }
                else {
                    setResult(RESULT_CANCELED, reply_intent);
                }
                finish();

            }
        });
    }
}
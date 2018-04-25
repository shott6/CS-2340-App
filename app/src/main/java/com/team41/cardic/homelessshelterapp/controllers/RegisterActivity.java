package com.team41.cardic.homelessshelterapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.team41.cardic.homelessshelterapp.model.Admin;
import com.team41.cardic.homelessshelterapp.model.HomelessPerson;
import com.team41.cardic.homelessshelterapp.model.User;

/**
 * A login screen that offers registration via username/password.
 */
public class RegisterActivity extends AppCompatActivity {


    // UI references.
    private boolean adminChecked;
    private final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Set up the login form.
        CheckBox admin = findViewById(R.id.adminCheckbox);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminChecked = ((CheckBox) view).isChecked();
            }
        });

        Button RegisterButton = findViewById(R.id.RegisterButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mfirstName = findViewById(R.id.firstName);
                EditText mlastName = findViewById(R.id.lastName);
                EditText musername = findViewById(R.id.username);
                EditText mpassword = findViewById(R.id.password);
                EditText memail = findViewById(R.id.email_forgotten);
                EditText madminKey = findViewById(R.id.adminKey);

                Editable firstNameEditable = mfirstName.getText();
                String firstName = firstNameEditable.toString();

                Editable lastNameEditable = mlastName.getText();
                String lastName = lastNameEditable.toString();

                Editable usernameEditable = musername.getText();
                String username = usernameEditable.toString();

                Editable passwordEditable = mpassword.getText();
                String password = passwordEditable.toString();

                Editable emailEditable = memail.getText();
                String email = emailEditable.toString();

                Editable adminKeyEditable = madminKey.getText();
                String adminKey = adminKeyEditable.toString();

                if (!adminKey.equals("")) {
                    if (adminKey.equals("8u42") && adminChecked) {
                        writeNewUser(firstName, lastName, username, password, email);
                    } else {
                        madminKey.setError("The authentication key you have entered is incorrect."
                        + " Either uncheck the Admin box and leave this field blank or"
                        + " re-enter the correct authentication key and make sure to check"
                        + " the Admin box.");
                    }
                } else if ((adminKey.equals("")) && adminChecked) {
                    madminKey.setError("Please enter an authentication key.");
                } else {
                    writeNewUser(firstName, lastName, username, password, email);
                }
                if ((adminKey.equals("8u42") && adminChecked) || (adminKey == null && !adminChecked)) {
                    Intent intent = new Intent(getBaseContext(), OpeningActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), OpeningActivity.class);
                startActivity(intent);
            }
        });
    }

    private void writeNewUser(String fName, String lName, String uName, String pWord, String _email) {
        DatabaseReference usersRef = mDatabase.child("users");
        DatabaseReference usernameRef = usersRef.child(uName);
        if (adminChecked) {
            Admin toAdd = new Admin(fName, lName, uName, pWord, _email);
            usernameRef.setValue(toAdd);
        } else {
            HomelessPerson toAdd = new HomelessPerson(fName, lName, uName, pWord, _email);
            usernameRef.setValue(toAdd);
            DatabaseReference curSheltRef = usernameRef.child("currentShelter");
            curSheltRef.setValue(null);
        }
    }
}
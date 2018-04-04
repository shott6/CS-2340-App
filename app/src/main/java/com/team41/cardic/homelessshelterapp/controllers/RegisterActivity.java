package com.team41.cardic.homelessshelterapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.team41.cardic.homelessshelterapp.controllers.R;
import com.team41.cardic.homelessshelterapp.model.Admin;
import com.team41.cardic.homelessshelterapp.model.HomelessPerson;
import com.team41.cardic.homelessshelterapp.model.Model;
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
                Model model = Model.getInstance();
                EditText mfirstName = findViewById(R.id.firstName);
                EditText mlastName = findViewById(R.id.lastName);
                EditText musername = findViewById(R.id.username);
                EditText mpassword = findViewById(R.id.password);

                String firstName = mfirstName.getText().toString();
                String lastName = mlastName.getText().toString();
                String username = musername.getText().toString();
                String password = mpassword.getText().toString();
                User user;
                if (adminChecked) {
                    user = new    Admin(firstName, lastName, username, password);
                } else {
                    user = new HomelessPerson(firstName, lastName, username, password);
                }
                Log.d("thisone", user.toString());
                model.addUser(user);
                writeNewUser(firstName, lastName, username, password);
                Intent intent = new Intent(getBaseContext(), OpeningActivity.class);
                startActivity(intent);
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

    private void writeNewUser(String fName, String lName, String uName, String pWord) {
        if (adminChecked) {
            Admin toAdd = new Admin(fName, lName, uName, pWord);
            mDatabase.child("users").child(uName).setValue(toAdd);
        } else {
            HomelessPerson toAdd = new HomelessPerson(fName, lName, uName, pWord);
            mDatabase.child("users").child(uName).setValue(toAdd);
            mDatabase.child("users").child(uName).child("currentShelter").setValue(null);
        }
    }
}
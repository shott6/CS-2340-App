package com.team41.cardic.homelessshelterapp.controllers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.team41.cardic.homelessshelterapp.controllers.R;
import com.team41.cardic.homelessshelterapp.model.Admin;
import com.team41.cardic.homelessshelterapp.model.HomelessPerson;
import com.team41.cardic.homelessshelterapp.model.Model;
import com.team41.cardic.homelessshelterapp.model.User;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers registration via username/password.
 */
public class RegisterActivity extends AppCompatActivity {


    // UI references.
    boolean adminChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Set up the login form.
        CheckBox admin = (CheckBox) findViewById(R.id.adminCheckbox);
        admin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                adminChecked = true;
            }
                                 });

        Button RegisterButton = findViewById(R.id.RegisterButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Model model = Model.getInstance();
                EditText mfirstName = (EditText) findViewById(R.id.firstName);
                EditText mlastName = (EditText) findViewById(R.id.lastName);
                EditText musername = (EditText) findViewById(R.id.username);
                EditText mpassword = (EditText) findViewById(R.id.password);

                String firstName = mfirstName.getText().toString();
                String lastName = mlastName.getText().toString();
                String username = musername.getText().toString();
                String password = mpassword.getText().toString();
                User user;
                if (adminChecked) {
                    user = (User) new Admin(firstName, lastName, username, password);
                } else {
                    user = (User) new HomelessPerson(firstName, lastName, username, password);
                }
                model.getUsers();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), OpeningActivity.class);
                startActivity(intent);
            }
        });
    }
}
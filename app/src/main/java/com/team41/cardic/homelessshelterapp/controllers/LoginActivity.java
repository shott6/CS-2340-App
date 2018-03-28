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
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team41.cardic.homelessshelterapp.controllers.R;
import com.team41.cardic.homelessshelterapp.model.Admin;
import com.team41.cardic.homelessshelterapp.model.HomelessPerson;
import com.team41.cardic.homelessshelterapp.model.Model;
import com.team41.cardic.homelessshelterapp.model.Shelter;
import com.team41.cardic.homelessshelterapp.model.User;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends AppCompatActivity {


    private static final String dummyUsername = "user";
    private static final String dummyPassword = "pass";

    private EditText mUserView;
    private EditText mPasswordView;
    Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserView = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);


        Button SignInButton = findViewById(R.id.sign_in_button);
        SignInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("users").child(mUserView.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    HomelessPerson refHomeless;
                    Admin refAdmin;
                    String uRefHomeless;
                    String pRefHomeless;
                    String uRefAdmin;
                    String pRefAdmin;
                    boolean isAdmin;
                    boolean matched = false;
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        refHomeless = dataSnapshot.getValue(HomelessPerson.class);
                        refAdmin = dataSnapshot.getValue(Admin.class);
                        if (refHomeless == null || refAdmin == null) {
                            mUserView.setError("Invalid username");
                            mPasswordView.setError("Invalid password");
                        } else {
                            isAdmin = refAdmin.getAdmin();
                            uRefAdmin = refAdmin.getUsername();
                            pRefAdmin = refAdmin.getPassword();
                            uRefHomeless = refHomeless.getUsername();
                            pRefHomeless = refHomeless.getPassword();
                            if (mUserView.getText().toString().equals(uRefAdmin) && mPasswordView.getText().toString().equals(pRefAdmin)) {
                                matched = true;
                            } else if (mUserView.getText().toString().equals(uRefHomeless) && mPasswordView.getText().toString().equals(pRefHomeless)) {
                                matched = true;
                            }
                            if (matched) {
                                if (refAdmin.getAdmin()) {
                                    model.setCurrentUser(refAdmin);
                                } else {
                                    model.setCurrentUser(refHomeless);
                                }
                            } else {
                                mUserView.setError("Invalid username");
                                mPasswordView.setError("Invalid password");
                            }
                            if (model.getCurrentUser() instanceof HomelessPerson) {
                                DatabaseReference userListRef = FirebaseDatabase.getInstance().getReference().child("users").child(uRefHomeless);
                                userListRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Shelter curShelt = (Shelter)dataSnapshot.child("currentShelter").getValue(Shelter.class);
                                        ((HomelessPerson) model.getCurrentUser()).setCurrentShelter(curShelt);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }
                            Log.d("CHECKING", "currentUser: " + model.getCurrentUser().toString());
                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

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
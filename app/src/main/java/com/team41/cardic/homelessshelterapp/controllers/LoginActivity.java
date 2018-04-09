package com.team41.cardic.homelessshelterapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team41.cardic.homelessshelterapp.controllers.R;
import com.team41.cardic.homelessshelterapp.model.Admin;
import com.team41.cardic.homelessshelterapp.model.HomelessPerson;
import com.team41.cardic.homelessshelterapp.model.Model;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText mUserView;
    private EditText mPasswordView;
    private final Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserView = findViewById(R.id.username);
        mPasswordView = findViewById(R.id.password);


        Button SignInButton = findViewById(R.id.sign_in_button);
        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable userViewEditable = mUserView.getText();
                final String userViewString = userViewEditable.toString();
                Editable passwordViewEditable = mPasswordView.getText();
                final String passwordViewString = passwordViewEditable.toString();

                FirebaseDatabase dataInstance = FirebaseDatabase.getInstance();
                DatabaseReference dataRef = dataInstance.getReference();
                DatabaseReference usersRef = dataRef.child("users");
                DatabaseReference userViewRef = usersRef.child(userViewString);

                userViewRef.addListenerForSingleValueEvent(new ValueEventListener() {
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
                        if ((refHomeless == null) || (refAdmin == null)) {
                            mUserView.setError("Invalid username");
                            mPasswordView.setError("Invalid password");
                        } else {
                            isAdmin = refAdmin.getAdmin();
                            uRefAdmin = refAdmin.getUsername();
                            pRefAdmin = refAdmin.getPassword();
                            uRefHomeless = refHomeless.getUsername();
                            pRefHomeless = refHomeless.getPassword();
                            if (userViewString.equals(uRefAdmin) &&
                                    passwordViewString.equals(pRefAdmin)) {
                                matched = true;
                            } else if (userViewString.equals(uRefHomeless) &&
                                        passwordViewString.equals(pRefHomeless)) {
                                matched = true;
                            }
                            if (matched) {
                                if (isAdmin) {
                                    model.setCurrentUser(refAdmin);
                                } else {
                                    model.setCurrentUser(refHomeless);
                                }
                            } else {
                                mUserView.setError("Invalid username");
                                mPasswordView.setError("Invalid password");
                            }
                            if (model.getCurrentUser() instanceof HomelessPerson) {
                                FirebaseDatabase dataInstance = FirebaseDatabase.getInstance();
                                DatabaseReference dataRef = dataInstance.getReference();
                                DatabaseReference usersRef = dataRef.child("users");
                                DatabaseReference userListRef = usersRef.child(uRefHomeless);
                                userListRef.addListenerForSingleValueEvent(new ValueEventListener(){
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        DataSnapshot currentSheltSnap = dataSnapshot.
                                                                        child("currentShelter");
                                        DataSnapshot numCheckedInSnap = dataSnapshot.
                                                                        child("numberCheckedIn");
                                        DataSnapshot checkedInSnap = dataSnapshot.
                                                                     child("checkedIn");

                                        if(currentSheltSnap.getValue() == null){
                                            ((HomelessPerson) model.getCurrentUser()).
                                                    setCurrentShelter(-1);
                                        } else {
                                            int curShelt = (currentSheltSnap.
                                                            getValue(Integer.class));
                                            ((HomelessPerson) model.getCurrentUser()).
                                                    setCurrentShelter(curShelt);
                                        }
                                        int numChecked = (numCheckedInSnap.
                                                            getValue(Integer.class));
                                        ((HomelessPerson) model.getCurrentUser()).
                                                            setNumberCheckedIn(numChecked);
                                        boolean isChecked = (boolean)checkedInSnap.getValue();
                                        ((HomelessPerson) model.getCurrentUser()).
                                                            setCheckedIn(isChecked);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }
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
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), OpeningActivity.class);
                startActivity(intent);
            }
        });
    }
}
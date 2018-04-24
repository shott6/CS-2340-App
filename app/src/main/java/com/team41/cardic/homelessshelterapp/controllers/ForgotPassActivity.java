package com.team41.cardic.homelessshelterapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.team41.cardic.homelessshelterapp.model.Admin;
import com.team41.cardic.homelessshelterapp.model.HomelessPerson;

public class ForgotPassActivity extends AppCompatActivity {

    private EditText mEmailView;
    private EditText mUserView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        mUserView = findViewById(R.id.username_forgot);
        mEmailView = findViewById(R.id.email_forgotten);

        Button sendEmailButton = findViewById(R.id.send_button);
        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Editable emailViewEditable = mEmailView.getText();
                final String emailViewString = emailViewEditable.toString();
                Editable userViewEditable = mUserView.getText();
                final String userViewString = userViewEditable.toString();

                FirebaseDatabase dataInstance = FirebaseDatabase.getInstance();
                DatabaseReference dataRef = dataInstance.getReference();
                DatabaseReference usersRef = dataRef.child("users");
                DatabaseReference emailViewRef = usersRef.child(userViewString);

                emailViewRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    HomelessPerson refHomeless;
                    Admin refAdmin;
                    String emailRefHomeless;
                    String emailRefAdmin;
                    String uHomeless;
                    String uAdmin;
                    String correctPAdmin;
                    String correctPHomeless;
                    boolean isAdmin;
                    boolean matched = false;
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        refHomeless = dataSnapshot.getValue(HomelessPerson.class);
                        refAdmin = dataSnapshot.getValue(Admin.class);
                        if ((refHomeless == null) || (refAdmin == null)) {
                            mUserView.setError("Invalid username");
                            mEmailView.setError("Invalid email.");
                        } else {
                            emailRefAdmin = refAdmin.getEmail();
                            emailRefHomeless = refHomeless.getEmail();
                            uAdmin = refAdmin.getUsername();
                            uHomeless = refHomeless.getUsername();
                            correctPAdmin = refAdmin.getPassword();
                            correctPHomeless = refHomeless.getPassword();
                            isAdmin = refAdmin.getAdmin();
                            if (emailViewString.equals(emailRefAdmin) &&
                                    userViewString.equals(uAdmin)) {
                                matched = true;
                            } else if (emailViewString.equals(emailRefHomeless) &&
                                    userViewString.equals(uHomeless)) {
                                matched = true;
                            }
                            if (matched) {
                                TextView correctPass = (TextView)findViewById(R.id.correct_pass);
                                if (isAdmin) {
                                    correctPass.setText("The correct password is " + correctPAdmin);
                                } else {
                                    correctPass.setText("The correct password is " +
                                    correctPHomeless);
                                }
                                correctPass.setVisibility(View.VISIBLE);
                            } else {
                                mUserView.setError("Invalid username");
                                mEmailView.setError("Invalid email");
                            }
                            /*
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
                            } */
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
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

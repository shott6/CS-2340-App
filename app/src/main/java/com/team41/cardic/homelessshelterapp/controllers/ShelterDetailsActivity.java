package com.team41.cardic.homelessshelterapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.team41.cardic.homelessshelterapp.model.HomelessPerson;
import com.team41.cardic.homelessshelterapp.model.Model;

public class ShelterDetailsActivity extends AppCompatActivity {

    EditText shelterName;
    EditText capacity;
    EditText restrictions;
    EditText longitude;
    EditText latitude;
    EditText specialNotes;
    EditText phoneNumber;
    EditText numberCheckIn;

    Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_details);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button checkIn = findViewById(R.id.checkIn);
        checkIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (model.getCurrentUser() instanceof HomelessPerson) {
                    HomelessPerson person = (HomelessPerson) model.getCurrentUser();
                    if (person.getCheckedIn() != true) {
                        try {
                            if (Integer.parseInt(model.getCurrentShelter().getCapacity()) - Integer.parseInt(numberCheckIn.getText().toString()) > 0) {
                                person.setCheckedIn(true);
                                person.setNumberCheckedIn(Integer.parseInt(numberCheckIn.getText().toString()));
                                person.setCurrentShelter(model.getCurrentShelter().getUniqueKey());
                                DatabaseReference curUser = FirebaseDatabase.getInstance().getReference().child("users").child(person.getUsername());
                                curUser.child("checkedIn").setValue(true);
                                curUser.child("numberCheckedIn").setValue(Integer.parseInt(numberCheckIn.getText().toString()));
                                curUser.child("currentShelter").setValue(model.getCurrentShelter().getUniqueKey());


                                DatabaseReference sheltListRef = FirebaseDatabase.getInstance().getReference().child("shelters");
                                String newCapacity = "" + (Integer.parseInt(model.getCurrentShelter().getCapacity()) - Integer.parseInt(numberCheckIn.getText().toString()));
                                sheltListRef.child("" + model.getCurrentShelter().getUniqueKey())/*.child("Capacity")*/.setValue(Integer.parseInt(newCapacity));
                                model.getCurrentShelter().setCapacity(newCapacity);
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                numberCheckIn.setError("This shelter cannot check in this amount of people.");
                            }
                        } catch (java.lang.NumberFormatException e){
                            numberCheckIn.setError("You tell us the number of people you are trying to check in.");
                        }

                    } else {
                        numberCheckIn.setError("You are already checked into a shelter.");
                    }
                } else {
                    numberCheckIn.setError("Your account is not qualified to check into a shelter.");
                }


            }
        });

        shelterName = (EditText) findViewById(R.id.shelterNameInput);
        shelterName.setText(model.getCurrentShelter().getName());

        capacity = (EditText) findViewById(R.id.capacityInput);
        capacity.setText(model.getCurrentShelter().getCapacity());

        restrictions = (EditText) findViewById(R.id.restrictionsInput);
        restrictions.setText(model.getCurrentShelter().getGender());

        longitude = (EditText) findViewById(R.id.longitudeInput);
        longitude.setText("" + model.getCurrentShelter().getLongitude());

        latitude = (EditText) findViewById(R.id.latitudeInput);
        latitude.setText("" + model.getCurrentShelter().getLatitude());

        specialNotes = (EditText) findViewById(R.id.specialNotesInput);
        specialNotes.setText(model.getCurrentShelter().getSpecialNotes());

        phoneNumber = (EditText) findViewById(R.id.phoneNumberInput);
        phoneNumber.setText(model.getCurrentShelter().getPhoneNumber());

        numberCheckIn = (EditText) findViewById(R.id.numberCheckIn);
    }

}

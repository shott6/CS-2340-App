package com.team41.cardic.homelessshelterapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.team41.cardic.homelessshelterapp.model.Model;

public class ShelterDetailsActivity extends AppCompatActivity {

    EditText shelterName;
    EditText capacity;
    EditText restrictions;
    EditText longitude;
    EditText latitude;
    EditText specialNotes;
    EditText phoneNumber;

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
    }
}

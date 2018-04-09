package com.team41.cardic.homelessshelterapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.team41.cardic.homelessshelterapp.model.HomelessPerson;
import com.team41.cardic.homelessshelterapp.model.Model;
import com.team41.cardic.homelessshelterapp.model.Shelter;

/**
 * This class controls checking in a User to a shelter. If a User decides to check in, the
 * information of that specific shelter will be updated (such as incrementing the capacity).
 * Additionally, this class allows a User to use the back button.
 */
public class ShelterDetailsActivity extends AppCompatActivity {

    private EditText numberCheckIn;

    private final Model model = Model.getInstance();
    private final Shelter modelCurrentShelter = model.getCurrentShelter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_details);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button checkIn = findViewById(R.id.checkIn);
        checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (model.getCurrentUser() instanceof HomelessPerson) {
                    HomelessPerson person = (HomelessPerson) model.getCurrentUser();
                    if (!person.getCheckedIn()) {
                        try {
                            Editable numCheckInEditable = numberCheckIn.getText();
                            String numCheckIn = numCheckInEditable.toString();

                            if ((Integer.parseInt(modelCurrentShelter.getCapacity()) -
                                    Integer.parseInt(numCheckIn)) > 0) {
                                person.setCheckedIn(true);
                                person.setNumberCheckedIn(Integer.parseInt(numCheckIn));
                                person.setCurrentShelter(modelCurrentShelter.getUniqueKey());

                                FirebaseDatabase dataInstance = FirebaseDatabase.getInstance();
                                DatabaseReference dataRef = dataInstance.getReference();
                                DatabaseReference usersRef = dataRef.child("users");
                                DatabaseReference curUser = usersRef.child(person.getUsername());
                                DatabaseReference checkedInRef = curUser.child("checkedIn");
                                DatabaseReference numCheckedInRef = curUser.
                                                                    child("numberCheckedIn");
                                DatabaseReference curSheltRef = curUser.child("currentShelter");

                                checkedInRef.setValue(true);
                                numCheckedInRef.setValue(Integer.parseInt(numCheckIn));
                                curSheltRef.setValue(modelCurrentShelter.getUniqueKey());

                                DatabaseReference sheltListRef = dataRef.child("shelters");
                                String newCapacity = "" + (Integer.parseInt(modelCurrentShelter
                                                            .getCapacity()) -
                                        Integer.parseInt(numCheckIn));
                                int modelCurSheltID = modelCurrentShelter.getUniqueKey();
                                DatabaseReference sheltersCurSheltRef = sheltListRef.
                                                                        child("" + modelCurSheltID);
                                sheltersCurSheltRef.setValue(Integer.parseInt(newCapacity));
                                modelCurrentShelter.setCapacity(newCapacity);
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                numberCheckIn.setError("This shelter cannot check in " +
                                                        "this amount of people.");
                            }
                        } catch (java.lang.NumberFormatException e){
                            numberCheckIn.setError("You tell us the number of people you are " +
                                                    "trying to check in.");
                        }

                    } else {
                        numberCheckIn.setError("You are already checked into a shelter.");
                    }
                } else {
                    numberCheckIn.setError("Your account is not qualified to check " +
                            "into a shelter.");
                }
            }
        });

        TextView shelterName = findViewById(R.id.shelterNameInput);
        shelterName.setText(modelCurrentShelter.getName());

        TextView capacity = findViewById(R.id.capacityInput);
        capacity.setText(modelCurrentShelter.getCapacity());

        TextView restrictions = findViewById(R.id.restrictionsInput);
        restrictions.setText(modelCurrentShelter.getGender());

        TextView longitude = findViewById(R.id.longitudeInput);
        longitude.setText("" + modelCurrentShelter.getLongitude());

        TextView latitude = findViewById(R.id.latitudeInput);
        latitude.setText("" + modelCurrentShelter.getLatitude());

        TextView specialNotes = findViewById(R.id.specialNotesInput);
        specialNotes.setText(modelCurrentShelter.getSpecialNotes());

        TextView phoneNumber = findViewById(R.id.phoneNumberInput);
        phoneNumber.setText(modelCurrentShelter.getPhoneNumber());

        numberCheckIn = findViewById(R.id.numberCheckIn);
    }

}

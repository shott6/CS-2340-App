package com.team41.cardic.homelessshelterapp.controllers;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.team41.cardic.homelessshelterapp.model.HomelessPerson;
import com.team41.cardic.homelessshelterapp.model.Model;
import com.team41.cardic.homelessshelterapp.model.Shelter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.team41.cardic.homelessshelterapp.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class controls the activities that can occur on the Main page when the user enters the app
 * after logging in. This includes activities such as going back, searching for Shelters,
 * logging out, and checking out.
 */
public class MainActivity extends AppCompatActivity {

    private Spinner shelterSpinner;
    private EditText searchBar;
    private TextView errorView;
    private final Model model = Model.getInstance();
    private final Collection<Shelter> shelters = new ArrayList<>();
    private final List<String> shelterNames = new ArrayList<>();
    private String cur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<Shelter> modelShelters = model.getShelters();
        int modelSheltersSize = modelShelters.size();

        searchBar = findViewById(R.id.search_Bar);
        errorView = findViewById(R.id.errorView);
        if (!model.getReadData()) {
            this.readShelterFile();
        } else {
            for (int i = 0; i < modelSheltersSize; i++) {
                Shelter currentModelShelter = modelShelters.get(i);
                shelterNames.add(currentModelShelter.getName());
            }
        }

        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), OpeningActivity.class);
                startActivity(intent);
            }
        });

        final Button checkOutButton = findViewById(R.id.checkOut);
        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (model.getCurrentUser() instanceof HomelessPerson) {
                    HomelessPerson modelCurrentUser = (HomelessPerson) model.getCurrentUser();
                    if (modelCurrentUser.getCheckedIn()) {
                        int modelCurrentUserCurrentSheltID = modelCurrentUser.getCurrentShelter();
                        Shelter modelCurrentUserCurrentShelt = modelShelters.
                                                                get(modelCurrentUserCurrentSheltID);
                        int newCapacity = Integer.
                                            parseInt(modelCurrentUserCurrentShelt.getCapacity());
                        newCapacity = newCapacity + modelCurrentUser.getNumberCheckedIn();

                        FirebaseDatabase dataInstance = FirebaseDatabase.getInstance();
                        DatabaseReference dataRef = dataInstance.getReference();
                        DatabaseReference usersRef = dataRef.child("users");
                        DatabaseReference userRef = usersRef.child(modelCurrentUser.getUsername());
                        DatabaseReference checkedInRef = userRef.child("checkedIn");
                        DatabaseReference numCheckedInRef = userRef.child("numberCheckedIn");
                        DatabaseReference curSheltRef = userRef.child("currentShelter");

                        checkedInRef.setValue(false);
                        numCheckedInRef.setValue(0);
                        curSheltRef.setValue(-1);

                        DatabaseReference sheltListRef = dataRef.child("shelters");
                        DatabaseReference sheltRefCurShelt = sheltListRef.
                                                        child("" + modelCurrentUserCurrentSheltID);
                        sheltRefCurShelt.setValue(newCapacity);
                        modelCurrentUserCurrentShelt.setCapacity("" + newCapacity);
                        modelCurrentUser.setNumberCheckedIn(0);
                        modelCurrentUser.setCheckedIn(false);
                        modelCurrentUser.setCurrentShelter(-1);
                    } else {
                        errorView.setVisibility(View.VISIBLE);
                        errorView.setError("You are not checked into a shelter.");
                    }
                } else {
                    errorView.setVisibility(View.VISIBLE);
                    errorView.setError("You are not checked into a shelter.");
                }

            }
        });

        final Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable searchEditable = searchBar.getText();
                String searchString = searchEditable.toString();
                Log.d("checkText", "search: " + searchString);
                Intent intent = new Intent(getBaseContext(), SearchResultsActivity.class);
                intent.putExtra("SEARCH_STRING", searchString);
                startActivity(intent);
            }

        });

        shelterSpinner = findViewById(R.id.shelterSpinner);

        final ArrayAdapter<String> shelterAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, shelterNames);
        shelterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shelterSpinner.setAdapter(shelterAdapter);

        

        Button selectButton = findViewById(R.id.selectButton);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cur = shelterSpinner.getSelectedItem().toString();
                int index = shelterNames.indexOf(cur);
                model.setCurrentShelter(modelShelters.get(index));

                Intent intent = new Intent(getBaseContext(), ShelterDetailsActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * This method reads the .csv file that contains the list of shelters and parses the list
     * to create unique Shelters based on the information given within the csv
     */
    private void readShelterFile() {
        final List<Shelter> modelShelters = model.getShelters();

        try {
            InputStream is = getResources().openRawResource(R.raw.homeless_shelter_database);
            BufferedReader br=new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line = br.readLine();
            line = br.readLine();
            Log.d("Main", "this is line" + line);


            while (line != null) {
                final String[] tokens = line.split(("#"));
                int id = Integer.parseInt(tokens[0]);
                double longitude = Double.parseDouble(tokens[4]);
                double latitude = Double.parseDouble(tokens[5]);
                //String cap = "" + dataSnapshot.child("Capacity").getValue();
                Shelter shelter = new Shelter(id, tokens[1], tokens[2], tokens[3],
                                            longitude, latitude, tokens[7], tokens[6], tokens[8]);
                Log.d("lookhere", shelter.toString());
                shelters.add(shelter);
                Log.d("letssee", shelters.toString());
                shelterNames.add(shelter.getName());
                Log.d("working", shelterNames.toString());
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
                Log.e("Main", "error reading assets", e);
            }
        model.setShelters(shelters);
        Log.d("lookhere2", "modelShelters: " + modelShelters);

        model.flipReadData();



        FirebaseDatabase dataInstance = FirebaseDatabase.getInstance();
        DatabaseReference dataRef = dataInstance.getReference();
        DatabaseReference sheltListRef = dataRef.child("shelters");
        sheltListRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String refCapacity = ds.getValue().toString();
                    if (Integer.parseInt(ds.getKey()) < modelShelters.size()) {
                        int capacity = Integer.parseInt(refCapacity);
                        Shelter dsShelter = modelShelters.get(Integer.parseInt(ds.getKey()));
                        dsShelter.setCapacity("" + capacity);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
                    /*for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String key = (String) ds.getKey();

                        DatabaseReference keyReference = FirebaseDatabase.getInstance().
                        getReference().child("shelters").child(key);
                        keyReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                int id = Integer.parseInt(tokens[0]);
                                double longitude = Double.parseDouble(tokens[4]);
                                double latitude = Double.parseDouble(tokens[5]);
                                String cap = "" + dataSnapshot.child("Capacity").getValue();
                                Shelter shelter = new Shelter(id, tokens[1], cap, tokens[3],
                                longitude, latitude, tokens[7], tokens[6], tokens[8]);
                                Log.d("lookhere", shelter.toString());
                                shelters.add(shelter);
                                Log.d("letssee", shelters.toString());
                                Log.d("working", shelterNames.toString());
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                } catch (IOException e) {
                    Log.e("Main", "error reading assets", e);
                }
            }
             @Override
             public void onCancelled(DatabaseError databaseError) {
              }
        });
        for (int i = 0; i < model.getShelters().size(); i++) {
            shelterNames.add(model.getShelters().get(i).getName());
            Log.d("working", shelterNames.toString());
        }*/

}

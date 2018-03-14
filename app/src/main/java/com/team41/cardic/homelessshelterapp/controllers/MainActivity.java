package com.team41.cardic.homelessshelterapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner shelterSpinner;
    private EditText searchBar;
    private TextView errorView;
    Model model = Model.getInstance();
    final List<Shelter> shelters = new ArrayList<>();
    final List<String> shelterNames = new ArrayList<>();
    BufferedReader br;
    String line;
    String cur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = (EditText) findViewById(R.id.search_Bar);
        errorView = (TextView) findViewById(R.id.errorView);
        this.readShelterFile();

        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), OpeningActivity.class);
                startActivity(intent);
            }
        });

        final Button checkOutButton = findViewById(R.id.checkOut);
        checkOutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (model.getCurrentUser() instanceof HomelessPerson) {
                    if (((HomelessPerson) model.getCurrentUser()).getCheckedIn()) {
                        int newCapacity = Integer.parseInt(((HomelessPerson) model.getCurrentUser()).getCurrentShelter().getCapacity());
                        newCapacity = newCapacity + ((HomelessPerson) model.getCurrentUser()).getNumberCheckedIn();
                        ((HomelessPerson) model.getCurrentUser()).getCurrentShelter().setCapacity("" + newCapacity);
                        ((HomelessPerson) model.getCurrentUser()).setNumberCheckedIn(0);
                        ((HomelessPerson) model.getCurrentUser()).setCheckedIn(false);
                        ((HomelessPerson) model.getCurrentUser()).setCurrentShelter(null);
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
            public void onClick(View view) {
                String searchString = searchBar.getText().toString();
                Log.d("checkText", "search: " + searchString);
                Intent intent = new Intent(getBaseContext(), SearchResultsActivity.class);
                intent.putExtra("SEARCH_STRING", searchString);
                startActivity(intent);
            }

        });

        shelterSpinner = (Spinner) findViewById(R.id.shelterSpinner);

        ArrayAdapter<String> shelterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, shelterNames);
        shelterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shelterSpinner.setAdapter(shelterAdapter);

        

        Button selectButton = findViewById(R.id.selectButton);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setCurrentShelter(shelters.get(shelterNames.indexOf(cur)));
                Intent intent = new Intent(getBaseContext(), ShelterDetailsActivity.class);
                startActivity(intent);
            }
        });

    }

    public void readShelterFile() {

        DatabaseReference sheltListRef = FirebaseDatabase.getInstance().getReference().child("shelters");
        sheltListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {

                    InputStream is = getResources().openRawResource(R.raw.homeless_shelter_database);
                    br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    line = br.readLine();
                    line = br.readLine();
                    Log.d("Main", "this is line" + line);

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String key = (String) ds.getKey();
                        final String[] tokens = line.split(("#"));


                        DatabaseReference keyReference = FirebaseDatabase.getInstance().getReference().child("shelters").child(key);
                        keyReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                int id = Integer.parseInt(tokens[0]);
                                double longitude = Double.parseDouble(tokens[4]);
                                double latitude = Double.parseDouble(tokens[5]);
                                String cap = "" + dataSnapshot.child("Capacity").getValue();
                                Shelter shelter = new Shelter(id, tokens[1], cap, tokens[3], longitude, latitude, tokens[7], tokens[6], tokens[8]);
                                Log.d("lookhere", shelter.toString());
                                shelters.add(shelter);
                                Log.d("letssee", shelters.toString());
                                shelterNames.add(shelter.getName());
                                Log.d("working", shelterNames.toString());
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        line = br.readLine();
                    }
                    br.close();
                } catch (IOException e) {
                    Log.e("Main", "error reading assets", e);
                }
            }
             @Override
             public void onCancelled(DatabaseError databaseError) {
              }
        });
        model.setShelters(shelters);
        Log.d("lookhere2", model.getShelters().toString());
    }

    /**
    public void setNames() {
        for (int i = 0; i < shelters.size(); i++) {
            shelterNames.add(shelters.get(i).getName());
            Log.d("working", shelterNames.toString());
        }
    }
     */
}

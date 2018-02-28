package com.team41.cardic.homelessshelterapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.team41.cardic.homelessshelterapp.model.Model;
import com.team41.cardic.homelessshelterapp.model.Shelter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner shelterSpinner;
    Model model = Model.getInstance();
    List<Shelter> shelters = model.getShelters();
    List<String> shelterNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        readShelterFile();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), OpeningActivity.class);
                startActivity(intent);
            }
        });

        Button selectButton = findViewById(R.id.selectButton);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = shelterNames.indexOf(shelterSpinner.getSelectedItem());
                model.setCurrentShelter(shelters.get(index));
                Intent intent = new Intent(getBaseContext(), ShelterDetailsActivity.class);
                startActivity(intent);
            }
        });

        shelterSpinner = (Spinner) findViewById(R.id.shelterSpinner);
        for (int i = 0; i < shelters.size(); i++) {
            shelterNames.add(shelters.get(i).getName());
        }

        ArrayAdapter<String> shelterAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, shelterNames);
        shelterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shelterSpinner.setAdapter(shelterAdapter);
    }

    public void readShelterFile() {

        try {
            InputStream is = getResources().openRawResource(R.raw.homeless_shelter_database);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            line = br.readLine();
            line = br.readLine();
            while(line != null) {
                //Log.d("test", line);
                String[] tokens = line.split(("#"));
                
                int id = Integer.parseInt(tokens[0]);
                double longitude = Double.parseDouble(tokens[4]);
                double latitude = Double.parseDouble(tokens[5]);
                Shelter shelter = new Shelter(id, tokens[1], tokens[2], tokens[3], longitude, latitude, tokens[6], tokens[7], tokens[8]);
                Log.d("lookhere", shelter.toString());
                model.addShelter(shelter);
                line = br.readLine();
            }
            br.close();
            Log.d("lookhere", model.getShelters().toString());
        } catch (IOException e) {
            Log.e("Main", "error reading assets", e);
        }
    }
}

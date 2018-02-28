package com.team41.cardic.homelessshelterapp.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.team41.cardic.homelessshelterapp.model.Model;
import com.team41.cardic.homelessshelterapp.model.Shelter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readShelterFile();

        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), OpeningActivity.class);
                startActivity(intent);
            }
        });
    }

    public void readShelterFile() {
        Model model = Model.getInstance();

        try {
            InputStream is = getResources().openRawResource(R.raw.homeless_shelter_database);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            line = br.readLine();
            line = br.readLine();
            while(line != null) {
                Log.d("test", line);
                String[] tokens = line.split(("#"));
                int length = tokens.length;
                Log.d("test", "length: " + length);
                Log.d("test", "print: ");
                for (int i = 0; i < length; i++) {
                    Log.d("test", tokens[i]);
                }
                int id = Integer.parseInt(tokens[0]);
                //Log.d("test", "tokens[3]: " + tokens[3]);
                //Log.d("test", "tokens[4] " + tokens[4]);
                double longitude = Double.parseDouble(tokens[4]);
                Log.d("test", "longitude " + longitude);
                double latitude = Double.parseDouble(tokens[5]);
                Log.d("test", "latitude " + latitude);
                Shelter check = new Shelter(id, tokens[1], tokens[2], tokens[3], longitude, latitude, tokens[6], tokens[7], tokens[8]);
                Log.d("Main Activity", check.toString());
                line = br.readLine();
            }
            br.close();
           // Log.d("Main Activity", model.getShelters().toString());
        } catch (IOException e) {
            Log.e("Main Activity", "error reading assets", e);
        }
    }
}

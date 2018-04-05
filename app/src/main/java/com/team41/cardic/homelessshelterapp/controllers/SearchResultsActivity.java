package com.team41.cardic.homelessshelterapp.controllers;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.team41.cardic.homelessshelterapp.model.Filter;
import com.team41.cardic.homelessshelterapp.model.Shelter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class controls the searching of Shelters through either the map or the search bar
 */
public class SearchResultsActivity extends FragmentActivity implements OnMapReadyCallback {
    private final Filter searchFilter = new Filter();
    private final List<Shelter> searchResults = new ArrayList<>();
    private final List<String> shelterNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        String searchString;
        Spinner resSpinner;

        Intent lastIntent = getIntent();
        searchString = lastIntent.getStringExtra("SEARCH_STRING");
        List<Shelter> temp = new ArrayList<>();
        //search through shelter names
        searchFilter.getByName(searchString);
        temp = searchFilter.getFilteredShelters();
        searchResults.addAll(temp);
        searchFilter.clearShelterList();

        //search for female only (if requested)
        if("Female".equals(searchString) || "female".equals(searchString))  {
            searchFilter.getFemaleOnly();
            temp = searchFilter.getFilteredShelters();
            searchResults.addAll(temp);
            searchFilter.clearShelterList();
        }

        //search for male only (if requested)
        if("Male".equals(searchString) || "male".equals(searchString))  {
            searchFilter.getMaleOnly();
            temp = searchFilter.getFilteredShelters();
            searchResults.addAll(temp);
            searchFilter.clearShelterList();
        }

        //search for newborns acceptance(if requested)
        if("Newborns".equals(searchString) || "newborns".equals(searchString))  {
            searchFilter.getNewborns();
            temp = searchFilter.getFilteredShelters();
            searchResults.addAll(temp);
            searchFilter.clearShelterList();
        }

        //search for family acceptance(if requested)
        if("Children".equals(searchString) || "children".equals(searchString))  {
            searchFilter.getFamilies();
            temp = searchFilter.getFilteredShelters();
            searchResults.addAll(temp);
            searchFilter.clearShelterList();
        }

        //search for young adult acceptance(if requested)
        if("Young adult".equals(searchString) || "young adult".equals(searchString))  {
            searchFilter.getYoungAdults();
            temp = searchFilter.getFilteredShelters();
            searchResults.addAll(temp);
            searchFilter.clearShelterList();
        }

        //search for shelters that accept anyone(if requested)
        if("Anyone".equals(searchString) || "anyone".equals(searchString))  {
            searchFilter.getAnyone();
            temp = searchFilter.getFilteredShelters();
            searchResults.addAll(temp);
            searchFilter.clearShelterList();
        }


        Log.d("checkFilter", "name search: " + searchResults.toString());

        resSpinner = findViewById(R.id.resSpinner);
        for (int i = 0; i < searchResults.size(); i++) {
            Shelter currentSearchResult = searchResults.get(i);
            shelterNames.add(currentSearchResult.getName());
        }

        ArrayAdapter<String> shelterAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, shelterNames);
        shelterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resSpinner.setAdapter(shelterAdapter);

        FragmentManager supportFragManager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) supportFragManager.
                findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
            }
        });

        for (Shelter shelt: searchResults) {
            LatLng loc = new LatLng(shelt.getLatitude(), shelt.getLongitude());
            googleMap.addMarker(new MarkerOptions().position(loc).title(shelt.getName()).
                    snippet(shelt.getPhoneNumber()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }
    }
}

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
        //search through shelter names
        searchFilter.getByName(searchString);
        moveToSearchResults();

        //search for female only (if requested)
        if("Female".equals(searchString) || "female".equals(searchString))  {
            femaleOnly();
            moveToSearchResults();
        }

        //search for male only (if requested)
        if("Male".equals(searchString) || "male".equals(searchString))  {
            maleOnly();
            moveToSearchResults();
        }

        //search for newborns acceptance(if requested)
        if("Newborns".equals(searchString) || "newborns".equals(searchString))  {
            newborns();
            moveToSearchResults();
        }

        //search for family acceptance(if requested)
        if("Children".equals(searchString) || "children".equals(searchString))  {
            families();
            moveToSearchResults();
        }

        //search for young adult acceptance(if requested)
        if("Young adult".equals(searchString) || "young adult".equals(searchString))  {
            youngAdults();
            moveToSearchResults();
        }

        //search for shelters that accept anyone(if requested)
        if("Anyone".equals(searchString) || "anyone".equals(searchString))  {
            anyone();
            moveToSearchResults();
        }


        Log.d("checkFilter", "name search: " + searchResults.toString());

        onCreateHelper();

    }
    private void onCreateHelper() {
        Spinner resSpinner;
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

    /**
     * Transfers the Shelters in the filter object's list into the searchResults list and then
     *      clears the list in the filter object
     */
    public void moveToSearchResults() {
        List<Shelter> temp = searchFilter.getFilteredShelters();
        searchResults.addAll(temp);
        searchFilter.clearShelterList();
    }

    /**
     * Tells the filter to search by female only shelters
     */
    public void femaleOnly() {
        searchFilter.getFemaleOnly();
    }

    /**
     * * Tells the filter to search by male only shelters
     */
    public void maleOnly() {
        searchFilter.getMaleOnly();
    }

    /**
     * * Tells the filter to search for filters that accept newborns
     */
    public void newborns() {
        searchFilter.getNewborns();
    }

    /**
     * Tells the filter to search for filters that accept families
     */
    public void families() {
        searchFilter.getFamilies();
    }

    /**
     * Tells the filter to search for filters that accept young adults
     */
    public void youngAdults() {
        searchFilter.getYoungAdults();
    }

    /**
     * Tells the filter to search for filters that accept anyone
     */
    public void anyone() {
        searchFilter.getAnyone();
    }
}

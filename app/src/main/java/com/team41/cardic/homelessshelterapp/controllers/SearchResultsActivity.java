package com.team41.cardic.homelessshelterapp.controllers;

import android.os.health.ServiceHealthStats;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
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
    private String searchString;
    private Filter searchFilter = new Filter();
    private List<Shelter> searchResults = new ArrayList<>();
    private Spinner resSpinner;
    private List<String> shelterNames = new ArrayList<>();
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        searchString = getIntent().getStringExtra("SEARCH_STRING");
        List<Shelter> temp = new ArrayList<>();
        //search through shelter names
        searchFilter.getByName(searchString);
        temp = searchFilter.getFilteredShelters();
        for (int i = 0; i < temp.size(); i++) {
            searchResults.add(temp.get(i));
        }
        searchFilter.clearShelterList();

        //search for female only (if requested)
        if(searchString.equals("Female") || searchString.equals("female"))  {
            searchFilter.getFemaleOnly();
            temp = searchFilter.getFilteredShelters();
            for (int i = 0; i < temp.size(); i++) {
                searchResults.add(temp.get(i));
            }
            searchFilter.clearShelterList();
        }

        //search for male only (if requested)
        if(searchString.equals("Male") || searchString.equals("male"))  {
            searchFilter.getMaleOnly();
            temp = searchFilter.getFilteredShelters();
            for (int i = 0; i < temp.size(); i++) {
                searchResults.add(temp.get(i));
            }
            searchFilter.clearShelterList();
        }

        //search for newborns acceptance(if requested)
        if(searchString.equals("Newborns") || searchString.equals("newborns"))  {
            searchFilter.getNewborns();
            temp = searchFilter.getFilteredShelters();
            for (int i = 0; i < temp.size(); i++) {
                searchResults.add(temp.get(i));
            }
            searchFilter.clearShelterList();
        }

        //search for family acceptance(if requested)
        if(searchString.equals("Children") || searchString.equals("children"))  {
            searchFilter.getFamilies();
            temp = searchFilter.getFilteredShelters();
            for (int i = 0; i < temp.size(); i++) {
                searchResults.add(temp.get(i));
            }
            searchFilter.clearShelterList();
        }

        //search for young adult acceptance(if requested)
        if(searchString.equals("Young adult") || searchString.equals("young adult"))  {
            searchFilter.getYoungAdults();
            temp = searchFilter.getFilteredShelters();
            for (int i = 0; i < temp.size(); i++) {
                searchResults.add(temp.get(i));
            }
            searchFilter.clearShelterList();
        }

        //search for shelters that accept anyone(if requested)
        if(searchString.equals("Anyone") || searchString.equals("anyone"))  {
            searchFilter.getAnyone();
            temp = searchFilter.getFilteredShelters();
            for (int i = 0; i < temp.size(); i++) {
                searchResults.add(temp.get(i));
            }
            searchFilter.clearShelterList();
        }


        Log.d("checkFilter", "name search: " + searchResults.toString());

        resSpinner = (Spinner) findViewById(R.id.resSpinner);
        for (int i = 0; i < searchResults.size(); i++) {
            shelterNames.add(searchResults.get(i).getName());
        }

        ArrayAdapter<String> shelterAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, shelterNames);
        shelterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resSpinner.setAdapter(shelterAdapter);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
            }
        });

        for (Shelter shelt: searchResults) {
            LatLng loc = new LatLng(shelt.getLatitude(), shelt.getLongitude());
            mMap.addMarker(new MarkerOptions().position(loc).title(shelt.getName()).snippet(shelt.getPhoneNumber()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }
    }
}

package com.team41.cardic.homelessshelterapp.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.team41.cardic.homelessshelterapp.model.Filter;
import com.team41.cardic.homelessshelterapp.model.Shelter;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends Activity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Shelter> shelters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        final Filter shelterFilter = new Filter();

        RadioButton femaleOnlyBox = findViewById(R.id.femaleOnlyRadioButton);
        RadioButton maleOnlyBox = findViewById(R.id.maleOnlyRadioButton);
        RadioButton youngAdultsBox = findViewById(R.id.youngAdultRadioButton);
        RadioButton newbornsBox = findViewById(R.id.newbornRadioButton);
        RadioButton childrenBox = findViewById(R.id.childrenRadioButton);
        RadioButton anyoneBox = findViewById(R.id.anyoneRadioButton);

        femaleOnlyBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shelterFilter.getFemaleOnly();
            }
        });

        maleOnlyBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shelterFilter.getMaleOnly();
            }
        });

        youngAdultsBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shelterFilter.getYoungAdults();
            }
        });

        newbornsBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shelterFilter.getNewborns();
            }
        });

        childrenBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shelterFilter.getFamilies();
            }
        });

        anyoneBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shelterFilter.getAnyone();
            }
        });
        for (int i = 0; i < shelterFilter.getFilteredShelters().size(); i++) {
            shelters.set(i, shelterFilter.getFilteredShelters().get(i));
        }

        MapView map = (MapView) findViewById(R.id.mapView);
        map.getMapAsync(this);
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

        for (Shelter shelt: shelters) {
            LatLng loc = new LatLng(shelt.getLatitude(), shelt.getLongitude());
            mMap.addMarker(new MarkerOptions().position(loc).title(shelt.getName()).snippet(shelt.getPhoneNumber()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }
    }
}

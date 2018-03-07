package com.team41.cardic.homelessshelterapp.controllers;

import android.os.health.ServiceHealthStats;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.team41.cardic.homelessshelterapp.model.Filter;
import com.team41.cardic.homelessshelterapp.model.Shelter;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {
    private String searchString;
    private Filter searchFilter = new Filter();
    private List<Shelter> searchResults = new ArrayList<>();

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

    }
}

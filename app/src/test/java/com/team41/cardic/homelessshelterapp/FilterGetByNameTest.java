package com.team41.cardic.homelessshelterapp;

import android.util.Log;

import com.team41.cardic.homelessshelterapp.model.Filter;
import com.team41.cardic.homelessshelterapp.model.Model;
import com.team41.cardic.homelessshelterapp.model.Shelter;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by shott on 4/2/18.
 */

public class FilterGetByNameTest {

    private Filter filter = new Filter();

    private String name = "Shelter1";
    double longitude = 3.14;
    double latitude = 1.59;

    private String[] correctShelts = new String[2];
    int correctSheltsSize;

    Model model = Model.getInstance();


    @Before
    public void setup() {
        model.setShelters(new ArrayList<Shelter>());
        model.addShelter(new Shelter(0, name, "", "",longitude, latitude, "", "", ""));
        model.addShelter(new Shelter(1, "notThis", "", "", longitude, latitude, "", "", ""));
        model.addShelter(new Shelter(2, "no", "", "", longitude, latitude, "", "", ""));
    }

    @Test
    public void testNameEquality1() {
        correctShelts[0] = name;
        correctSheltsSize = 1;
        filter.getByName(name);
        for (int i = 0; i < filter.getFilteredShelters().size(); i++) {
            Assert.assertEquals(correctShelts[i], filter.getFilteredShelters().get(i).getName());
            Assert.assertEquals(correctSheltsSize, filter.getFilteredShelters().size());
        }
    }

    @Test
    public void testNameEquality2() {
        model.addShelter(new Shelter(3, name, "", "", longitude, latitude, "", "", ""));
        correctShelts[0] = name;
        correctShelts[1] = name;
        correctSheltsSize = 2;
        filter.getByName(name);
        for (int i = 0; i < filter.getFilteredShelters().size(); i++) {
            Assert.assertEquals(correctShelts[i], filter.getFilteredShelters().get(i).getName());
            Assert.assertEquals(correctSheltsSize, filter.getFilteredShelters().size());
        }
    }

    @Test
    public void testEmpty() {
        model.setShelters(new ArrayList<Shelter>());
        filter.getByName("name");
        Assert.assertEquals(new ArrayList<Shelter>(), filter.getFilteredShelters());
    }
}

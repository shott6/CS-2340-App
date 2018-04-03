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

    private String[] correctShelts = new String[2];
    int correctSheltsSize;

    Model model = Model.getInstance();


    @Before
    public void setup() {
        model.setShelters(new ArrayList<Shelter>());
        model.addShelter(new Shelter(0, name, "", "", 3.14, 1.59, "", "", ""));
        model.addShelter(new Shelter(1, "notThis", "", "", 2.42, 1.34, "", "", ""));
        model.addShelter(new Shelter(2, "no", "", "", 4.6434, 3.452, "", "", ""));
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
        model.addShelter(new Shelter(3, name, "", "", 2.342, 43.3, "", "", ""));
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

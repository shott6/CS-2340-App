package com.team41.cardic.homelessshelterapp;

import com.team41.cardic.homelessshelterapp.model.Filter;
import com.team41.cardic.homelessshelterapp.model.Model;
import com.team41.cardic.homelessshelterapp.model.Shelter;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * JUnit test to test the getFamilies() method in the Filter class
 *
 * Created by mmcke on 4/9/2018.
 */

public class FilterGetFamiliesTest {
    private final Filter filter = new Filter();
    private final String cond1 = "Children";
    private final String cond2 = "anyAge, Anyone";
    private final String[] correctShelters = new String[2];
    private final Model model = Model.getInstance();


    /**
     * Method that sets up the array list of shelters in model with different shelters to test
     * shelters based on the family restriction.
     */
    @Before
    public void setup() {
        filter.clearShelterList();
        model.clearShelters();
        final double LONG = 3.14;
        final double LAT = 1.59;
        model.addShelter(new Shelter(0, "", "", cond1, LONG, LAT,
                "", "", ""));
        model.addShelter(new Shelter(0, "", "", cond2, LONG, LAT,
                "", "", ""));
        String cond3 = "Men";
        model.addShelter(new Shelter(0, "", "", cond3, LONG, LAT,
                "", "", ""));
        model.addShelter(new Shelter(0, "", "", "", LONG, LAT,
                "", "", ""));
    }


    /**
     * Method to test the getFamilies() method in the filter class to make sure the filter
     * correctly adds the shelters that allow families. It also makes sure the associated list
     * is of the correct size.
     */
    @Test
    public void testFamilyRestrictionEquality() {
        correctShelters[0] = cond1;
        correctShelters[1] = cond2;
        int correctShelterSize = 2;
        filter.getFamilies();
        List<Shelter> filteredShelters = filter.getFilteredShelters();
        for (int i = 0; i < filteredShelters.size(); i++) {
            Shelter currentShelter = filteredShelters.get(i);
            Assert.assertEquals(correctShelters[i], currentShelter.getGender());
            Assert.assertEquals(correctShelterSize, filteredShelters.size());
        }
    }

    /**
     * A method to test that the getFamilies() method in Filter works when the list of shelters
     * in the model class is empty
     */
    @Test
    public void testEmpty() {
        model.clearShelters();
        filter.clearShelterList();
        filter.getFamilies();
        Assert.assertEquals(new ArrayList<Shelter>(), filter.getFilteredShelters());
    }
}



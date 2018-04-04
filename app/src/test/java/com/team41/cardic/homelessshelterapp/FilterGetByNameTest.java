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

    private final Filter filter = new Filter();

    private final String name = "Shelter1";
    private final double LONG = 3.14;
    private final double LAT = 1.59;

    private final String[] correctShelts = new String[2];
    private int correctSheltsSize;

    private final Model model = Model.getInstance();

    /**
     * Method that sets up the shelters array list in model to contain test shelters with different names
     */
    @Before
    public void setup() {
        filter.clearShelterList();
        model.clearShelters();
        model.addShelter(new Shelter(0, name, "", "", LONG, LAT, "", "", ""));
        model.addShelter(new Shelter(1, "notThis", "", "", LONG, LAT, "", "", ""));
        model.addShelter(new Shelter(2, "no", "", "", LONG, LAT, "", "", ""));
    }

    /**
     * Method that tests to make sure filter's getByName method works when there is only one shelter whose name matches
     * It also makes sure that the size of the associated array list is the correct size
     */
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

    /**
     * Method that tests to make sure filter's getByName method works when there is more than one shelter whose name matches
     * It also makes sure that the size of the associated array list is the correct size
     */
    @Test
    public void testNameEquality2() {
        model.addShelter(new Shelter(3, name, "", "", LONG, LAT, "", "", ""));
        correctShelts[0] = name;
        correctShelts[1] = name;
        correctSheltsSize = 2;
        filter.getByName(name);
        for (int i = 0; i < filter.getFilteredShelters().size(); i++) {
            Assert.assertEquals(correctShelts[i], filter.getFilteredShelters().get(i).getName());
            Assert.assertEquals(correctSheltsSize, filter.getFilteredShelters().size());
        }
    }

    /**
     * Method that makes sure filter's getByName method works when there are no shelters in the model list
     */
    @Test
    public void testEmpty() {
        model.clearShelters();
        filter.getByName("name");
        Assert.assertEquals(new ArrayList<Shelter>(), filter.getFilteredShelters());
    }
}

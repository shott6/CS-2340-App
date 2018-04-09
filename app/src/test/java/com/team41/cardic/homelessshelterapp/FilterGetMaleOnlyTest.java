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
 * Created by ddanniell3 on 2/9/18.
 */
public class FilterGetMaleOnlyTest {
    private final Filter filter = new Filter();

    private final String nameGender = "Men";
    private final String nameGender2 = "anyGender";
    private final String[] correctShelters = new String[2];

    private final Model model = Model.getInstance();

    /**
     * Method that sets up the shelters array list in model to contain test shelters with different
     * genders
     */
    @Before
    public void setup() {
        model.setShelters(new ArrayList<Shelter>());
        String name = "Shelter1";
        double LONG = 3.14;
        double LAT = 1.59;
        model.addShelter(new Shelter(0, name, "", nameGender, LONG, LAT, "", "", ""));
        model.addShelter(new Shelter(1, "a name", "Women", "", LONG, LAT, "", "", ""));
        model.addShelter(new Shelter(2, "name2", "", nameGender2, LONG, LAT, "", "", ""));
    }


    /**
     * Method that tests to make sure filter's getMaleOnly method works
     * It also makes sure that the size of the associated array list is the correct size
     */
    @Test
    public void testGenderEquality() {
        correctShelters[0] = nameGender;
        correctShelters[1] = nameGender2;
        int correctSheltersSize = 2;
        filter.getMaleOnly();
        List<Shelter> filteredShelters = filter.getFilteredShelters();
        for (int i = 0; i < filteredShelters.size(); i++) {
            Shelter currentShelter = filteredShelters.get(i);
            Assert.assertEquals(correctShelters[i], currentShelter.getGender());
            Assert.assertEquals(correctSheltersSize, filteredShelters.size());
        }
    }

    /**
     * Method that makes sure filter's getMaleOnly method works when there are no shelters in the
     * model list
     */
    @Test
    public void testEmpty() {
        model.clearShelters();
        filter.clearShelterList();
        filter.getMaleOnly();

        Assert.assertEquals(new ArrayList<Shelter>(), filter.getFilteredShelters());
    }
}

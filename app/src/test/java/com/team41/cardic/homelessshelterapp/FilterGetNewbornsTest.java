package com.team41.cardic.homelessshelterapp;

import com.team41.cardic.homelessshelterapp.model.Filter;
import com.team41.cardic.homelessshelterapp.model.Model;
import com.team41.cardic.homelessshelterapp.model.Shelter;

import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

/**
 * JUnit Test class for the .getNewborns() method within the Filter class.
 */
public class FilterGetNewbornsTest {
    private final Filter testFilter = new Filter();

    private final String restriction1 = "People, Things, Places, Newborns";
    private final String restriction4 = "Newborns uk6yrets8o97yfdtyuyhfxs";
    private final String[] newbornShelters = new String[4];
    private final Model testModel = Model.getInstance();

    /**
     * This method sets up randomized shelters with certain gender restrictions. Some of these
     * created Shelters allow Newborns whereas the rest do not.
     */
    @Before
    public void setup() {
        testFilter.clearShelterList();
        testModel.clearShelters();
        final double LONG = 44.44;
        final double LAT = 12345.12345;
        testModel.addShelter(new Shelter(0, "", "", restriction1, LONG, LAT, "", "", ""));
        String restriction2 = "ewborns";
        testModel.addShelter(new Shelter(1, "", "", restriction2, LONG, LAT, "", "", ""));
        String restriction3 = "People";
        testModel.addShelter(new Shelter(2, "", "", restriction3, LONG, LAT, "", "", ""));
        testModel.addShelter(new Shelter(3, "", "", restriction4, LONG, LAT, "", "", ""));
    }

    /**
     * This method performs the actual test to ensure that the filter method .getNewborns()
     * correctly adds the shelters that allow for Newborns while in tandem testing for the
     * correct size of filtered shelters.
     */
    @Test
    public void testNewbornRestrictionEquality() {
        newbornShelters[0] = restriction1;
        newbornShelters[1] = restriction4;
        int correctListSize = 2;
        testFilter.getNewborns();
        List<Shelter> filteredShelters = testFilter.getFilteredShelters();
        for (int i = 0; i < filteredShelters.size(); i++) {
            Shelter currentFilteredShelter = filteredShelters.get(i);
            Assert.assertEquals(newbornShelters[i], currentFilteredShelter.getGender());
            Assert.assertEquals(correctListSize, filteredShelters.size());
        }
    }

    /**
     * Method that makes sure filter's getNewborns method works when there are no shelters in the model list
     */
    @Test
    public void testEmpty() {
        testModel.clearShelters();
        testFilter.clearShelterList();
        testFilter.getNewborns();

        Assert.assertEquals(new ArrayList<Shelter>(), testFilter.getFilteredShelters());
    }
}

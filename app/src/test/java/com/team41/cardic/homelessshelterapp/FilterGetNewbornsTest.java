package com.team41.cardic.homelessshelterapp;

import com.team41.cardic.homelessshelterapp.model.Filter;
import com.team41.cardic.homelessshelterapp.model.Model;

public class FilterGetNewbornsTest {
    private final Filter testFilter = new Filter();

    private final String restriction = "People, Things, Places, Newborns";
    private final double LONG = 44.44;
    private final double LAT = 12345.12345;
    private final String[] correctShelters = new String[4];
    private int correctListSize;
    private final Model testModel = Model.getInstance();
}

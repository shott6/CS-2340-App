package com.team41.cardic.homelessshelterapp.model;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by shott on 2/6/18.
 */

public class Model {
    //Singleton instance
    private static final Model _instance = new Model();

    public static Model getInstance() {
        return _instance;
    }

    private List<Shelter> shelters;

    private Shelter currentShelter;

    private List<User> users;

    private Model() {

        users = new ArrayList<>();
        shelters = new ArrayList<>();
    }

    public boolean addUser(User user) {
        users.add(user);
        return true;
    }

    public void setCurrentShelter(Shelter shelter) {
        currentShelter = shelter;
    }

    public Shelter getCurrentShelter() {
        return currentShelter;
    }

    public void addShelter(Shelter shelter) {shelters.add(shelter);}

    public List<Shelter> getShelters() {return shelters;}


    public String getUsers() {
        return users.toString();
    }

    public List<User> getUserList(){return users;}

}

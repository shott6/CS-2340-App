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
// if you're reading this, Nyima was able to push to Git
public class Model {
    //Singleton instance
    private static final Model _instance = new Model();

    public static Model getInstance() {
        return _instance;
    }

    private List<Shelter> shelters;

    private Shelter currentShelter;

    private List<User> users;

    private User currentUser;

    private boolean readData = false;

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

    public void setCurrentUser(User user) {currentUser = user;}
    public User getCurrentUser() {return  currentUser;}

    public void addShelter(Shelter shelter) {shelters.add(shelter);}
    public void setShelters(List<Shelter> set) {shelters = set;}

    public void setReadData(boolean read) {readData = read;}
    public boolean getReadData() {return readData;}

    public List<Shelter> getShelters() {return shelters;}

    public String getUsers() {
        return users.toString();
    }

    public List<User> getUserList(){return users;}

}

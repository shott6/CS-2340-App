package com.team41.cardic.homelessshelterapp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by shott on 2/6/18.
 */
// if you're reading this, Nyima was able to push to Git  s
public final class Model {
    //Singleton instance
    private static final Model _instance = new Model();

    /**
     * gets an instance of the singleton model
     * @return returns the singleton instance
     */
    public static Model getInstance() {
        return _instance;
    }

    private final List<Shelter> shelters;

    private Shelter currentShelter;

    private User currentUser;

    private boolean readData = false;

    private Model() {

        shelters = new ArrayList<>();
    }

    /**
     * setter method for the currentShelter variable
     * @param shelter the Shelter you would like to make as currentShelter
     */
    public void setCurrentShelter(Shelter shelter) {
        currentShelter = shelter;
    }

    /**
     * getter method for the current Shelter of the Model
     * @return returns the current Shelter of the Model
     */
    public Shelter getCurrentShelter() {
        return currentShelter;
    }

    /**
     * setter method for the current User variable of the Model
     * @param user the User to set the currentUser of the Model
     */
    public void setCurrentUser(User user) {currentUser = user;}

    /**
     * getter method for the currentUser of the Model
     * @return returns the current User of the Model
     */
    public User getCurrentUser() {return  currentUser;}

    /**
     * adds a Shelter to the list of Shelters that the Model holds
     * @param shelter Shelter that will be added to the list of Shelters of Model
     */
    public void addShelter(Shelter shelter) {shelters.add(shelter);}

    /**
     * setter method for the list of Shelters that the Model holds
     * @param set list of Shelters that will become the new list of Shelters for the Model
     */
    public void setShelters(Collection<Shelter> set) {
        shelters.addAll(set);
    }

    /**
     * Method that will clear that shelters Array List
     */
    public void clearShelters() {
        shelters.clear();
    }

    /**
     * method for the readData boolean variable of the Model that
     * flips its current value to opposite value
     *
     */
    public void flipReadData() {readData = !readData;}

    /**
     * getter method for the readData variable in Model
     * @return boolean value saved in the readData variable of the Model
     */
    public boolean getReadData() {return readData;}

    /**
     * getter method for the list of Shelters of the Model
     * @return List of Shelters of the Model
     */
    public List<Shelter> getShelters() {return shelters;}

}

package com.team41.cardic.homelessshelterapp.model;

/**
 * Created by shott on 2/20/18.
 */

public class HomelessPerson extends User {
    private int currentShelter = -1;
    private int numberCheckedIn = 0;
    private boolean checkedIn = false;

    /**
     * constructor for the HomelessPerson class that takes in the person's info
     * @param _firstName String of first name of HomelessPerson(User)
     * @param _lastName String of last name of HomelessPerson(User)
     * @param _username String of username of HomelessPerson(User)
     * @param _password String of password of HomelessPerson(User)
     */
    public HomelessPerson(String _firstName, String _lastName, String _username, String _password) {
        super(_firstName, _lastName, _username, _password, false, false);
    }

    /**
     * No-arg constructor for the HomelessPerson class that generates generic info for a person
     */
    public HomelessPerson() {
        this("Generic", "User", "username", "password");
    }

    /**
     * getter method for the current Shelter of the HomelessPerson
     * @return int of the uniqueKey of the currentShelter of the HomelessPerson
     */
    public int getCurrentShelter() {return currentShelter;}

    /**
     * setter method for the currentShelter of the HomelessPerson
     * @param shelter int of the uniqueKey to set the current Shelter of the HomelessPerson
     */
    public void setCurrentShelter(int shelter) {currentShelter = shelter;}

    /**
     * getter method for the number of people this HomelessPerson has checked in
     * @return int of the number of people that this HomelessPerson has checked in
     */
    public int getNumberCheckedIn() {return numberCheckedIn;}

    /**
     * setter method for the number of people this HomelessPerson has checked in
     * @param _numCheckedIn int of the number to set for the amount of people this
     *                      HomelessPerson checked in
     */
    public void setNumberCheckedIn(int _numCheckedIn) {numberCheckedIn = _numCheckedIn;}

    /**
     * getter method for checkedIn variable of the HomelessPerson
     * @return boolean that tells whether the HomeslessPerson is currently
     *          checkedIn to another shelter
     */
    public boolean getCheckedIn() {return checkedIn;}

    /**
     * setter method for the checkedIn variable of the HomelessPerson
     * @param _checkedIn boolean to set whether the HomelessPerson is checkedIn or not
     */
    public void setCheckedIn(boolean _checkedIn) {checkedIn = _checkedIn;}
}

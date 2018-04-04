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
     * @param _firstName
     * @param _lastName
     * @param _username
     * @param _password
     */
    public HomelessPerson(String _firstName, String _lastName, String _username, String _password) {
        super(_firstName, _lastName, _username, _password, false, false);
    }
    public HomelessPerson() {
        this("Generic", "User", "username", "password");
    }

    public int getCurrentShelter() {return currentShelter;}
    public void setCurrentShelter(int shelter) {currentShelter = shelter;}

    public int getNumberCheckedIn() {return numberCheckedIn;}
    public void setNumberCheckedIn(int _numCheckedIn) {numberCheckedIn = _numCheckedIn;}

    public boolean getCheckedIn() {return checkedIn;}
    public void setCheckedIn(boolean _checkedIn) {checkedIn = _checkedIn;}
}

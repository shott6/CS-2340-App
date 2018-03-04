package com.team41.cardic.homelessshelterapp.model;

/**
 * Created by shott on 2/20/18.
 */

public class HomelessPerson extends User {
    public HomelessPerson(String _firstName, String _lastName, String _username, String _password) {
        super(_firstName, _lastName, _username, _password, false);
    }
    public HomelessPerson() {
        this("Generic", "User", "username", "password");
    }
}

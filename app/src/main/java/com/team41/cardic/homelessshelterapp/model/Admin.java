package com.team41.cardic.homelessshelterapp.model;

/**
 * Created by shott on 2/20/18.
 */

public class Admin extends User {
    public Admin(String _firstName, String _lastName, String _username, String _password) {
        super(_firstName, _lastName, _username, _password, true);
    }
    public Admin() {
        this("Generic", "User", "username", "password");
    }
}

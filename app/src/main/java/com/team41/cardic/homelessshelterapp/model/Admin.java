package com.team41.cardic.homelessshelterapp.model;

/**
 * Object that stores info relevant to an Admin user
 */

public class Admin extends User {
    /**
     * constructor that initializes all the user data for the Admin based on param info
     * @param _firstName String of first name of Admin(User)
     * @param _lastName String of last name of Admin(User)
     * @param _username String of username of Admin(User)
     * @param _password String of password of Admin(User)
     */
    public Admin(String _firstName, String _lastName, String _username, String _password, String
                 _email) {
        super(_firstName, _lastName, _username, _password, _email, true, false, 0);
    }

    /**
     * No-arg constructor for the Admin class that generates generic info for a person
     */
    public Admin() {
        this("Generic", "User", "username", "password", "");
    }
}

package com.team41.cardic.homelessshelterapp.model;

/**
 * Created by shott on 2/20/18.
 */

public abstract class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isAdmin;


    public User(String _firstName, String _lastName, String _username, String _password, boolean admin) {
        firstName = _firstName;
        lastName = _lastName;
        username = _username;
        password = _password;
        isAdmin = admin;

    }

    public User() {
        this("Generic", "User", "user", "pass", false);
    }
    /*
     * Getters and setters
    */
    public String getFirstName() {return firstName;}
    public void setFirstName(String fName) {firstName = fName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lName) {lastName = lName;}

    public String getUsername() {return  username;}
    public void setUsername(String user) {username = user;}

    public String getPassword() {return password;}
    public void setPassword(String _password) {password = _password;}

    public boolean getAdmin() {return isAdmin;}
    public void setAdmin(boolean admin) {isAdmin = admin;}


    @Override
    public String toString() {
        return firstName + " " + lastName + " username: " + username + " password: " + password;
    }

}

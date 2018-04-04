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
    private boolean accountLocked;
    //contactInfo


    /**
     *
     * @param _firstName first name of user
     * @param _lastName last name of user
     * @param _username desired username id for the user
     * @param _password desired password for the user
     * @param admin whether the user is an admin or not
     * @param _accountLocked if the user gets rejected too many times, account becomes locked
     */
    User(String _firstName, String _lastName, String _username,
         String _password, boolean admin, boolean _accountLocked) {
        firstName = _firstName;
        lastName = _lastName;
        username = _username;
        password = _password;
        isAdmin = admin;
        accountLocked = _accountLocked;

    }

    /**
     * No-Arg constructor that creates a user with name being "Generic User",
     * username: User, password: pass, not an admin, and account not locked
     */
    User() {
        this("Generic", "User", "user", "pass", false,  false);
    }
    /*
     * Getters and setters
    */

    /**
     * getter method for first name of specific user
     * @return String of the user's first name
     */
    public String getFirstName() {return firstName;}

    /**
     * setter method for the User's first name
     * @param fName sets the first name of the instance of user to the String input param
     */
    public void setFirstName(String fName) {firstName = fName;}

    /**
     * getter method for the last name of the User
     * @return String that contains the last name of the user
     */
    public String getLastName() {return lastName;}

    /**
     * setter method for the last name of the User
     * @param lName sets the last name of the User to the String input param
     */
    public void setLastName(String lName) {lastName = lName;}

    /**
     * getter method for the username id of the User
     * @return String that contains the username d of the user
     */
    public String getUsername() {return  username;}

    /**
     * setter method for the username id of the User
     * @param user sets the username id of the User to the String input param
     */
    public void setUsername(String user) {username = user;}

    /**
     * getter method for the password of the User
     * @return String that contains the password of the User
     */
    public String getPassword() {return password;}

    /**
     * setter method for the password of the User
     * @param _password sets the password of the User to the String input param
     */
    public void setPassword(String _password) {password = _password;}

    /**
     * getter method for whether or not the User is an Admin or not
     * @return boolean of whether or not the User is an Admin or not
     */
    public boolean getAdmin() {return isAdmin;}

    /**
     * setter method to whether or not the User is an Admin
     * @param admin boolean that either changes or retains whether the User is an Admin or not
     */
    public void setAdmin(boolean admin) {isAdmin = admin;}

    /**
     * getter method for whether or not the User's account is locked
     * @return boolean for whether ot not the User's account is locked or not
     */
    public boolean getAccountState() {return accountLocked;}

    /**
     * setter method for whether or not the User's account is locked or not
     * @param _accountLocked boolean that either locks or unlocks the User's account
     */
    public void setAccountState(boolean _accountLocked) {accountLocked = _accountLocked;}


    @Override
    public String toString() {
        return firstName + " " + lastName + " username: " + username + " password: " + password;
    }

}

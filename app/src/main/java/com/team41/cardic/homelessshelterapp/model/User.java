package com.team41.cardic.homelessshelterapp.model;

/**
 * Abstract class that has info and methods relevant to all types of users
 */

public abstract class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private boolean isAdmin;
    private boolean accountLocked;
    private int numAttempts;

    /**
     *
     * @param _firstName first name of user
     * @param _lastName last name of user
     * @param _username desired username id for the user
     * @param _password desired password for the user
     * @param admin whether the user is an admin or not
     * @param _accountLocked if the user gets rejected too many times, account becomes locked
     * @param _numAttempts number of unsuccessful login attempts by User
     */
    User(String _firstName, String _lastName, String _username,
         String _password, String _email, boolean admin, boolean _accountLocked, int _numAttempts) {
        firstName = _firstName;
        lastName = _lastName;
        username = _username;
        password = _password;
        email = _email;
        isAdmin = admin;
        accountLocked = _accountLocked;
        numAttempts = _numAttempts;

    }

    /**
     * No-Arg constructor that creates a user with name being "Generic User",
     * username: User, password: pass, not an admin, account not locked,
     * and number of login attempts at 0
     */
    User() {
        this("Generic", "User", "user", "pass", "", false,  false, 0);
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
    public boolean getAccountLocked() {return accountLocked;}

    /**
     * setter method for whether or not the User's account is locked or not
     * @param _accountLocked boolean that either locks or unlocks the User's account
     */
    public void setAccountLocked(boolean _accountLocked) {accountLocked = _accountLocked;}

    /**
     * getter method for the email of the user
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * getter method for number of unsuccessful login attempts a user has
     * @return int of number of login attempts
     */
    public int getNumAttempts() {return numAttempts;}

    /**
     * setter method for number of unsuccessful login attempts a user has
     * @param _numAttempts int of number of login attempts
     */
    public void setNumAttempts(int _numAttempts) {numAttempts = _numAttempts;}


    @Override
    public String toString() {
        return firstName + " " + lastName + " username: " + username + " password: " + password;
    }

}

package com.team41.cardic.homelessshelterapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shott on 2/6/18.
 */

public class Model {
    //Singleton instance
    private static final Model _instance = new Model();
    public static Model getInstance() {
        return _instance;
    }

    private List<User> users = new ArrayList<User>();

    private User currentUser;

    public boolean addUser(User user) {
        users.add(user);
        return true;
    }

    public String getUsers() {
        return users.toString();
    }

    public List<User> getUserList(){return users;}

}

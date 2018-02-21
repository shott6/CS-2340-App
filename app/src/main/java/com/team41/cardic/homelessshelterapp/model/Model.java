package com.team41.cardic.homelessshelterapp.model;

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

    private List<User> users;

    private User currentUser;

    public boolean addUser(User user) {
        users.add(user);
        return true;
    }

}

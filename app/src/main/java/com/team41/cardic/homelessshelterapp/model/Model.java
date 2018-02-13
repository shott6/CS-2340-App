package com.team41.cardic.homelessshelterapp.model;

/**
 * Created by shott on 2/6/18.
 */

public class Model {
    //Singleton instance
    private static final Model _instance = new Model();
    public static Model getInstance() {
        return _instance;
    }

}

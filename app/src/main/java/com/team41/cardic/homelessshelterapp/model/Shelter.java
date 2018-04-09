package com.team41.cardic.homelessshelterapp.model;

/**
 * Object that holds all info and methods relevant to each Shelter
 */

public class Shelter {
    private int uniqueKey;
    private String name;
    private String capacity;
    private String gender;
    private double longitude;
    private double latitude;
    private CharSequence specialNotes;
    private String address;
    private String phoneNumber;

    /**
     * constructor that takes in unique parameters to create an instance of the Shelter class
     * @param _uniqueKey int that uniquely represents each specific Shelter created
     * @param _name  String of name of Shelter
     * @param _capacity String representation of the limit the Shelter can hold
     * @param _gender String representation of the type of people allowed in the Shelter
     *                (either male/female)
     * @param _longitude double that is the longitude of the location of the Shelter
     * @param _latitude double that is the latitude of the location of the Shelter
     * @param _specialNotes CharSequence of any extra information not included
     *                      in the parameters above
     * @param _address String of the address of the Shelter
     * @param _phoneNumber String representation of the phone number of the Shelter location
     */
    public Shelter(int _uniqueKey, String _name, String _capacity, String _gender,
                   double _longitude, double _latitude, CharSequence _specialNotes,
                   String _address, String _phoneNumber) {
        uniqueKey = _uniqueKey;
        name = _name;
        capacity = _capacity;
        gender = _gender;
        longitude = _longitude;
        latitude = _latitude;
        specialNotes = _specialNotes;
        address = _address;
        phoneNumber = _phoneNumber;
    }

    /*
    Getters and setters
    -------------------
     */

    /**
     * getter method for the unique key instance variable
     * @return int that is the uniqueKey of the Shelter
     */
    public int getUniqueKey() {return uniqueKey; }

    /**
     * setter method for the UniqueKey instance variable of the Shelter class
     * @param newKey int of the newKey that you would like to set the Shelter to
     */
    public void setUniqueKey(int newKey) {uniqueKey = newKey; }

    /**
     * getter method for the name instance variable of the Shelter class
     * @return String of the name of the Shelter
     */
    public String getName() {
        return name;
    }

    /**
     * setter method for the name variable of the Shelter
     * @param nameNew String of the new name that you want to set the Shelter to
     */
    public void setName(String nameNew) {
        name = nameNew;
    }

    /**
     * getter method for the capacity variable of the Shelter
     * @return String representation of the capacity of the Shelter
     */
    public String getCapacity() {
        return capacity;
    }

    /**
     * setter method for the capacity variable of the Shelter
     * @param capacityNew String representation of the new capacity you would like
     *                    to set the Shelter to
     */
    public void setCapacity(String capacityNew) {
        capacity = capacityNew;
    }

    /**
     * setter method for the gender variable of the Shelter
     * @return String representation of the gender restrictions for the Shelter
     */
    public String getGender() {
        return gender;
    }

    /**
     * setter method for the gender variable of the Shelter class
     * @param genderNew String representation of the new gender restrictions for the Shelter
     */
    public void setGender(String genderNew) {
        gender = genderNew;
    }

    /**
     * getter method for the longitude variable of the Shelter class
     * @return double of the longitude of the location of the Shelter
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * setter method for the longitude of the Shelter
     * @param longitudeNew double of the new longitude to set for the Shelter
     */
    public void setLongitude(double longitudeNew) {
        longitude = longitudeNew;
    }

    /**
     * getter method for the latitude of the Shelter
     * @return double of the latitude of the location of the Shelter
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * setter method for the latitude of the Shelter
     * @param latitudeNew double of the new latitude to set for the Shelter
     */
    public void setLatitude(double latitudeNew) {
        latitude = latitudeNew;
    }

    /**
     * getter method for the special notes of the Shelter
     * @return CharSequence that contains the special notes of the Shelter
     */
    public CharSequence getSpecialNotes() {return specialNotes; }

    /**
     * setter method for the special notes of the Shelter
     * @param newSpecialNotes String that contains the new special notes to set for the Shelter
     */
    public void setSpecialNotes(CharSequence newSpecialNotes) {specialNotes = newSpecialNotes; }

    /**
     * getter method for the address of the Shelter
     * @return String that contains the address of the Shelter
     */
    public String getAddress() {
        return address;
    }

    /**
     * setter method for the address of the Shelter
     * @param addressNew String that contains the new address to set for the Shelter
     */
    public void setAddress(String addressNew) {
        address = addressNew;
    }

    /**
     * getter method for the phone number of the Shelter
     * @return String representation of the phone number of the Shelter
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * setter method for the phone number of the Shelter
     * @param phoneNumberNew String representation of the phone number to set for the Shelter
     */
    public void setPhoneNumber(String phoneNumberNew) {
        phoneNumber = phoneNumberNew;
    }

    @Override
    public String toString() {
        return name + " capacity: " + capacity + " address: " + address;
    }
}

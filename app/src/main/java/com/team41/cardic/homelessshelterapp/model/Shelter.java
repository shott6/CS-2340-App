package com.team41.cardic.homelessshelterapp.model;

/**
 * Created by mmcke on 2/26/2018.
 */

public class Shelter {
    private int uniqueKey;
    private String name;
    private String capacity;
    private String gender;
    private double longitude;
    private double latitude;
    private String specialNotes;
    private String address;
    private String phoneNumber;

    public Shelter(int _uniqueKey, String _name, String _capacity, String _gender, double _longitude, double _latitude, String _specialNotes, String _address, String _phoneNumber) {
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

    /**
     * Getters and setters
     */
    public int getUniqueKey() {return uniqueKey; }
    public void setUniqueKey(int newKey) {uniqueKey = newKey; }

    public String getName() {
        return name;
    }
    public void setName(String nameNew) {
        name = nameNew;
    }

    public String getCapacity() {
        return capacity;
    }
    public void setCapacity(String capacityNew) {
        capacity = capacityNew;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String genderNew) {
        gender = genderNew;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitudeNew) {
        longitude = longitudeNew;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitudeNew) {
        latitude = latitudeNew;
    }

    public String getSpecialNotes() {return specialNotes; }
    public void setSpecialNotes(String newSpecialNotes) {specialNotes = newSpecialNotes; }

    public String getAddress() {
        return address;
    }
    public void setAddress(String addressNew) {
        address = addressNew;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumberNew) {
        phoneNumber = phoneNumberNew;
    }

    public String toString() {
        return name + " capacity: " + capacity + " address: " + address;
    }
}

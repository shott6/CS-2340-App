package com.team41.cardic.homelessshelterapp.model;

/**
 * Created by mmcke on 2/26/2018.
 */

public class Shelter {
    private String name;
    private int capacity;
    private String gender;
    private double longitude;
    private double latitude;
    private String address;
    private String phoneNumber;

    public Shelter(String _name, int _capacity, String _gender, double _longitude, double _latitude, String _address, String _phoneNumber) {
        name = _name;
        capacity = _capacity;
        gender = _gender;
        longitude = _longitude;
        latitude = _latitude;
        address = _address;
        phoneNumber = _phoneNumber;
    }

    /**
     * Getters and setters
     */
    public String getName() {
        return name;
    }
    public void setName(String nameNew) {
        name = nameNew;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacityNew) {
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

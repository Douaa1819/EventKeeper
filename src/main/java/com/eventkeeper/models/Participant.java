package com.eventkeeper.models;

public class Participant extends User {
    private String phoneNumber;

    public Participant(int id, String firstName, String lastName, String email, String phoneNumber) {
        super(id, firstName, lastName, email);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Participant{" + "id=" + getId() + ", firstName='" + getFirstName() + "', lastName='" + getLastName() + "', email='" + getEmail() + "', phoneNumber='" + phoneNumber + "'}";
    }
}

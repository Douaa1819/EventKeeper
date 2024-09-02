package com.eventkeeper.models;

public class Admin extends User {

    public Admin(int id, String firstName, String lastName, String email) {
        super(id, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + getId() + ", firstName='" + getFirstName() + "', lastName='" + getLastName() + "', email='" + getEmail() + "'}";
    }
}

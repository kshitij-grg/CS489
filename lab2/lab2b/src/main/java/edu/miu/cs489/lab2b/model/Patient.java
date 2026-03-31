package edu.miu.cs489.lab2b.model;

import java.time.LocalDate;
import java.time.Period;

public class Patient {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final String email;
    private final String mailingAddress;
    private final LocalDate dateOfBirth;

    public Patient(
            int id,
            String firstName,
            String lastName,
            String phoneNumber,
            String email,
            String mailingAddress,
            LocalDate dateOfBirth
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.mailingAddress = mailingAddress;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}

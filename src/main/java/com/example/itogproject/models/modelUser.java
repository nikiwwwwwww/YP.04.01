package com.example.itogproject.models;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class modelUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Field cannot be empty")
    private String name;
    @NotEmpty(message = "Field cannot be empty")
    private String lastName;
    @NotEmpty(message = "Field cannot be empty")
    private String middleName;
    @NotEmpty(message = "Field cannot be empty")
    private String dateOfEmployment;
    @NotEmpty(message = "Field cannot be empty")
    private String username;
    @NotEmpty(message = "Field cannot be empty")
    private String password;
    @NotEmpty(message = "Field cannot be empty")
    private String phoneNumber;

    public modelUser(Long id, String name, String lastName, String middleName, String dateOfEmployment, String username, String password, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dateOfEmployment = dateOfEmployment;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public modelUser() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(String dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}

package com.example.itogproject.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class editEmployeClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_employe;
    private String name;
    private String lastName;
    private String middleName;
    private String dateOfEmployment;
    private String username;
    private String password;
    private String phoneNumber;

    public editEmployeClass(Long id_employe, String name, String lastName, String middleName, String dateOfEmployment, String username, String password, String phoneNumber) {
        this.id_employe = id_employe;
        this.name = name;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dateOfEmployment = dateOfEmployment;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public editEmployeClass() {    }
    public Long getId() {
        return id_employe;
    }

    public void setId(Long id_employe) {
        this.id_employe = id_employe;
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

package com.example.itogproject.models;



import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_employe;
    @NotEmpty(message = "Field cannot be empty")
    private String name;
    @NotEmpty(message = "Field cannot be empty")
    private String lastName;
    @NotEmpty(message = "Field cannot be empty")
    private String middleName;
    private Date dateOfEmployment;
    @Size(min = 6, message = "Field must be at least 6 characters long")
    private String username;
    @NotEmpty(message = "Field cannot be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;
    @NotEmpty(message = "Field cannot be empty")
    @Pattern(regexp = "^\\+7\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$", message = "Phone number must match the pattern +7(123)456-78-90")
    private String phoneNumber;

    public Employe(Long id_employe, String name, String lastName, String middleName, Date dateOfEmployment, String username, String password, String phoneNumber) {
        this.id_employe = id_employe;
        this.name = name;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dateOfEmployment = dateOfEmployment;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public Employe() {

    }

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

    public Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(Date dateOfEmployment) {
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

package com.example.itogproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Stake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Stake;
    @NotBlank
    private int countDaysWorked;
    @NotEmpty(message = "Field cannot be empty")
    private float paymentPerDay;
    private Date dateOfSalary;
    @NotEmpty(message = "Field cannot be empty")
    private float paymentPerShift;

    public Stake(Long ID_Stake, int countDaysWorked, float paymentPerDay, Date dateOfSalary, float paymentPerShift) {
        this.ID_Stake = ID_Stake;
        this.countDaysWorked = countDaysWorked;
        this.paymentPerDay = paymentPerDay;
        this.dateOfSalary = dateOfSalary;
        this.paymentPerShift = paymentPerShift;
    }

    public Stake() {

    }

    public Long getId() {
        return ID_Stake;
    }

    public void setId(Long ID_Stake) {
        this.ID_Stake = ID_Stake;
    }

    public int getCountDaysWorked() {
        return countDaysWorked;
    }

    public void setCountDaysWorked(int countDaysWorked) {
        this.countDaysWorked = countDaysWorked;
    }

    public float getPaymentPerDay() {
        return paymentPerDay;
    }

    public void setPaymentPerDay(float paymentPerDay) {
        this.paymentPerDay = paymentPerDay;
    }

    public Date getDateOfSalary() {
        return dateOfSalary;
    }

    public void setDateOfSalary(Date dateOfSalary) {
        this.dateOfSalary = dateOfSalary;
    }

    public float getPaymentPerShift() {
        return paymentPerShift;
    }

    public void setPaymentPerShift(float paymentPerShift) {
        this.paymentPerShift = paymentPerShift;
    }

}


package com.example.itogproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Education;
    @NotEmpty(message = "Field cannot be empty")
    private String typeOfEducation;
    @NotEmpty(message = "Field cannot be empty")
    private String specialization;
    @NotEmpty(message = "Field cannot be empty")
    private String institution;

    public Education(Long ID_Education, String typeOfEducation, String specialization, String institution) {
        this.ID_Education = ID_Education;
        this.typeOfEducation = typeOfEducation;
        this.specialization = specialization;
        this.institution = institution;
    }

    public Education() {

    }

    public Long getId() {
        return ID_Education;
    }

    public void setId(Long ID_Education) {
        this.ID_Education = ID_Education;
    }

    public String getTypeOfEducation() {
        return typeOfEducation;
    }

    public void setTypeOfEducation(String typeOfEducation) {
        this.typeOfEducation = typeOfEducation;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

}

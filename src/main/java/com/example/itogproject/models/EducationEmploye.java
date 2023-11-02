package com.example.itogproject.models;

import javax.persistence.*;

@Entity
public class EducationEmploye {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Education_employe;

    @ManyToOne
    @JoinColumn(name = "education_id")
    private Education education;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;

    public EducationEmploye(Long ID_Education_employe, Education education, Employe employe) {
        this.ID_Education_employe = ID_Education_employe;
        this.education = education;
        this.employe = employe;
    }

    public EducationEmploye() {

    }

    public Long getId() {
        return ID_Education_employe;
    }

    public void setId(Long ID_Education_employe) {
        this.ID_Education_employe = ID_Education_employe;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

}
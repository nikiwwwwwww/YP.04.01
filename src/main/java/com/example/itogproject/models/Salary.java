package com.example.itogproject.models;

import javax.persistence.*;

@Entity
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Sallary;

    @ManyToOne
    @JoinColumn(name = "post_employe_id")
    private PostEmploye postEmploye;

    @ManyToOne
    @JoinColumn(name = "stake_id")
    private Stake stake;

    public Salary(Long ID_Sallary, PostEmploye postEmploye, Stake stake) {
        this.ID_Sallary = ID_Sallary;
        this.postEmploye = postEmploye;
        this.stake = stake;
    }

    public Salary() {

    }

    public Long getId() {
        return ID_Sallary;
    }

    public void setId(Long ID_Sallary) {
        this.ID_Sallary = ID_Sallary;
    }

    public PostEmploye getPostEmploye() {
        return postEmploye;
    }

    public void setPostEmploye(PostEmploye postEmploye) {
        this.postEmploye = postEmploye;
    }


    public Stake getStake() {
        return stake;
    }

    public void setStake(Stake stake) {
        this.stake = stake;
    }
}

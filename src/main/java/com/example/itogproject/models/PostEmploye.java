package com.example.itogproject.models;

import javax.persistence.*;

@Entity
public class PostEmploye {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_post_employe;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;

    public PostEmploye(Long id_post_employe, Post post, Employe employe) {
        this.id_post_employe = id_post_employe;
        this.post = post;
        this.employe = employe;
    }

    public PostEmploye() {

    }

    public Long getId() {
        return id_post_employe;
    }

    public void setId(Long id_post_employe) {
        this.id_post_employe = id_post_employe;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

}

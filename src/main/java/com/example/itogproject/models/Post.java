package com.example.itogproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_post;
    @NotEmpty(message = "Field cannot be empty")
    @Column(unique = true)
    private String namePost;


    public Post(Long id_post, String namePost) {
        this.id_post = id_post;
        this.namePost = namePost;
    }

    public Post() {

    }

    public Long getId() {
        return id_post;
    }

    public void setId(Long id_post) {
        this.id_post = id_post;
    }

    public String getNamePost() {
        return namePost;
    }

    public void setNamePost(String namePost) {
        this.namePost = namePost;
    }


}

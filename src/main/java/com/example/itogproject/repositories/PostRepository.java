package com.example.itogproject.repositories;

import com.example.itogproject.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByNamePost(String name);

    List<Post> findAllByNamePost(String name);
}

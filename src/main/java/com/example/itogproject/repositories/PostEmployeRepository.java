package com.example.itogproject.repositories;

import com.example.itogproject.models.PostEmploye;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostEmployeRepository extends JpaRepository<PostEmploye, Long> {

    List<PostEmploye> findAllByEmployeUsername(String name);

}

package com.example.itogproject.repositories;

import com.example.itogproject.models.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
    Employe findByUsername(String username);

    List<Employe> findAllByUsername(String name);
}

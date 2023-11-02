package com.example.itogproject.repositories;

import com.example.itogproject.models.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SallaryRepository extends JpaRepository<Salary, Long> {
    List<Salary> findAllByPostEmployeEmployeUsername(String name);
}

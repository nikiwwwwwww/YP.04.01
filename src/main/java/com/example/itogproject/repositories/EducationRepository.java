package com.example.itogproject.repositories;

import com.example.itogproject.models.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findAllBySpecialization(String name);

}

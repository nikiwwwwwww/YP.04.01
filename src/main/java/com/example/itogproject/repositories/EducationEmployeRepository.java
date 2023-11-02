package com.example.itogproject.repositories;

import com.example.itogproject.models.EducationEmploye;
import com.example.itogproject.models.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationEmployeRepository extends JpaRepository<EducationEmploye, Long> {

    List<EducationEmploye> findAllByEmploye_Username(String name);

}

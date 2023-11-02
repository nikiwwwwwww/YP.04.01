package com.example.itogproject.repositories;

import com.example.itogproject.models.Education;
import com.example.itogproject.models.EmployeTasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeTasksRepository extends JpaRepository<EmployeTasks, Long> {
    List<EmployeTasks> findAllByEmployeUsername(String name);

}

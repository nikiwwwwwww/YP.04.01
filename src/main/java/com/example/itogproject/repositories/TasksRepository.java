package com.example.itogproject.repositories;

import com.example.itogproject.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<Tasks, Long> {

    List<Tasks> findAllByTopic(String name);

}

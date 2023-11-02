package com.example.itogproject.repositories;

import com.example.itogproject.models.Salary;
import com.example.itogproject.models.Stake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StakeRepository extends JpaRepository<Stake, Long> {

    List<Stake> findAllByCountDaysWorked(int count);

}

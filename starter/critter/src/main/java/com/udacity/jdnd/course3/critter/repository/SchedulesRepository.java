package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchedulesRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByPetId(Long petId);
    List<Schedule> findByPetIdIn(List<Long> petIds);

    List<Schedule> findByEmployeeId(Long employeeId);

}

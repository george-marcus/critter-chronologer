package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeesRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeesService {

    @Autowired
    private EmployeesRepository employeesRepository;

    public Employee getEmployeeById(long employeeId) {
        return employeesRepository.getOne(employeeId);
    }

    public List<Employee> getEmployeesForService(LocalDate date, Set<EmployeeSkill> skills) {

        List<Employee> employees = employeesRepository
                .findByDaysAvailable(date.getDayOfWeek());

        return employees.stream()
                            .filter(employee -> employee.getSkills().containsAll(skills))
                            .collect(Collectors.toList());
    }

    public Employee saveEmployee(Employee employee) {
        return employeesRepository.save(employee);
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {

        Employee employee = employeesRepository.getOne(employeeId);

        employee.setDaysAvailable(daysAvailable);
        employeesRepository.save(employee);
    }
}

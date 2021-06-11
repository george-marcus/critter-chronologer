package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.SchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private SchedulesService schedulesService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {

        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());

        schedule = schedulesService
                .saveSchedule(schedule, scheduleDTO.getEmployeeIds(), scheduleDTO.getPetIds());

        return ScheduleDTO.getScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {

        List<Schedule> schedules = schedulesService.getAllSchedules();
        return schedules.stream()
                .map(ScheduleDTO::getScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {

        List<Schedule> schedules = schedulesService.getAllSchedulesForPet(petId);
        return schedules.stream()
                .map(ScheduleDTO::getScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {

        List<Schedule> schedules = schedulesService.getAllSchedulesForEmployee(employeeId);
        return schedules.stream()
                .map(ScheduleDTO::getScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {

        List<Schedule> schedules = schedulesService.getAllSchedulesForCustomer(customerId);
        return schedules.stream()
                .map(ScheduleDTO::getScheduleDTO).collect(Collectors.toList());
    }


}

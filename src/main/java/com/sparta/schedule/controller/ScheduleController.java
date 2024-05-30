package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.service.ScheduleService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){
        return scheduleService.createSchedule(requestDto);
    }

    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getSchedule(Long id){
        return scheduleService.getSchedule(id);
    }

    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getAllSchedule(){
        return scheduleService.getAllSchedule();
    }

    @PutMapping("/schedule")
    public Long updateSchedule(@RequestParam Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(id, requestDto);
    }

    @DeleteMapping("/schedule")
    public Long deleteSchedule(@RequestParam Long id, @RequestBody ScheduleRequestDto requestDto){
        return scheduleService.deleteSchedule(id, requestDto);
    }
}

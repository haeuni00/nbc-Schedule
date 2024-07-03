package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.security.UserDetailsImpl;
import com.sparta.schedule.service.ScheduleService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl authentication){
        return scheduleService.createSchedule(requestDto, authentication);
    }

    @GetMapping("/{id}")
    public List<ScheduleResponseDto> getSchedule(@PathVariable Long id){
        return scheduleService.getSchedule(id);
    }

    @GetMapping
    public List<ScheduleResponseDto> getAllSchedule(){
        return scheduleService.getAllSchedule();
    }

    @PutMapping("/{id}")
    public Long updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl authentication) {
        return scheduleService.updateSchedule(id, requestDto, authentication);
    }

    @DeleteMapping("/{id}")
    public Long deleteSchedule(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl authentication){
        return scheduleService.deleteSchedule(id, authentication);
    }
}

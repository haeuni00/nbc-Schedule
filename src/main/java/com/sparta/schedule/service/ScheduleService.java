package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class ScheduleService {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);

        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        Schedule saveSchedule = scheduleRepository.save(schedule);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    public List<ScheduleResponseDto> getSchedule(Long id){

        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.findList(id);
    }

    public List<ScheduleResponseDto> getAllSchedule(){
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);
        return scheduleRepository.findAll();
    }

    public Long updateSchedule(Long id, ScheduleRequestDto requestDto) {
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);

        Schedule schedule = scheduleRepository.findById(id);
        if(schedule != null) {
            if(!schedule.getPassword().equals(requestDto.getPassword())){
                throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            } else {
                scheduleRepository.update(id, requestDto);
                return id;
            }
        } else {
            throw new IllegalArgumentException("일정이 존재하지 않습니다.");
        }
    }

    public Long deleteSchedule(Long id, ScheduleRequestDto requestDto){
        ScheduleRepository scheduleRepository = new ScheduleRepository(jdbcTemplate);

        Schedule schedule = scheduleRepository.findById(id);
        if(schedule != null) {
            if(!schedule.getPassword().equals(requestDto.getPassword())){
                throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }else {
                scheduleRepository.delete(id);
                return id;
            }
        } else {
            throw new IllegalArgumentException("일정이 존재하지 않습니다.");
        }
    }
}

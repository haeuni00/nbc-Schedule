package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
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
    private final JdbcTemplate jdbcTemplate;
    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){
        Schedule schedule = new Schedule(requestDto);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO schedule (title, content, manager, password, date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getTitle());
                    preparedStatement.setString(2, schedule.getContent());
                    preparedStatement.setString(3, schedule.getManager());
                    preparedStatement.setString(4, schedule.getPassword());
                    preparedStatement.setString(5, schedule.getDate());
                    return preparedStatement;
                },
                keyHolder);

        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getSchedule(Long id){
        String sql = "SELECT * FROM schedule WHERE id = ?";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String manager = rs.getString("manager");
                String date = rs.getString("date");
                return new ScheduleResponseDto(id, title, content, manager, date);
            }
        }, id);
    }

    @GetMapping("/schedule/All")
    public List<ScheduleResponseDto> getAllSchedule(){
        String sql = "SELECT * FROM schedule order by date DESC";

        return jdbcTemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String manager = rs.getString("manager");
                String date = rs.getString("date");
                return new ScheduleResponseDto(id, title, content, manager, date);
            }
        });
    }

    @PutMapping("/schedule")
    public Long updateSchedule(@RequestParam Long id, @RequestParam String password, @RequestBody ScheduleRequestDto requestDto) {
        Schedule schedule = findIdPwd(id, password);
        if(schedule != null) {
            String sql = "UPDATE schedule SET title = ?, content = ?, manager = ? WHERE id = ? AND password = ?";
            jdbcTemplate.update(sql, requestDto.getTitle(), requestDto.getContent(), requestDto.getManager(), id, password);

            return id;
        } else {
            throw new IllegalArgumentException("일정이 존재하지 않습니다.");
        }
    }

    private Schedule findIdPwd(Long id, String password) {
        String sql = "SELECT * FROM schedule WHERE id = ? AND password = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setTitle(resultSet.getString("title"));
                schedule.setContent(resultSet.getString("content"));
                schedule.setManager(resultSet.getString("manager"));
                return schedule;
            } else {
                return null;
            }
        }, id, password);
    }
}

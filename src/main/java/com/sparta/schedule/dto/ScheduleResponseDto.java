package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private String manager;
    private Timestamp date;

    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.manager = schedule.getManager();
        this.date = schedule.getDate();
    }

    public ScheduleResponseDto(Long id, String title, String content, String manager, Timestamp date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.manager = manager;
        this.date = date;
    }
}


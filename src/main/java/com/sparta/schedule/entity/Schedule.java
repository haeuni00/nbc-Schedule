package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class Schedule {

    private long id;
    private String title;
    private String content;
    private String manager;
    private String password;
    private Timestamp date;

    public Schedule(ScheduleRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.manager = requestDto.getManager();
        this.password = requestDto.getPassword();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

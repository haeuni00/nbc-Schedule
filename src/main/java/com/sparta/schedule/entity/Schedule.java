package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private long id;
    private String title;
    private String content;
    private String manager;
    private String password;
    private String date;

    public Schedule(ScheduleRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.manager = requestDto.getManager();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
    }
}

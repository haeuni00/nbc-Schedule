package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class CommentRequestDto {
    private String comment;
    private String userid;
    private Schedule schedule;
    private Timestamp date;
}

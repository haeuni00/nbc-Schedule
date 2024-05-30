package com.sparta.schedule.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class CommentRequestDto {
    private String comment;
    private String userid;
    private Timestamp date;
}

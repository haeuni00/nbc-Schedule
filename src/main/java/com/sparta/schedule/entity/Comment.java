package com.sparta.schedule.entity;

import com.sparta.schedule.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentid;
    private String comment;
    private String userid;
    private Timestamp date;

    public Comment(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
        this.userid = requestDto.getUserid();
        this.date = requestDto.getDate();
    }
}

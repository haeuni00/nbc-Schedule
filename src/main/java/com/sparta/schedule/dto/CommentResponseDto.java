package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Comment;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class CommentResponseDto {
    private Long commentid;
    private String comment;
    private String userid;
    private Timestamp date;

    public CommentResponseDto(Comment comment) {
        this.commentid = comment.getCommentid();
        this.comment = comment.getComment();
        this.userid = comment.getUserid();
        this.date = comment.getDate();
    }

    public CommentResponseDto(Long commentid, String comment, String userid, Timestamp date) {
        this.commentid = commentid;
        this.comment = comment;
        this.userid = userid;
        this.date = date;
    }
}

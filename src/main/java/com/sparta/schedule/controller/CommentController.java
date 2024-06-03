package com.sparta.schedule.controller;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.service.CommentService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.Statement;

@RestController
@RequestMapping("/api/schedule")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(requestDto);
    }

    @PutMapping("/comment")
    public Long updateComment(@RequestParam Long commentid, @RequestBody CommentRequestDto requestDto){
        return commentService.updateComment(commentid, requestDto);
    }

    @DeleteMapping("/comment")
    public String deleteComment(@RequestParam Long commentid, @RequestBody CommentRequestDto requestDto){
        return commentService.deleteComment(commentid, requestDto);
    }
}

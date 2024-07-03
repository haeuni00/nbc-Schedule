package com.sparta.schedule.controller;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.security.UserDetailsImpl;
import com.sparta.schedule.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.Statement;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    public CommentResponseDto createComment(@Valid @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl authentication) {
        return commentService.createComment(requestDto,authentication);
    }

    @PutMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @Valid @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl authentication){
        return commentService.updateComment(commentId, requestDto, authentication);
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl authentication){
        return commentService.deleteComment(commentId, authentication);
    }
}

package com.sparta.schedule.service;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
public class CommentService {

    private final ScheduleService scheduleService;
    private final CommentRepository commentRepository;
    public CommentService(ScheduleService scheduleService, CommentRepository commentRepository){
        this.scheduleService = scheduleService;
        this.commentRepository = commentRepository;
    }

    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto) {
        Comment comment = new Comment(requestDto);
        if (comment.getComment() == null){
            throw  new IllegalArgumentException("내용을 입력해주세요.");
        }
        Schedule schedule = scheduleService.findSchedule(comment.getSchedule().getId());
        Comment saveComment = commentRepository.save(comment);

        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);

        return commentResponseDto;

    }

    public Long updateComment(Long commentid, CommentRequestDto requestDto){

        Comment comment = findComment(commentid);
        comment.update(commentid, requestDto);
        return commentid;
    }

    public String deleteComment(Long commentid, CommentRequestDto requestDto) {

        Comment comment = findComment(commentid);
        commentRepository.delete(comment);
        return "삭제되었습니다.";
    }

    private Comment findComment(Long commentid){
        return commentRepository.findById(commentid).orElseThrow(() ->
                new IllegalArgumentException("댓글이 존재하지 않습니다."));
    }
}

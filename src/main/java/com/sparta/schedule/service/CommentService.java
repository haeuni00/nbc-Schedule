package com.sparta.schedule.service;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto) {
        Comment comment = new Comment(requestDto);
        if(comment.getScheduleid() == null){
            throw  new IllegalArgumentException("일정을 선택해주세요.");
        } else if (comment.getComment() == null){
            throw  new IllegalArgumentException("내용을 입력해주세요.");
        }
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

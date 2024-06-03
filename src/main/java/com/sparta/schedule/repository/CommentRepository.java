package com.sparta.schedule.repository;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//    private final JdbcTemplate jdbcTemplate;
//
//    public CommentRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public Comment save(Long id, Comment comment) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        String sql = "INSERT INTO comment (comment, userid, scheduleid, date) VALUES (?, ?, ?, ?)";
//        jdbcTemplate.update( con -> {
//                    PreparedStatement preparedStatement = con.prepareStatement(sql,
//                            Statement.RETURN_GENERATED_KEYS);
//
//                    preparedStatement.setString(1, comment.getComment());
//                    preparedStatement.setString(2, comment.getUserid());
//                    preparedStatement.setLong(3, id);
//                    preparedStatement.setTimestamp(4, comment.getDate());
//                    return preparedStatement;
//                },
//                keyHolder);
//
//        Long commentid = keyHolder.getKey().longValue();
//        comment.setCommentid(commentid);
//
//        return comment;
//    }
}

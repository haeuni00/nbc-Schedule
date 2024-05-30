package com.sparta.schedule.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentid;
    private String comment;
    private String userid;
    private Timestamp date;
}

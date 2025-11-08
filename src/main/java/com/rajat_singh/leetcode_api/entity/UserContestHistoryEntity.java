package com.rajat_singh.leetcode_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "contest_history")
public class UserContestHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String title;
    private boolean attended;
    private String trendDirection;
    private int problemsSolved;
    private int totalProblems;
    private long finishTimeInSeconds;
    private double rating;
    private int ranking;
    private long startTime;

    private LocalDateTime lastUpdated;

}

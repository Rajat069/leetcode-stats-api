package com.rajat_singh.leetcode_api.dto;

import lombok.Data;

@Data
public class UserContestRankingHistory {
    private boolean attended;
    private String trendDirection;
    private int problemsSolved;
    private int totalProblems;
    private int finishTimeInSeconds;
    private double rating;
    private int ranking;
    private Contest contest;


    @Data
    public static class Contest {
        private String title;
        private long startTime;
    }
}

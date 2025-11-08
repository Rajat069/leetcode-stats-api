package com.rajat_singh.leetcode_api.dto;

import lombok.Data;

@Data
public class UserContestBiggestRatingJump {

    private double ratingJump;
    private double previousRating;
    private double newRating;
    private UserContestRankingHistory userContestRankingHistory;
}

package com.rajat_singh.leetcode_api.dto;

import lombok.Data;

import java.util.List;


@Data
public class UserContestResponse {

    private DataNode data;

    @Data
    public static class DataNode{
        private UserContestRanking userContestRanking;
        private List<UserContestRankingHistory> userContestRankingHistory;
    }
}

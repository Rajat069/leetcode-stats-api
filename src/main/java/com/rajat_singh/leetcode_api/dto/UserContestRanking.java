package com.rajat_singh.leetcode_api.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;


@JsonIgnoreProperties
@Data
public class UserContestRanking {
        private int attendedContestsCount;
        private double rating;
        private int globalRanking;
        private int totalParticipants;
        private double topPercentage;
        private Badge badge;

        @Data
        private static class Badge{
            @JsonProperty("name")
            String badgeName;
        }
}

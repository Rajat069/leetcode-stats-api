package com.rajat_singh.leetcode_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRecentSubmissionsResponse {

    private DataNode data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataNode {
        private List<Submission> recentAcSubmissionList;
    }

    /**
     * Represents a single recent AC submission.
     */
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Submission {
        private String id;
        private String title;
        private String titleSlug;
        private String timestamp; // Kept as String, can be mapped to Instant or Long
        private String lang;
        private String time;
        private String url;
        private String statusDisplay;
    }
}
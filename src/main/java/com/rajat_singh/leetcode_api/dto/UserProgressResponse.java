package com.rajat_singh.leetcode_api.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserProgressResponse {
    private DataNode data;

    @lombok.Data
    public static class DataNode {
        private UserProfileUserQuestionProgressV2 userProfileUserQuestionProgressV2;
    }

    @lombok.Data
    public static class UserProfileUserQuestionProgressV2 {
        private List<QuestionCount> numAcceptedQuestions;
        private List<QuestionCount> numFailedQuestions;
        private List<QuestionCount> numUntouchedQuestions;
        private List<UserSessionBeatsPercentage> userSessionBeatsPercentage;
        private Double totalQuestionBeatsPercentage;
    }

    @lombok.Data
    public static class QuestionCount {
        private int count;
        private String difficulty;
    }

    @lombok.Data
    public static class UserSessionBeatsPercentage {
        private String difficulty;
        private Double percentage;
    }
}

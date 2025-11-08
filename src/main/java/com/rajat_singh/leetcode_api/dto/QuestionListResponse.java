package com.rajat_singh.leetcode_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

import static com.rajat_singh.leetcode_api.constants.Constants.PROBLEM_URL_PREFIX;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionListResponse {

    private DataNode data;

    @Data
    public static class DataNode{
        public QuestionList problemsetQuestionListV2;
    }

    @Data
    public static class QuestionList{
        public ArrayList<Question> questions;
        public int totalLength;
    }

    @Data
    public static class Question{
        String difficulty;
        int id;
        Boolean paidOnly;
        String title;
        String titleSlug;
        ArrayList<TopicTag> topicTags;
        Double acRate;
        public String getProblemUrl() {
            if (this.titleSlug == null || this.titleSlug.isEmpty()) {
                return null;
            }
            return PROBLEM_URL_PREFIX + this.titleSlug + "/";
        }
    }

    @Data
    public static class TopicTag{
        String name;
        String slug;
    }

}

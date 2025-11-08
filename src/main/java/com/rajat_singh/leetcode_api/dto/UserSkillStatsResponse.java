package com.rajat_singh.leetcode_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSkillStatsResponse {

    private DataNode data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataNode {
        private MatchedUser matchedUser;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MatchedUser {
        private TagProblemCounts tagProblemCounts;
    }

    /**
     * This class holds the lists for each difficulty category.
     */
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TagProblemCounts {
        private List<TagProblem> advanced;
        private List<TagProblem> intermediate;
        private List<TagProblem> fundamental;
    }

    /**
     * This class represents a single tag's problem count.
     */
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TagProblem {
        private String tagName;
        private String tagSlug;
        private int problemsSolved;
    }
}
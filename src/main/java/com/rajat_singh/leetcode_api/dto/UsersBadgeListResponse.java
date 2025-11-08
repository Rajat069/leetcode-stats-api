package com.rajat_singh.leetcode_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersBadgeListResponse {

    private DataNode data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataNode {
        private MatchedUser matchedUser;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MatchedUser {
        private List<Badge> badges;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Badge {
        private String id;
        private String name;
        private String shortName;
        private String displayName;
        private String icon;
        private String hoverText;
        private Medal medal;
        private String creationDate; // Kept as String for simplicity, can be LocalDate
        private String category;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Medal {
        private String slug;
        private MedalConfig config;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MedalConfig {
        private String iconGif;
        private String iconGifBackground;
    }
}
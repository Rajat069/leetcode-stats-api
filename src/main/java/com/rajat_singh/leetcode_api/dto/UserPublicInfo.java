package com.rajat_singh.leetcode_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.util.ArrayList;

@Data
@JsonPropertyOrder({ "data" })
public class UserPublicInfo {

    private DataNode data;

    @Data
    @JsonPropertyOrder({ "matchedUser" })
    public static class DataNode {
        private MatchedUser matchedUser;
    }

    @Data
    @JsonPropertyOrder({
            "contestBadge",
            "userName",
            "githubUrl",
            "twitterUrl",
            "linkedinUrl",
            "profile"
    })
    public static class MatchedUser {
        private ContestBadge contestBadge;

        @JsonProperty("username")
        private String userName;
        private String githubUrl;
        private String twitterUrl;
        private String linkedinUrl;
        private Profile profile;
    }

    @Data
    @JsonPropertyOrder({ "badgeName", "expired", "badgeDetails", "icon" })
    public static class ContestBadge {
        @JsonProperty("name")
        private String badgeName;

        @JsonProperty("expired")
        private boolean expired;

        @JsonProperty("hoverText")
        private String badgeDetails;

        private String icon;
    }

    @Data
    @JsonPropertyOrder({
            "ranking",
            "userAvatar",
            "userRealName",
            "aboutMe",
            "schoolName",
            "websites",
            "countryName",
            "companyName",
            "jobTitle",
            "skillTags",
            "postViewCount",
            "postViewCountDiff",
            "reputationStars",
            "reputationDiff",
            "solutionCount",
            "solutionCountDiff",
            "categoryDiscussCount",
            "categoryDiscussCountDiff",
            "certificationLevel"
    })
    public static class Profile {
        private long ranking;
        private String userAvatar;

        @JsonProperty("realName")
        private String userRealName;

        private String aboutMe;

        @JsonProperty("school")
        private String schoolName;

        private ArrayList<String> websites;
        private String countryName;

        @JsonProperty("company")
        private String companyName;

        private String jobTitle;
        private ArrayList<String> skillTags;
        private long postViewCount;
        private long postViewCountDiff;

        @JsonProperty("reputation")
        private long reputationStars;

        private long reputationDiff;
        private long solutionCount;
        private long solutionCountDiff;
        private long categoryDiscussCount;
        private long categoryDiscussCountDiff;
        private String certificationLevel;
    }
}

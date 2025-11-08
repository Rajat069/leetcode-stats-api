package com.rajat_singh.leetcode_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLeetCodeCalendarResponse {

    private DataNode data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataNode {
        private MatchedUser matchedUser;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MatchedUser {
        private UserCalendar userCalendar;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserCalendar {
        private List<Integer> activeYears;
        private int streak;
        private int totalActiveDays;
        private List<DccBadge> dccBadges;
        
        /**
         * NOTE: This field comes from the API as a JSON-escaped STRING,
         * not a nested object. We need to parse this string
         * in your service layer to get the Map<String, Integer>.
         */
        private String submissionCalendar;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DccBadge {
        private long timestamp;
        private Badge badge;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Badge {
        private String name;
        private String icon;
    }
}
package com.rajat_singh.leetcode_api.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class UserCalendarDTO {
    private List<Integer> activeYears;
    private int streak;
    private int totalActiveDays;
    private Map<String, Integer> submissionCalendar;
    private List<UserLeetCodeCalendarResponse.DccBadge> dccBadges;
}
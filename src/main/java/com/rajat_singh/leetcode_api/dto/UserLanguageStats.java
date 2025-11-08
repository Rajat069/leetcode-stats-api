package com.rajat_singh.leetcode_api.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserLanguageStats {

    private DataNode data;

    @lombok.Data
    public static class DataNode {
        private MatchedUser matchedUser;
    }

    @lombok.Data
    public static class MatchedUser{
        List<LanguageInfo> languageProblemCount;
    }

    @lombok.Data
    public static class LanguageInfo{
        String languageName;
        int problemsSolved;
    }
}

package com.rajat_singh.leetcode_api.enums;

public enum ContestFilterType {
    IS_ATTENDED,        // attended : true/false
    TREND_DIRECTION,     // trendDirection : UP/DOWN/NONE
    PROBLEMS_SOLVED_GTE, // problemsSolved >= x
    PROBLEMS_SOLVED_LTE, // problemsSolved <= x
    FINISH_TIME,         // finished under time limit in seconds
    FILTER_BY_RATING,    // rating >= threshold
    MATCH_BY_TITLE,     // contest Title Name (weekly 131)
    BEST_RANKING        // best ranking
}

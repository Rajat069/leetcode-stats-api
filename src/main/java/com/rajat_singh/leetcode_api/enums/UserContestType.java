package com.rajat_singh.leetcode_api.enums;

import lombok.Getter;

@Getter
public enum UserContestType {
    INCLUDE_CONTEST_HISTORY("INCLUDE_CONTEST_HISTORY"),
    EXCLUDE_CONTEST_HISTORY("EXCLUDE_CONTEST_HISTORY"),
    ONLY_CONTEST_HISTORY("ONLY_CONTEST_HISTORY");

    private final String value;

    UserContestType(String value) {
        this.value = value;
    }

}

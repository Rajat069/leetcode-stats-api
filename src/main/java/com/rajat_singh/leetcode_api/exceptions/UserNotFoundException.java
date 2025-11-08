package com.rajat_singh.leetcode_api.exceptions;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("User '" + username + "' not found on LeetCode");
    }
}

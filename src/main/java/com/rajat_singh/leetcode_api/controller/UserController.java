package com.rajat_singh.leetcode_api.controller;

import com.rajat_singh.leetcode_api.dto.*;
import com.rajat_singh.leetcode_api.service.LeetCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tinylog.Logger;
@RestController
@RequestMapping("/api/v1/users/{username}")
@RequiredArgsConstructor
public class UserController {

    private final LeetCodeService leetCodeService;

    @GetMapping("/profile")
    public ResponseEntity<UserProgressResponse.UserProfileUserQuestionProgressV2> getUserProfile(@PathVariable String username) {
        Logger.info("getUserProfile() method called with username :: {}",username);
        return leetCodeService.getUserProfile(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/languageStats")
    public ResponseEntity<UserLanguageStats.MatchedUser> getUserLanguageStats(@PathVariable String username) {
        Logger.info("getUserLanguageStats() method called with username :: {}",username);
        return leetCodeService.getUserLanguageStats(username).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/publicInfo")
    public ResponseEntity<UserPublicInfo.MatchedUser> getUserPublicInfo(@PathVariable String username) {
        Logger.info("getUserPublicInfo() method called with username :: {}",username);
        return leetCodeService.getUserPublicInfo(username).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/badges")
    public ResponseEntity<UsersBadgeListResponse.MatchedUser> getUserBadges(@PathVariable String username) {
        Logger.info("getUserBadges() method called with username :: {}", username);
        return leetCodeService.getUserBadgesList(username)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/userSkillStats")
    public ResponseEntity<UserSkillStatsResponse.MatchedUser> getUserSkillStats(@PathVariable String username) {
        Logger.info("getUserSkillStats() method called with username :: {}", username);
        return leetCodeService.getUserSkillStats(username)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    };

    @GetMapping("/recentUserSubmissions/{limit}")
    public ResponseEntity<UserRecentSubmissionsResponse.DataNode> getUserRecentSubmissions(@PathVariable String username,@PathVariable int limit) {
        Logger.info("getUserRecentSubmissions() method called with username :: {}", username);
        return leetCodeService.getUserRecentSubmissions(username,limit)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/userCalendarStats/{year}")
    public ResponseEntity<UserCalendarDTO> getUserCalendarStats(@PathVariable String username,@PathVariable int year) {
        Logger.info("getUserCalendarStats() method called with username :: {}", username);
        return leetCodeService.getUserLeetCodeCalendar(username,year)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
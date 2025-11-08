package com.rajat_singh.leetcode_api.controller;

import com.rajat_singh.leetcode_api.dto.UserContestBiggestRatingJump;
import com.rajat_singh.leetcode_api.dto.UserContestRanking;
import com.rajat_singh.leetcode_api.dto.UserContestRankingHistory;
import com.rajat_singh.leetcode_api.dto.UserContestResponse;
import com.rajat_singh.leetcode_api.enums.ContestFilterType;
import com.rajat_singh.leetcode_api.service.LeetCodeContestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.tinylog.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{username}/contests")
@RequiredArgsConstructor
public class UserContestInfoController {

    @Value("${app.api.secret:}")
    private String apiSecret;

    private final LeetCodeContestService leetCodeContestService;

    @GetMapping()
    public ResponseEntity<UserContestResponse.DataNode> getUserContestRankingWithRankingHistory(@PathVariable String username){
        return leetCodeContestService.getUserContestRankingWithHistory(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/ranking")
    public ResponseEntity<UserContestRanking> getUserContestRanking(@PathVariable String username){
        return leetCodeContestService.getUserContestRanking(username).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/bestRanking")
    public ResponseEntity<UserContestRankingHistory> getUserContestBestRanking(@PathVariable String username) {
        return leetCodeContestService.getBestContestRanking(username).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rankingHistory")
    public ResponseEntity<List<UserContestRankingHistory>>getUserContestRankingHistory(@PathVariable String username) {
        return leetCodeContestService.getUserContestRankingHistory(username).map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/contest-name/{contestTitle}")
    public ResponseEntity<List<UserContestRankingHistory> >getUserContestRankingHistoryByContestTitle(@PathVariable String username, @PathVariable String contestTitle) {
        return  leetCodeContestService.getUsersContestInfo(username,contestTitle, ContestFilterType.MATCH_BY_TITLE).map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/hasAttended/{attended}")
    public ResponseEntity<List<UserContestRankingHistory> > getUserContestRankingHistoryByAttendance(@PathVariable String username ,@PathVariable String attended) {
        return leetCodeContestService.getUsersContestInfo(username,attended, ContestFilterType.IS_ATTENDED).map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/trendDirection/{direction}")
    public ResponseEntity<List<UserContestRankingHistory>> getUserContestRankingHistoryByTrendDirection(@PathVariable String username,@PathVariable String direction){
        return leetCodeContestService.getUsersContestInfo(username,direction,ContestFilterType.TREND_DIRECTION).map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/problemSolvedGTE/{questionCount}")
    public ResponseEntity<List<UserContestRankingHistory>> getUserContestRankingHistoryByProblemsSolvedGTE(@PathVariable String username,@PathVariable String questionCount){
        return leetCodeContestService.getUsersContestInfo(username,questionCount,ContestFilterType.PROBLEMS_SOLVED_GTE).map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/problemSolvedLTE/{questionCount}")
    public ResponseEntity<List<UserContestRankingHistory>> getUserContestRankingHistoryByProblemsSolvedLTE(@PathVariable String username,@PathVariable String questionCount){
        return leetCodeContestService.getUsersContestInfo(username,questionCount,ContestFilterType.PROBLEMS_SOLVED_LTE).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/finishTime/{timeInSeconds}")
    public ResponseEntity<List<UserContestRankingHistory>> getUserContestRankingHistoryByFinishTime(@PathVariable String username,@PathVariable String timeInSeconds) {
        return leetCodeContestService.getUsersContestInfo(username, timeInSeconds, ContestFilterType.FINISH_TIME).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/biggestJumpInRating")
    public ResponseEntity<UserContestBiggestRatingJump> getUserContestWithBiggestJumpInRating(@PathVariable String username) {
        return leetCodeContestService.getBiggestRatingJump(username).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/evictUserData")
    public ResponseEntity<String> evictUserContestData(@PathVariable String username,@RequestHeader(value = "X-API-KEY", required = false) String apiKey, HttpServletRequest request) {
        try {
            String remoteAddress = request.getRemoteAddr();
            boolean isLocal = "127.0.0.1".equals(remoteAddress) || "0:0:0:0:0:0:0:1".equals(remoteAddress);
            // Allow if locally we are using OR has valid key
            if (!isLocal && (apiSecret.isEmpty() || !apiSecret.equals(apiKey))) {
                Logger.info("API key missing or invalid from IP: {}", remoteAddress);
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Unauthorized: Access denied");
            }
            Logger.info("Evicting contest data for user: {} requested from IP: {}", username, remoteAddress);
            leetCodeContestService.evictUserContestData(username);
            return ResponseEntity.ok("Evicted contest data for user: " + username);
        } catch (Exception e) {
            Logger.error(e, "Error evicting contest data for user: {}", username);
            return ResponseEntity.status(500).body("Error evicting contest data for user: " + username);
        }
    }
}

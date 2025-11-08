package com.rajat_singh.leetcode_api.service;

import com.rajat_singh.leetcode_api.repository.ContestHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContestHistoryCleanupService {

    private final ContestHistoryRepository contestHistoryRepository;

    public void evictUserContestData(String username) {
        try {
            contestHistoryRepository.deleteByUsername(username);
            Logger.info("Evicted DB records for user: {}", username);
        } catch (Exception e) {
            Logger.error(e, "Error evicting DB data for user: {}", username);
        }
    }

    public void evictAllContestData() {
        try {
            contestHistoryRepository.deleteAll();
            Logger.info("Evicted all contest history records from DB");
        } catch (Exception e) {
            Logger.error(e, "Error clearing contest history table");
        }
    }

    public void evictInactiveUsers(List<String> usernames) {
        usernames.forEach(this::evictUserContestData);
    }
}

package com.rajat_singh.leetcode_api.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.rajat_singh.leetcode_api.repository.ContestHistoryRepository;
import com.rajat_singh.leetcode_api.service.ContestHistoryCleanupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.tinylog.Logger;

import java.time.Duration;
import java.time.DayOfWeek;
import java.time.LocalDate;

@EnableScheduling
@Configuration
public class CacheConfig {


    private final ContestHistoryCleanupService cleanupService;

    public CacheConfig(ContestHistoryCleanupService cleanupService) {
        this.cleanupService = cleanupService;
    }

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("contestHistoryCache","contestRankingCache","contestRankingWithHistoryCache");
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }

    private Caffeine<Object, Object> caffeineCacheBuilder() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        Duration expiryDuration = (today == DayOfWeek.WEDNESDAY)
                ? Duration.ofHours(1)    // LeetCode updates rankings on Wednesday
                : Duration.ofHours(24);

        return Caffeine.newBuilder()
                .expireAfterWrite(expiryDuration)
                .recordStats()
                .removalListener((key, value, cause) -> {
                    if (key != null) {
                        Logger.info("Evicting cache entry for user: {} due to {}", key, cause);
                        try {
                            cleanupService.evictUserContestData(key.toString());
                        } catch (Exception e) {
                            Logger.error(e, "Failed to evict DB data for user: {}", key);
                        }
                    }
                });
    }

    @Scheduled(fixedRate = 7200000) // every 2 hour
    public void logCacheStats() {
        CaffeineCacheManager caffeineCacheManager = (CaffeineCacheManager) cacheManager();
        for(String cacheName : caffeineCacheManager.getCacheNames()) {
            com.github.benmanes.caffeine.cache.Cache<Object, Object> nativeCache =
                    (com.github.benmanes.caffeine.cache.Cache<Object, Object>)
                            caffeineCacheManager.getCache(cacheName).getNativeCache();
            Logger.info("{} stats: {}", cacheName, nativeCache.stats());
        }
    }


}

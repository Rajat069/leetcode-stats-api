package com.rajat_singh.leetcode_api.repository;

import com.rajat_singh.leetcode_api.dto.UserContestRankingHistory;
import com.rajat_singh.leetcode_api.entity.UserContestHistoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ContestHistoryRepository extends JpaRepository<UserContestHistoryEntity, Long> {

    List<UserContestHistoryEntity> findByUsername(String username);
    List<UserContestHistoryEntity> findByUsernameAndAttended(String username, boolean isAttended);
    List<UserContestHistoryEntity> findByUsernameAndTrendDirection(String username, String trendDirection);
    List<UserContestHistoryEntity> findByUsernameAndProblemsSolvedGreaterThanEqual(String username, int count);
    List<UserContestHistoryEntity> findByUsernameAndProblemsSolvedLessThanEqual(String username, int count);
    List<UserContestHistoryEntity> findByUsernameAndRankingLessThanEqual(String username, int rank);
    UserContestHistoryEntity findByUsernameAndTitleContainingIgnoreCase(String username, String titlePart);
    List<UserContestHistoryEntity> findByUsernameAndFinishTimeInSecondsLessThanEqualAndAttendedTrue(String username, long finishTimeInSeconds);
    List<UserContestHistoryEntity> findByUsernameAndRatingEquals(String username, int rating);
    UserContestHistoryEntity findTopByUsernameAndRankingGreaterThanOrderByRankingAsc(String username,int ranking);
    boolean existsByUsername(String username);

    @Query(value = """
        WITH OrderedContests AS (
            SELECT
                title,
                rating,
                start_time,
                finish_time_in_seconds,
                problems_solved,
                total_problems,
                ranking,
                attended,
                trend_direction,
                LAG(rating) OVER (ORDER BY start_time) AS previous_rating
            FROM contest_history
            WHERE username = :username
              AND attended = 1
        ),
        RatedDiff AS (
            SELECT
                title,
                ranking,
                rating,
                start_time,
                attended,
                trend_direction,
                finish_time_in_seconds,
                problems_solved,
                total_problems,
                previous_rating,
                rating AS new_rating,
                (rating - previous_rating) AS rating_jump
            FROM OrderedContests
            WHERE previous_rating IS NOT NULL
        )
        SELECT *
        FROM RatedDiff
        ORDER BY rating_jump DESC
        LIMIT 1
        """, nativeQuery = true)
    Map<String, Object> findBiggestRatingJump(@Param("username") String username);

    @Transactional
    void deleteByUsername(String username);
}

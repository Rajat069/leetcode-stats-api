package com.rajat_singh.leetcode_api.repository;

import com.rajat_singh.leetcode_api.entity.QuestionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionsRepository extends JpaRepository<QuestionEntity,Integer>, JpaSpecificationExecutor<QuestionEntity> {

    QuestionEntity findByTitleSlug(String title);
    QuestionEntity findByIsProblemOfTheDayTrue();

    @Transactional
    void deleteByIsProblemOfTheDayTrue();
}

package com.rajat_singh.leetcode_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "leetcode_questions")
public class QuestionEntity {
    @Id
    int id;
    String difficulty;
    String title;
    @Column(unique = true)
    String titleSlug;
    Double acRate;
    Boolean isPaidOnly;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "question_topic_tags",
            joinColumns = @JoinColumn(name = "question_id")
    )
    private List<TopicTag> topicTags = new ArrayList<>();
    String problemUrl;
}

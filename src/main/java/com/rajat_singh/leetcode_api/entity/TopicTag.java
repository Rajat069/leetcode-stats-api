package com.rajat_singh.leetcode_api.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Embeddable // Tells JPA this class can be embedded in other entities
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicTag {
    private String name;
    private String slug;
}
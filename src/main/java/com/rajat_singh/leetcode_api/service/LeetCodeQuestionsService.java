package com.rajat_singh.leetcode_api.service;

import com.rajat_singh.leetcode_api.client.LeetCodeClient;
import com.rajat_singh.leetcode_api.dto.QuestionListResponse;
import com.rajat_singh.leetcode_api.dto.QuestionResponseDTO;
import com.rajat_singh.leetcode_api.dto.QuestionSearchRequest;
import com.rajat_singh.leetcode_api.entity.QuestionEntity;
import com.rajat_singh.leetcode_api.enums.questions.SortOrder;
import com.rajat_singh.leetcode_api.mappers.QuestionMapper;
import com.rajat_singh.leetcode_api.repository.QuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeetCodeQuestionsService {

    private final QuestionsRepository questionRepository;
    private final QuestionSpecificationService specificationService;
    private final QuestionMapper questionMapper;

    public Page<QuestionResponseDTO> findQuestions(QuestionSearchRequest request) {

        Pageable pageable = createPageable(request);

        Specification<QuestionEntity> spec = specificationService.build(
                request.getFilters(), request.getSearchKeyword()
        );

        Page<QuestionEntity> entityPage = questionRepository.findAll(spec, pageable);

        return entityPage.map(questionMapper::entityToResponseDTO);
    }

    /**
     * Helper method to convert your DTOs into a Spring Data Pageable object.
     */
    private Pageable createPageable(QuestionSearchRequest request) {

        int size = (request.getLimit() <= 0) ? 10 : request.getLimit();
        int page = request.getSkip() / size;

        Sort sort = Sort.unsorted(); // Default


        if (request.getSortBy() != null && request.getSortBy().getSortField() != null) {

            QuestionSearchRequest.SortingCriteria sortBy = request.getSortBy();

            Sort.Direction direction = (sortBy.getSortOrder() == SortOrder.DESCENDING) ?
                    Sort.Direction.DESC : Sort.Direction.ASC;

            String field = switch (sortBy.getSortField()) {
                case AC_RATE -> "acRate";
                case FRONTEND_ID, CUSTOM -> "id";
                case DIFFICULTY -> "difficulty";
            };

            sort = Sort.by(direction, field);
        }

        return PageRequest.of(page, size, sort);
    }
}

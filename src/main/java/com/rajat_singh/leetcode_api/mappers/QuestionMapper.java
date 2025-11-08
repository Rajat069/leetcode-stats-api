package com.rajat_singh.leetcode_api.mappers;


import com.rajat_singh.leetcode_api.dto.QuestionListResponse;
import com.rajat_singh.leetcode_api.dto.QuestionResponseDTO;
import com.rajat_singh.leetcode_api.entity.QuestionEntity;
import com.rajat_singh.leetcode_api.entity.TopicTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    // API -> ENTITY (for syncing to DB)
    @Mapping(source = "paidOnly", target = "isPaidOnly")
    @Mapping(target = "problemUrl", expression = "java(dto.getProblemUrl())")
    QuestionEntity apiDtoToEntity(QuestionListResponse.Question dto);

    TopicTag apiTagToEntityTag(QuestionListResponse.TopicTag dtoTag);


    @Mapping(source = "isPaidOnly", target = "isPaidOnly")
    QuestionResponseDTO entityToResponseDTO(QuestionEntity entity);

}
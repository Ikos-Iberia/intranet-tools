package com.saniikos.backend.mappers;

import org.springframework.stereotype.Component;

import com.saniikos.backend.dto.toolcategory.ToolCategoryRequest;
import com.saniikos.backend.dto.toolcategory.ToolCategoryResponse;
import com.saniikos.backend.entities.ToolCategory;

@Component
public class ToolCategoryMapper {

    public ToolCategoryResponse toResponse(ToolCategory category) {

        if (category == null) {
            return null;
        }

        ToolCategoryResponse response = new ToolCategoryResponse();

        response.setId(category.getId());
        response.setCode(category.getCode());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        response.setSystem(category.getSystem());
        response.setActive(category.getActive());
        response.setDisplayOrder(category.getDisplayOrder());
        response.setCreatedAt(category.getCreatedAt());
        response.setUpdatedAt(category.getUpdatedAt());

        return response;
    }

    public ToolCategory toEntity(ToolCategoryRequest request) {

        if (request == null) {
            return null;
        }

        ToolCategory category = new ToolCategory();

        category.setCode(request.getCode());
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setDisplayOrder(request.getDisplayOrder());
        category.setActive(request.getActive());

        return category;
    }

    public void updateEntity(ToolCategoryRequest request, ToolCategory category) {

        category.setCode(request.getCode());
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setDisplayOrder(request.getDisplayOrder());
        category.setActive(request.getActive());

    }

}
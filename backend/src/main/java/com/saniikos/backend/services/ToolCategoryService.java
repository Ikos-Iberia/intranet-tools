package com.saniikos.backend.services;

import java.util.List;

import com.saniikos.backend.dto.toolcategory.ToolCategoryRequest;
import com.saniikos.backend.dto.toolcategory.ToolCategoryResponse;

public interface ToolCategoryService {

    List<ToolCategoryResponse> findAll();

    ToolCategoryResponse findById(Long id);

    ToolCategoryResponse findByCode(String code);

    List<ToolCategoryResponse> findActive();

    ToolCategoryResponse save(ToolCategoryRequest request);

    ToolCategoryResponse update(Long id, ToolCategoryRequest request);

    void delete(Long id);

}
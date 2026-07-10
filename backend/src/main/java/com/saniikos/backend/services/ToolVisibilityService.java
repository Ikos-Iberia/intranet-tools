package com.saniikos.backend.services;

import java.util.List;

import com.saniikos.backend.dto.toolvisibility.ToolVisibilityRequest;
import com.saniikos.backend.dto.toolvisibility.ToolVisibilityResponse;

public interface ToolVisibilityService {

    List<ToolVisibilityResponse> findAll();

    ToolVisibilityResponse findById(Long id);

    ToolVisibilityResponse findByCode(String code);

    ToolVisibilityResponse save(ToolVisibilityRequest request);

    ToolVisibilityResponse update(Long id, ToolVisibilityRequest request);

    void delete(Long id);

}
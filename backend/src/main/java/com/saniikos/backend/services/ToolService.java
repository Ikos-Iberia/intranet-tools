package com.saniikos.backend.services;

import java.util.List;

import com.saniikos.backend.dto.tool.ToolRequest;
import com.saniikos.backend.dto.tool.ToolResponse;

public interface ToolService {

    List<ToolResponse> findAll();

    ToolResponse findById(Long id);

    ToolResponse findByCode(String code);

    ToolResponse findByRoute(String route);

    List<ToolResponse> findEnabled();

    ToolResponse save(ToolRequest request);

    ToolResponse update(Long id, ToolRequest request);

    void delete(Long id);

}
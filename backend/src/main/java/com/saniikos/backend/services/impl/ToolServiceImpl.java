package com.saniikos.backend.services.impl;

import com.saniikos.backend.dto.tool.ToolRequest;
import com.saniikos.backend.dto.tool.ToolResponse;
import com.saniikos.backend.entities.Tool;
import com.saniikos.backend.entities.ToolCategory;
import com.saniikos.backend.entities.ToolVisibility;
import com.saniikos.backend.mappers.ToolMapper;
import com.saniikos.backend.repositories.ToolCategoryRepository;
import com.saniikos.backend.repositories.ToolRepository;
import com.saniikos.backend.repositories.ToolVisibilityRepository;
import com.saniikos.backend.services.ToolService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ToolServiceImpl implements ToolService {

    private final ToolRepository toolRepository;
    private final ToolCategoryRepository toolCategoryRepository;
    private final ToolVisibilityRepository toolVisibilityRepository;
    private final ToolMapper toolMapper;

    public ToolServiceImpl(
            ToolRepository toolRepository,
            ToolCategoryRepository toolCategoryRepository,
            ToolVisibilityRepository toolVisibilityRepository,
            ToolMapper toolMapper) {

        this.toolRepository = toolRepository;
        this.toolCategoryRepository = toolCategoryRepository;
        this.toolVisibilityRepository = toolVisibilityRepository;
        this.toolMapper = toolMapper;
    }

    @Override
    public List<ToolResponse> findAll() {
        return toolRepository.findAll()
                .stream()
                .map(toolMapper::toResponse)
                .toList();
    }

    @Override
    public ToolResponse findById(Long id) {

        Tool tool = toolRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool not found with id " + id));

        return toolMapper.toResponse(tool);
    }

    @Override
    public ToolResponse findByCode(String code) {

        Tool tool = toolRepository.findByCode(code)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool not found with code " + code));

        return toolMapper.toResponse(tool);
    }

    @Override
    public ToolResponse findByRoute(String route) {

        Tool tool = toolRepository.findByRoute(route)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool not found with route " + route));

        return toolMapper.toResponse(tool);
    }

    @Override
    public List<ToolResponse> findEnabled() {
        return toolRepository.findByEnabledTrueOrderByDisplayOrderAsc()
                .stream()
                .map(toolMapper::toResponse)
                .toList();
    }

    @Override
    public ToolResponse save(ToolRequest request) {

        if (toolRepository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException("Tool code already exists.");
        }

        if (toolRepository.existsByRoute(request.getRoute())) {
            throw new IllegalArgumentException("Tool route already exists.");
        }

        ToolCategory category = toolCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool category not found with id " + request.getCategoryId()));

        ToolVisibility visibility = toolVisibilityRepository.findById(request.getVisibilityId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool visibility not found with id " + request.getVisibilityId()));

        Tool tool = toolMapper.toEntity(request);

        tool.setCategory(category);
        tool.setVisibility(visibility);

        tool = toolRepository.save(tool);

        return toolMapper.toResponse(tool);
    }

    @Override
    public ToolResponse update(Long id, ToolRequest request) {

        Tool existing = toolRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool not found with id " + id));

        toolRepository.findByCode(request.getCode())
                .filter(tool -> !tool.getId().equals(id))
                .ifPresent(tool -> {
                    throw new IllegalArgumentException("Tool code already exists.");
                });

        toolRepository.findByRoute(request.getRoute())
                .filter(tool -> !tool.getId().equals(id))
                .ifPresent(tool -> {
                    throw new IllegalArgumentException("Tool route already exists.");
                });

        ToolCategory category = toolCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool category not found with id " + request.getCategoryId()));

        ToolVisibility visibility = toolVisibilityRepository.findById(request.getVisibilityId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool visibility not found with id " + request.getVisibilityId()));

        toolMapper.updateEntity(request, existing);

        existing.setCategory(category);
        existing.setVisibility(visibility);

        existing = toolRepository.save(existing);

        return toolMapper.toResponse(existing);
    }

    @Override
    public void delete(Long id) {

        Tool tool = toolRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool not found with id " + id));

        tool.setEnabled(false);

        toolRepository.save(tool);
    }

}
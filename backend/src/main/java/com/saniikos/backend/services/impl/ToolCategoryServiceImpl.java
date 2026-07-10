package com.saniikos.backend.services.impl;

import com.saniikos.backend.dto.toolcategory.ToolCategoryRequest;
import com.saniikos.backend.dto.toolcategory.ToolCategoryResponse;
import com.saniikos.backend.entities.ToolCategory;
import com.saniikos.backend.mappers.ToolCategoryMapper;
import com.saniikos.backend.repositories.ToolCategoryRepository;
import com.saniikos.backend.services.ToolCategoryService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ToolCategoryServiceImpl implements ToolCategoryService {

    private final ToolCategoryRepository toolCategoryRepository;
    private final ToolCategoryMapper toolCategoryMapper;

    public ToolCategoryServiceImpl(
            ToolCategoryRepository toolCategoryRepository,
            ToolCategoryMapper toolCategoryMapper) {
        this.toolCategoryRepository = toolCategoryRepository;
        this.toolCategoryMapper = toolCategoryMapper;
    }

    @Override
    public List<ToolCategoryResponse> findAll() {
        return toolCategoryRepository.findAll()
                .stream()
                .map(toolCategoryMapper::toResponse)
                .toList();
    }

    @Override
    public ToolCategoryResponse findById(Long id) {
        ToolCategory category = toolCategoryRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool category not found with id " + id));

        return toolCategoryMapper.toResponse(category);
    }

    @Override
    public ToolCategoryResponse findByCode(String code) {
        ToolCategory category = toolCategoryRepository.findByCode(code)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool category not found with code " + code));

        return toolCategoryMapper.toResponse(category);
    }

    @Override
    public List<ToolCategoryResponse> findActive() {
        return toolCategoryRepository.findByActiveTrueOrderByDisplayOrderAsc()
                .stream()
                .map(toolCategoryMapper::toResponse)
                .toList();
    }

    @Override
    public ToolCategoryResponse save(ToolCategoryRequest request) {

        if (toolCategoryRepository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException("Tool category code already exists.");
        }

        ToolCategory category = toolCategoryMapper.toEntity(request);

        category = toolCategoryRepository.save(category);

        return toolCategoryMapper.toResponse(category);
    }

    @Override
    public ToolCategoryResponse update(Long id, ToolCategoryRequest request) {

        ToolCategory existing = toolCategoryRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool category not found with id " + id));

        toolCategoryRepository.findByCode(request.getCode())
                .filter(category -> !category.getId().equals(id))
                .ifPresent(category -> {
                    throw new IllegalArgumentException("Tool category code already exists.");
                });

        toolCategoryMapper.updateEntity(request, existing);

        existing = toolCategoryRepository.save(existing);

        return toolCategoryMapper.toResponse(existing);
    }

    @Override
    public void delete(Long id) {

        ToolCategory category = toolCategoryRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool category not found with id " + id));

        if (Boolean.TRUE.equals(category.getSystem())) {
            throw new IllegalArgumentException("System tool categories cannot be deleted.");
        }

        category.setActive(false);

        toolCategoryRepository.save(category);
    }
}
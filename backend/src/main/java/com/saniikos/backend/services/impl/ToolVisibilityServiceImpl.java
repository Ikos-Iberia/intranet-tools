package com.saniikos.backend.services.impl;

import com.saniikos.backend.dto.toolvisibility.ToolVisibilityRequest;
import com.saniikos.backend.dto.toolvisibility.ToolVisibilityResponse;
import com.saniikos.backend.entities.ToolVisibility;
import com.saniikos.backend.mappers.ToolVisibilityMapper;
import com.saniikos.backend.repositories.ToolVisibilityRepository;
import com.saniikos.backend.services.ToolVisibilityService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ToolVisibilityServiceImpl implements ToolVisibilityService {

    private final ToolVisibilityRepository toolVisibilityRepository;
    private final ToolVisibilityMapper toolVisibilityMapper;

    public ToolVisibilityServiceImpl(
            ToolVisibilityRepository toolVisibilityRepository,
            ToolVisibilityMapper toolVisibilityMapper) {
        this.toolVisibilityRepository = toolVisibilityRepository;
        this.toolVisibilityMapper = toolVisibilityMapper;
    }

    @Override
    public List<ToolVisibilityResponse> findAll() {
        return toolVisibilityRepository.findAll()
                .stream()
                .map(toolVisibilityMapper::toResponse)
                .toList();
    }

    @Override
    public ToolVisibilityResponse findById(Long id) {

        ToolVisibility visibility = toolVisibilityRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool visibility not found with id " + id));

        return toolVisibilityMapper.toResponse(visibility);
    }

    @Override
    public ToolVisibilityResponse findByCode(String code) {

        ToolVisibility visibility = toolVisibilityRepository.findByCode(code)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool visibility not found with code " + code));

        return toolVisibilityMapper.toResponse(visibility);
    }

    @Override
    public ToolVisibilityResponse save(ToolVisibilityRequest request) {

        if (toolVisibilityRepository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException("Tool visibility code already exists.");
        }

        ToolVisibility visibility = toolVisibilityMapper.toEntity(request);

        visibility = toolVisibilityRepository.save(visibility);

        return toolVisibilityMapper.toResponse(visibility);
    }

    @Override
    public ToolVisibilityResponse update(Long id, ToolVisibilityRequest request) {

        ToolVisibility existing = toolVisibilityRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool visibility not found with id " + id));

        toolVisibilityRepository.findByCode(request.getCode())
                .filter(visibility -> !visibility.getId().equals(id))
                .ifPresent(visibility -> {
                    throw new IllegalArgumentException("Tool visibility code already exists.");
                });

        toolVisibilityMapper.updateEntity(request, existing);

        existing = toolVisibilityRepository.save(existing);

        return toolVisibilityMapper.toResponse(existing);
    }

    @Override
    public void delete(Long id) {

        ToolVisibility visibility = toolVisibilityRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool visibility not found with id " + id));

        toolVisibilityRepository.delete(visibility);
    }
}
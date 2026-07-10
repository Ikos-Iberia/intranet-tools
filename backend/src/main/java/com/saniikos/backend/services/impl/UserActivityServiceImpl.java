package com.saniikos.backend.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.saniikos.backend.dto.useractivity.UserActivityRequest;
import com.saniikos.backend.dto.useractivity.UserActivityResponse;
import com.saniikos.backend.entities.Tool;
import com.saniikos.backend.entities.User;
import com.saniikos.backend.entities.UserActivity;
import com.saniikos.backend.mappers.UserActivityMapper;
import com.saniikos.backend.repositories.ToolRepository;
import com.saniikos.backend.repositories.UserActivityRepository;
import com.saniikos.backend.repositories.UserRepository;
import com.saniikos.backend.services.UserActivityService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserActivityServiceImpl implements UserActivityService {

    private final UserActivityRepository userActivityRepository;
    private final UserRepository userRepository;
    private final ToolRepository toolRepository;
    private final UserActivityMapper userActivityMapper;

    public UserActivityServiceImpl(
            UserActivityRepository userActivityRepository,
            UserRepository userRepository,
            ToolRepository toolRepository,
            UserActivityMapper userActivityMapper) {

        this.userActivityRepository = userActivityRepository;
        this.userRepository = userRepository;
        this.toolRepository = toolRepository;
        this.userActivityMapper = userActivityMapper;
    }


    @Override
    public List<UserActivityResponse> findAll() {

        return userActivityRepository.findAll()
                .stream()
                .map(userActivityMapper::toResponse)
                .toList();
    }


    @Override
    public UserActivityResponse findById(Long id) {

        UserActivity activity = userActivityRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "User activity not found with id " + id));

        return userActivityMapper.toResponse(activity);
    }


    @Override
    public List<UserActivityResponse> findByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "User not found with id " + userId));

        return userActivityRepository
                .findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(userActivityMapper::toResponse)
                .toList();
    }


    @Override
    public List<UserActivityResponse> findByTool(Long toolId) {

        Tool tool = toolRepository.findById(toolId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Tool not found with id " + toolId));

        return userActivityRepository
                .findByToolOrderByCreatedAtDesc(tool)
                .stream()
                .map(userActivityMapper::toResponse)
                .toList();
    }


    @Override
    public List<UserActivityResponse> findByActionCode(String actionCode) {

        return userActivityRepository.findByActionCode(actionCode)
                .stream()
                .map(userActivityMapper::toResponse)
                .toList();
    }


    @Override
    public List<UserActivityResponse> findBySuccess(Boolean success) {

        return userActivityRepository.findBySuccess(success)
                .stream()
                .map(userActivityMapper::toResponse)
                .toList();
    }


    @Override
    public List<UserActivityResponse> findByDateRange(
            LocalDateTime start,
            LocalDateTime end) {

        return userActivityRepository
                .findByCreatedAtBetween(start, end)
                .stream()
                .map(userActivityMapper::toResponse)
                .toList();
    }


    @Override
    public UserActivityResponse save(UserActivityRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "User not found with id " + request.getUserId()));


        Tool tool = null;

        if (request.getToolId() != null) {

            tool = toolRepository.findById(request.getToolId())
                    .orElseThrow(() ->
                            new EntityNotFoundException(
                                    "Tool not found with id " + request.getToolId()));
        }


        UserActivity activity = new UserActivity();

        activity.setUser(user);
        activity.setTool(tool);

        activity.setActionCode(request.getActionCode());
        activity.setEntityType(request.getEntityType());
        activity.setEntityId(request.getEntityId());

        activity.setIpAddress(request.getIpAddress());
        activity.setUserAgent(request.getUserAgent());

        activity.setMetadata(request.getMetadata());

        activity.setSuccess(
                request.getSuccess() != null
                        ? request.getSuccess()
                        : true
        );


        activity = userActivityRepository.save(activity);


        return userActivityMapper.toResponse(activity);
    }

}
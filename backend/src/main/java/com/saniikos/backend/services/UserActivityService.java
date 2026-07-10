package com.saniikos.backend.services;

import java.time.LocalDateTime;
import java.util.List;

import com.saniikos.backend.dto.useractivity.UserActivityRequest;
import com.saniikos.backend.dto.useractivity.UserActivityResponse;

public interface UserActivityService {

    List<UserActivityResponse> findAll();

    UserActivityResponse findById(Long id);

    List<UserActivityResponse> findByUser(Long userId);

    List<UserActivityResponse> findByTool(Long toolId);

    List<UserActivityResponse> findByActionCode(String actionCode);

    List<UserActivityResponse> findBySuccess(Boolean success);

    List<UserActivityResponse> findByDateRange(
            LocalDateTime start,
            LocalDateTime end
    );

    UserActivityResponse save(UserActivityRequest request);

}
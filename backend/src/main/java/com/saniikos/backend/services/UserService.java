package com.saniikos.backend.services;

import java.util.List;

import com.saniikos.backend.dto.user.UserRequest;
import com.saniikos.backend.dto.user.UserResponse;

public interface UserService {

    List<UserResponse> findAll();

    UserResponse findById(Long id);

    UserResponse findByUsername(String username);

    UserResponse findByEmail(String email);

    UserResponse save(UserRequest request);

    UserResponse update(Long id, UserRequest request);

    void delete(Long id);

}
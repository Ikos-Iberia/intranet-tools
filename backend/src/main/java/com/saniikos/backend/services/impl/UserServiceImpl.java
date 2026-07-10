package com.saniikos.backend.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.saniikos.backend.dto.user.UserRequest;
import com.saniikos.backend.dto.user.UserResponse;
import com.saniikos.backend.entities.Department;
import com.saniikos.backend.entities.User;
import com.saniikos.backend.mappers.UserMapper;
import com.saniikos.backend.repositories.DepartmentRepository;
import com.saniikos.backend.repositories.UserRepository;
import com.saniikos.backend.services.UserService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            DepartmentRepository departmentRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse findById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with id " + id));

        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse findByUsername(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with username " + username));

        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse findByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with email " + email));

        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse save(UserRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists.");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Department not found with id " + request.getDepartmentId()));

        User user = userMapper.toEntity(request);

        user.setDepartment(department);

        user.setPasswordHash(
                passwordEncoder.encode(request.getPassword()));

        user = userRepository.save(user);

        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {

        User existing = userRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with id " + id));

        userRepository.findByUsername(request.getUsername())
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {
                    throw new IllegalArgumentException("Username already exists.");
                });

        userRepository.findByEmail(request.getEmail())
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {
                    throw new IllegalArgumentException("Email already exists.");
                });

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Department not found with id " + request.getDepartmentId()));

        userMapper.updateEntity(request, existing);

        existing.setDepartment(department);

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            existing.setPasswordHash(
                    passwordEncoder.encode(request.getPassword()));
        }

        existing = userRepository.save(existing);

        return userMapper.toResponse(existing);
    }

    @Override
    public void delete(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found with id " + id));

        user.setActive(false);
        user.setDeletedAt(LocalDateTime.now());

        userRepository.save(user);
    }

}
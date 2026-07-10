package com.saniikos.backend.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saniikos.backend.dto.userrole.UserRoleRequest;
import com.saniikos.backend.dto.userrole.UserRoleResponse;
import com.saniikos.backend.entities.Role;
import com.saniikos.backend.entities.User;
import com.saniikos.backend.entities.UserRole;
import com.saniikos.backend.entities.UserRoleId;
import com.saniikos.backend.mappers.UserRoleMapper;
import com.saniikos.backend.repositories.RoleRepository;
import com.saniikos.backend.repositories.UserRepository;
import com.saniikos.backend.repositories.UserRoleRepository;
import com.saniikos.backend.services.UserRoleService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleMapper userRoleMapper;

    public UserRoleServiceImpl(
            UserRoleRepository userRoleRepository,
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserRoleMapper userRoleMapper) {

        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public List<UserRoleResponse> findAll() {

        return userRoleRepository.findAll()
                .stream()
                .map(userRoleMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserRoleResponse> findByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "User not found with id " + userId));

        return userRoleRepository.findByUser(user)
                .stream()
                .map(userRoleMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserRoleResponse> findByRole(Long roleId) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Role not found with id " + roleId));

        return userRoleRepository.findByRole(role)
                .stream()
                .map(userRoleMapper::toResponse)
                .toList();
    }

    @Override
    public UserRoleResponse save(UserRoleRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "User not found with id " + request.getUserId()));

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Role not found with id " + request.getRoleId()));

        UserRoleId id = new UserRoleId(
                request.getUserId(),
                request.getRoleId()
        );

        if (userRoleRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    "The relationship between user and role already exists.");
        }

        UserRole userRole = new UserRole();

        userRole.setId(id);
        userRole.setUser(user);
        userRole.setRole(role);

        userRole = userRoleRepository.save(userRole);

        return userRoleMapper.toResponse(userRole);
    }

    @Override
    public void delete(Long userId, Long roleId) {

        UserRoleId id = new UserRoleId(userId, roleId);

        UserRole userRole = userRoleRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Relationship not found between user "
                                        + userId
                                        + " and role "
                                        + roleId));

        userRoleRepository.delete(userRole);
    }

}
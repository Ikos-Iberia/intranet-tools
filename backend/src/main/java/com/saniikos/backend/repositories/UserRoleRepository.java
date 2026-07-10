package com.saniikos.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saniikos.backend.entities.Role;
import com.saniikos.backend.entities.User;
import com.saniikos.backend.entities.UserRole;
import com.saniikos.backend.entities.UserRoleId;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    List<UserRole> findByUser(User user);

    List<UserRole> findByRole(Role role);

    void deleteByUser(User user);

    void deleteByRole(Role role);

}
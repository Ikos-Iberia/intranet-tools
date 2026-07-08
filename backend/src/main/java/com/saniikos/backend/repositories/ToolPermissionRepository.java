package com.saniikos.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saniikos.backend.entities.Role;
import com.saniikos.backend.entities.Tool;
import com.saniikos.backend.entities.ToolPermission;
import com.saniikos.backend.entities.ToolPermissionId;

@Repository
public interface ToolPermissionRepository extends JpaRepository<ToolPermission, ToolPermissionId> {

    List<ToolPermission> findByTool(Tool tool);

    List<ToolPermission> findByRole(Role role);

    void deleteByTool(Tool tool);

    void deleteByRole(Role role);

}
package com.saniikos.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saniikos.backend.entities.Department;
import com.saniikos.backend.entities.Tool;
import com.saniikos.backend.entities.ToolDepartment;
import com.saniikos.backend.entities.ToolDepartmentId;

@Repository
public interface ToolDepartmentRepository extends JpaRepository<ToolDepartment, ToolDepartmentId> {

    List<ToolDepartment> findByTool(Tool tool);

    List<ToolDepartment> findByDepartment(Department department);

    void deleteByTool(Tool tool);

    void deleteByDepartment(Department department);

}
package com.saniikos.backend.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tool_departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolDepartment {

    @EmbeddedId
    private ToolDepartmentId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("toolId")
    @JoinColumn(name = "tool_id")
    private Tool tool;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("departmentId")
    @JoinColumn(name = "department_id")
    private Department department;

}
package com.saniikos.backend.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tools")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, unique = true)
    private String route;

    private String icon;

    @Column(nullable = false, length = 30)
    private String version = "1.0.0";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ToolCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visibility_id", nullable = false)
    private ToolVisibility visibility;

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder = 0;

    @Column(name = "frontend_only", nullable = false)
    private Boolean frontendOnly = false;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "tool")
    private Set<ToolPermission> permissions = new HashSet<>();

    @OneToMany(mappedBy = "tool")
    private Set<ToolDepartment> departments = new HashSet<>();

    @OneToMany(mappedBy = "tool")
    private Set<UserActivity> activities = new HashSet<>();

}
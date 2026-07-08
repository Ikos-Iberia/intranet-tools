package com.saniikos.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saniikos.backend.entities.Tool;
import com.saniikos.backend.entities.ToolCategory;
import com.saniikos.backend.entities.ToolVisibility;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {

    Optional<Tool> findByCode(String code);

    Optional<Tool> findByRoute(String route);

    List<Tool> findByEnabledTrueOrderByDisplayOrderAsc();

    List<Tool> findByCategoryOrderByDisplayOrderAsc(ToolCategory category);

    List<Tool> findByVisibility(ToolVisibility visibility);

    List<Tool> findByCategoryAndEnabledTrueOrderByDisplayOrderAsc(ToolCategory category);

    boolean existsByCode(String code);

    boolean existsByRoute(String route);

}
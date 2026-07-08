package com.saniikos.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saniikos.backend.entities.ToolCategory;

@Repository
public interface ToolCategoryRepository extends JpaRepository<ToolCategory, Long> {

    Optional<ToolCategory> findByCode(String code);

    Optional<ToolCategory> findByName(String name);

    List<ToolCategory> findByActiveTrueOrderByDisplayOrderAsc();

    boolean existsByCode(String code);

}
package com.saniikos.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saniikos.backend.entities.ToolVisibility;

@Repository
public interface ToolVisibilityRepository extends JpaRepository<ToolVisibility, Long> {

    Optional<ToolVisibility> findByCode(String code);

    Optional<ToolVisibility> findByName(String name);

    boolean existsByCode(String code);

}
package com.saniikos.backend.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saniikos.backend.entities.UserActivity;
import com.saniikos.backend.entities.User;
import com.saniikos.backend.entities.Tool;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

    List<UserActivity> findByUser(User user);

    List<UserActivity> findByTool(Tool tool);

    List<UserActivity> findByUserOrderByCreatedAtDesc(User user);

    List<UserActivity> findByToolOrderByCreatedAtDesc(Tool tool);

    List<UserActivity> findByActionCode(String actionCode);

    List<UserActivity> findBySuccess(Boolean success);

    List<UserActivity> findByCreatedAtBetween(
            LocalDateTime start,
            LocalDateTime end
    );

}
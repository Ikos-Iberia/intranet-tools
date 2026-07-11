package com.saniikos.backend.dto.user;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "User response")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @Schema(description = "User id", example = "1")
    private Long id;

    @Schema(description = "User first name", example = "John")
    private String firstName;

    @Schema(description = "User last name", example = "Doe")
    private String lastName;

    @Schema(description = "User username", example = "johndoe")
    private String username;

    @Schema(description = "User email", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Department id", example = "1")
    private Long departmentId;

    @Schema(description = "Department code", example = "DEPT001")
    private String departmentCode;

    @Schema(description = "Department name", example = "Department Name")
    private String departmentName;

    @Schema(description = "Active status", example = "true")
    private Boolean active;

    @Schema(description = "Last login date", example = "2023-01-01T00:00:00")
    private LocalDateTime lastLogin;

    @Schema(description = "Created at", example = "2023-01-01T00:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Updated at", example = "2023-01-01T00:00:00")
    private LocalDateTime updatedAt;

}
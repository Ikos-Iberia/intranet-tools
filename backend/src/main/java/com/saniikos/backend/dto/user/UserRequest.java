package com.saniikos.backend.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "User request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @Schema(description = "User first name", example = "John")
    @NotBlank
    @Size(max = 50)
    private String firstName;

    @Schema(description = "User last name", example = "Doe")
    @Size(max = 100)
    private String lastName;

    @Schema(description = "User username", example = "johndoe")
    @NotBlank
    @Size(max = 50)
    private String username;

    @Schema(description = "User email", example = "john.doe@example.com")
    @NotBlank
    @Email
    @Size(max = 150)
    private String email;

    @Schema(description = "User password", example = "password123")
    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    @Schema(description = "Department id", example = "1")
    @NotNull
    private Long departmentId;

    @Schema(description = "Active status", example = "true")
    @NotNull
    private Boolean active;

}
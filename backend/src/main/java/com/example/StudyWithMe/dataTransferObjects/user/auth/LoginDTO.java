package com.example.StudyWithMe.dataTransferObjects.user.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LoginDTO {
    @Size(min = 6, message = "Username must be at least 6 characters")
    private String userName;
    @Size(min = 8, message = "Password must be at least 8 characters")
    @NotBlank(message = "Password cannot be blank")
    private String password;
}

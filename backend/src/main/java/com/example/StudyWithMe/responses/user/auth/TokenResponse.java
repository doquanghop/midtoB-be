package com.example.StudyWithMe.responses.user.auth;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class TokenResponse {
    private String tokenType;
    private String accessToken;
    private String refreshToken;
}

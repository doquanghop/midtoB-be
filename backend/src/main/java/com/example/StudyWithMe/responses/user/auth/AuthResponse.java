package com.example.StudyWithMe.responses.user.auth;

import com.example.StudyWithMe.responses.user.profile.ProfileDetailResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class AuthResponse {
    private Long userId;
    private List<String> roles;
    private TokenResponse token;
    private ProfileDetailResponse profile;
}

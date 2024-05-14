package com.example.StudyWithMe.controllers.user;

import com.example.StudyWithMe.dataTransferObjects.user.profile.UpdateProfileDTO;
import com.example.StudyWithMe.models.user.auth.User;
import com.example.StudyWithMe.models.user.profile.Profile;
import com.example.StudyWithMe.responses.ResponseObject;
import com.example.StudyWithMe.responses.user.profile.ProfileDetailResponse;
import com.example.StudyWithMe.services.user.auth.IAuthService;
import com.example.StudyWithMe.services.user.profile.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final IAuthService authService;
    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getProfile(
            @PathVariable Long userId
    ) throws JsonProcessingException{
        Profile profileResponse = userService.getProfile(userId);
        return ResponseEntity.ok(ResponseObject.builder()
                        .status(HttpStatus.OK)
                        .data(ProfileDetailResponse.fromProfile(profileResponse))
                .build());
    }
    @PutMapping("/updateProfile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> updateProfile(
            @RequestBody UpdateProfileDTO updateProfileDTO) throws IOException {
        User existingUser = authService.getCurrentUser();
        ProfileDetailResponse profileDetailResponse = userService.updateProfile(existingUser,updateProfileDTO);
        return ResponseEntity.ok((ResponseObject.success(HttpStatus.OK,
                "update profile successfully!",
                profileDetailResponse)));
    }
}

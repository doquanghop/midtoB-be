package com.example.StudyWithMe.services.user.profile;

import com.example.StudyWithMe.dataTransferObjects.user.profile.ProfileDTO;
import com.example.StudyWithMe.dataTransferObjects.user.profile.UpdateProfileDTO;
import com.example.StudyWithMe.models.user.auth.User;
import com.example.StudyWithMe.models.user.profile.Profile;
import com.example.StudyWithMe.responses.user.profile.ProfileDetailResponse;

import java.io.IOException;

public interface IUserService {
    Profile createProfile(User existingUser, ProfileDTO profileDTO);
    ProfileDetailResponse updateProfile(User existingUser, UpdateProfileDTO updateProfileDTO) throws IOException;
    Profile getProfile(Long userId);
}

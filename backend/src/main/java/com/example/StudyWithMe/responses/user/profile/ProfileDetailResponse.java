package com.example.StudyWithMe.responses.user.profile;
import com.example.StudyWithMe.models.user.profile.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProfileDetailResponse {
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime dateOfBirth;
    private String avatar;
    private String banner;
    private String createdAt;
    public static ProfileDetailResponse fromProfile(Profile profile){
        return ProfileDetailResponse.builder()
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .gender(profile.getGender())
                .address(profile.getAddress())
                .dateOfBirth(profile.getDateOfBirth())
                .avatar(profile.getAvatar())
                .banner(profile.getBanner())
                .createdAt(profile.getCreatedAt()!=null ? profile.getCreatedAt().toString() : null)
                .build();
    }
}

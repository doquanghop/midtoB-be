package com.example.StudyWithMe.responses.user.profile;

import com.example.StudyWithMe.models.user.auth.Role;
import com.example.StudyWithMe.models.user.auth.User;
import com.example.StudyWithMe.models.user.profile.Profile;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UserResponse {
    private Long userId;
    private String userName;
    private String email;
    private String phoneNumber;
    private List<String> roles;
    private Double balance;
    private String currency;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDateTime dateOfBirth;
    private String address;
    private String avatar;
    private String banner;
    public static UserResponse fromUser(User user, Profile profile){
        return UserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .firstName(profile!= null ? profile.getFirstName() : null)
                .lastName(profile!= null ? profile.getLastName() : null)
                .gender(profile!= null ? profile.getGender() : null)
                .dateOfBirth(profile!= null ? profile.getDateOfBirth() : null)
                .address(profile!= null ? profile.getAddress() : null)
                .avatar(profile!= null ? profile.getAvatar() : null)
                .banner(profile!= null ? profile.getBanner() : null)
                .build();
    }
}

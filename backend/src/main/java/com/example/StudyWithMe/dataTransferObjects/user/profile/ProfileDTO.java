package com.example.StudyWithMe.dataTransferObjects.user.profile;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder
public class ProfileDTO {
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private LocalDateTime dateOfBirth;
    private MultipartFile avatar;
    private MultipartFile banner;

}

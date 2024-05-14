package com.example.StudyWithMe.dataTransferObjects.user.profile;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateProfileDTO  {
    private Long userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateOfBirth;
    private String avatar;
    private String banner;
}

package com.example.StudyWithMe.models.user.profile;

import com.example.StudyWithMe.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name="profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile extends BaseEntity {
    @Id
    @Column(name = "profile_id")
    private Long profileId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "address")
    private String address;
    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "banner")
    private String banner;
}

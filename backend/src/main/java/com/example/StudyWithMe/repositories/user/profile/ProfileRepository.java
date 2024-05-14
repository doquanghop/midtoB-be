package com.example.StudyWithMe.repositories.user.profile;
import com.example.StudyWithMe.models.user.profile.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    @Query("SELECT p FROM Profile p")
    Page<Profile> findAllProfile(PageRequest pageRequest);
}

package com.example.StudyWithMe.repositories.socialmedia.message;

import com.example.StudyWithMe.models.socialmedia.message.RoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomMemberRepository extends JpaRepository<RoomMember, Long> {
    @Query("SELECT rm FROM RoomMember rm WHERE rm.user.id = :userId")
    List<RoomMember> findRoomMembersByUserId(@Param("userId") Long userId);
}
package com.example.StudyWithMe.repositories.socialmedia.message;

import com.example.StudyWithMe.models.socialmedia.message.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query("SELECT m FROM Message m WHERE m.room.roomId = :roomId")
    List<Message> getAllMessage(Long roomId);
    @Query("SELECT m FROM Message m WHERE m.room.roomId = :roomId")
    Page<Message> getAllMessage(Long roomId, PageRequest pageRequest);
    @Query("SELECT m FROM Message m WHERE m.room.roomId = :roomId ORDER BY m.createdAt DESC")
    Page<Message> findLatestMessageByRoomId(Long roomId, Pageable pageable);
}

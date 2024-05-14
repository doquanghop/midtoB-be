package com.example.StudyWithMe.repositories.socialmedia.message;

import com.example.StudyWithMe.models.socialmedia.message.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
}

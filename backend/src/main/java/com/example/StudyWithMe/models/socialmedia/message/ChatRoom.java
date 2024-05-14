package com.example.StudyWithMe.models.socialmedia.message;

import com.example.StudyWithMe.models.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chat_rooms")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChatRoom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;
    @Column(name = "room_name", nullable = false)
    private String roomName;
}
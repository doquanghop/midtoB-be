package com.example.StudyWithMe.models.socialmedia.message;
import com.example.StudyWithMe.models.user.auth.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChatRoom room;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
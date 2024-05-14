package com.example.StudyWithMe.responses.socialmedia.chat;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MessageResponse {
    private Long senderId;
    private String content;
    private LocalDateTime createdAt;
}

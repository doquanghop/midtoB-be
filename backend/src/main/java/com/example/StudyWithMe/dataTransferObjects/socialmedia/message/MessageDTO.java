package com.example.StudyWithMe.dataTransferObjects.socialmedia.message;

import lombok.Data;

import java.util.List;

@Data
public class MessageDTO {
    private Long senderId;
    private String content;
    private List<String> attachments;
}

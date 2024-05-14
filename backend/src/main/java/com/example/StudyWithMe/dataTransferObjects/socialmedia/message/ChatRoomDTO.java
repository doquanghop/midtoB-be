package com.example.StudyWithMe.dataTransferObjects.socialmedia.message;

import lombok.Data;

import java.util.List;

@Data
public class ChatRoomDTO {
    private String roomName;
    private List<Long> memberId;
}

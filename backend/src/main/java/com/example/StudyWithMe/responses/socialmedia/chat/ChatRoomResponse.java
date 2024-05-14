package com.example.StudyWithMe.responses.socialmedia.chat;

import com.example.StudyWithMe.models.socialmedia.message.ChatRoom;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ChatRoomResponse {
    private Long roomId;
    private String roomName;
    private String avatar;
    private MessageResponse lastMessage;
    public static ChatRoomResponse fromChatRoom(ChatRoom chatRoom){
        return ChatRoomResponse.builder()
                .roomId(chatRoom.getRoomId())
                .roomName(chatRoom.getRoomName())
                .build();
    }
}

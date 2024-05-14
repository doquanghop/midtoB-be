package com.example.StudyWithMe.services.socialmedia.message;

import com.example.StudyWithMe.dataTransferObjects.socialmedia.message.ChatRoomDTO;
import com.example.StudyWithMe.models.socialmedia.message.ChatRoom;
import com.example.StudyWithMe.responses.socialmedia.chat.ChatRoomResponse;

import java.util.List;

public interface IChatRoomService {
    ChatRoom  createChatRoom(ChatRoomDTO chatRoomDTO);

    void  addRoomMember(Long roomId, Long userId);
    List<ChatRoomResponse> getAllChatRooms();
    ChatRoom getChatRoom(Long roomId);
}

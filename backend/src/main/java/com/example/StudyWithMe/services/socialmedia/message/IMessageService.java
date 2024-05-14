package com.example.StudyWithMe.services.socialmedia.message;

import com.example.StudyWithMe.dataTransferObjects.socialmedia.message.MessageDTO;
import com.example.StudyWithMe.models.socialmedia.message.ChatRoom;
import com.example.StudyWithMe.models.user.auth.User;
import com.example.StudyWithMe.responses.socialmedia.chat.ListMessageResponse;
import com.example.StudyWithMe.responses.socialmedia.chat.MessageResponse;

import java.util.List;

public interface IMessageService {
    MessageResponse sendMessage(User sender,ChatRoom room, MessageDTO messageDTO);
    ListMessageResponse getAllMessage(Long roomId,int page,int limit);
    MessageResponse getLastMessage(Long roomId);
}

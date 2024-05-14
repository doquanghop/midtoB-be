package com.example.StudyWithMe.controllers.socailmedia.chat;

import com.example.StudyWithMe.dataTransferObjects.socialmedia.message.MessageDTO;
import com.example.StudyWithMe.models.socialmedia.message.ChatRoom;
import com.example.StudyWithMe.models.user.auth.User;
import com.example.StudyWithMe.responses.socialmedia.chat.MessageResponse;
import com.example.StudyWithMe.services.socialmedia.message.IChatRoomService;
import com.example.StudyWithMe.services.socialmedia.message.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatWSController {
    private final IMessageService messageService;
    private final IChatRoomService chatRoomService;
    @MessageMapping("/send/message/{roomId}")
    @SendTo("/chatRoom/{roomId}")
    public MessageResponse sendMessage(@Payload MessageDTO messageDTO,
                                       @DestinationVariable("roomId") Long roomId,
                                       @Header("sender") User currentUser) {
        System.out.println("senderId: "+currentUser.getUserId());
        ChatRoom chatRoom = chatRoomService.getChatRoom(roomId);
        MessageResponse newMessage = messageService.sendMessage(currentUser,chatRoom,messageDTO);
        return newMessage;
    }
}

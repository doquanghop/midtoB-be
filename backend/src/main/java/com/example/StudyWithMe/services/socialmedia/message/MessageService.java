package com.example.StudyWithMe.services.socialmedia.message;

import com.example.StudyWithMe.dataTransferObjects.socialmedia.message.MessageDTO;
import com.example.StudyWithMe.models.socialmedia.message.ChatRoom;
import com.example.StudyWithMe.models.socialmedia.message.Message;
import com.example.StudyWithMe.models.user.auth.User;
import com.example.StudyWithMe.models.user.profile.Profile;
import com.example.StudyWithMe.repositories.socialmedia.message.MessageRepository;
import com.example.StudyWithMe.responses.socialmedia.chat.ListMessageResponse;
import com.example.StudyWithMe.responses.socialmedia.chat.MessageResponse;
import com.example.StudyWithMe.services.user.auth.IAuthService;
import com.example.StudyWithMe.services.user.profile.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService{
    private final MessageRepository messageRepository;
    private final IUserService userService;
    @Override
    @Transactional
    public MessageResponse sendMessage(User sender,ChatRoom room, MessageDTO messageDTO){
        Message newMessage = Message.builder()
                .room(room)
                .user(sender)
                .content(messageDTO.getContent())
                .build();
        messageRepository.save(newMessage);
        return MessageResponse.builder()
                .senderId(sender.getUserId())
                .content(newMessage.getContent())
                .createdAt(newMessage.getCreatedAt())
                .build();
    }
    @Override
    @Transactional
    public ListMessageResponse getAllMessage(Long roomId, int page, int limit){
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Message> messagePage = messageRepository.getAllMessage(roomId,pageRequest);

        List<MessageResponse> messageList = messagePage
                .stream().map(message -> {
                    System.out.println(message);
                    return MessageResponse.builder()
                            .senderId(message.getUser().getUserId())
                            .content(message.getContent())
                            .createdAt(message.getCreatedAt())
                            .build();
                }).collect(Collectors.toList());
        return ListMessageResponse.builder()
                .totalPage(messagePage.getTotalPages())
                .messages(messageList)
                .build();
    }
    public MessageResponse getLastMessage(Long roomId){
        Page<Message> messages = messageRepository.findLatestMessageByRoomId(roomId, PageRequest.of(0, 1));
        if (messages != null && !messages.isEmpty()) {
            Message lastMessage = messages.getContent().get(0);
            return MessageResponse.builder()
                    .senderId(lastMessage.getUser().getUserId())
                    .content(lastMessage.getContent())
                    .createdAt(lastMessage.getCreatedAt())
                    .build();
        };
        return MessageResponse.builder()
                .senderId(null)
                .content("")
                .build();
    }
}

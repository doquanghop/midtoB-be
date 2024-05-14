package com.example.StudyWithMe.services.socialmedia.message;

import com.example.StudyWithMe.dataTransferObjects.socialmedia.message.ChatRoomDTO;
import com.example.StudyWithMe.exceptions.DataNotFoundException;
import com.example.StudyWithMe.models.socialmedia.message.ChatRoom;
import com.example.StudyWithMe.models.socialmedia.message.RoomMember;
import com.example.StudyWithMe.models.socialmedia.message.RoomMemberId;
import com.example.StudyWithMe.models.user.auth.User;
import com.example.StudyWithMe.repositories.socialmedia.message.ChatRoomRepository;
import com.example.StudyWithMe.repositories.socialmedia.message.RoomMemberRepository;
import com.example.StudyWithMe.responses.socialmedia.chat.ChatRoomResponse;
import com.example.StudyWithMe.responses.socialmedia.chat.MessageResponse;
import com.example.StudyWithMe.services.user.auth.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ChatRoomService implements IChatRoomService{
    private final ChatRoomRepository chatRoomRepository;

    private final RoomMemberRepository roomMemberRepository;

    private final IAuthService authService;
    private final IMessageService messageService;
    @Override
    @Transactional
    public ChatRoom createChatRoom(ChatRoomDTO chatRoomDTO) {
        ChatRoom newChatRoom = ChatRoom.builder()
                .roomName(chatRoomDTO.getRoomName())
                .build();
        chatRoomRepository.save(newChatRoom);
        chatRoomDTO.getMemberId().stream().map(memberId -> {
          User existingUser = authService.getUserByUserId(memberId);
          RoomMemberId roomMemberId = RoomMemberId.builder()
                    .roomId(newChatRoom.getRoomId())
                    .userId(existingUser.getUserId())
                    .build();
          RoomMember newMember = RoomMember.builder()
                  .roomMemberId(roomMemberId)
                  .room(newChatRoom)
                  .user(existingUser)
                  .build();
          return roomMemberRepository.save(newMember);
        }).collect(Collectors.toList());
        return newChatRoom;
    }
    @Transactional
    @Override
    public void addRoomMember(Long roomId, Long userId) {
        ChatRoom existingRoom = chatRoomRepository.findById(roomId)
                .orElse(null);
        User user = authService.getUserByUserId(userId);
        RoomMember roomMember = RoomMember.builder()
                .room(existingRoom)
                .user(user)
                .build();
        roomMemberRepository.save(roomMember);
    }
    @Override
    public List<ChatRoomResponse> getAllChatRooms() {
        User currentUser = authService.getCurrentUser();
        List<ChatRoom> chatRooms = new ArrayList<>();
        List<RoomMember> roomMembers = roomMemberRepository.findRoomMembersByUserId(currentUser.getUserId());
        for (RoomMember roomMember : roomMembers) {
            chatRooms.add(roomMember.getRoom());
        }
        List<ChatRoomResponse> chatRoomResponses = chatRooms
                .stream().map(chatRoom -> {
                    MessageResponse lastMessage = messageService.getLastMessage(chatRoom.getRoomId());
                    return ChatRoomResponse.builder()
                            .roomId(chatRoom.getRoomId())
                            .roomName(chatRoom.getRoomName())
                            .lastMessage(lastMessage)
                            .build();
                }).collect(Collectors.toList());
        for(int i=0;i<chatRoomResponses.size();i++){
            for (int j=i+1;j<chatRoomResponses.size();j++){
                if (chatRoomResponses.get(i).getLastMessage().getCreatedAt().isBefore(
                        chatRoomResponses.get(j).getLastMessage().getCreatedAt())){
                    ChatRoomResponse temp = chatRoomResponses.get(i);
                    chatRoomResponses.set(i,chatRoomResponses.get(j));
                    chatRoomResponses.set(j,temp);
                }
            }
        }
        return chatRoomResponses;
    }
    @Override
    public ChatRoom getChatRoom(Long roomId){
        return chatRoomRepository.findById(roomId)
                .orElseThrow(()->new DataNotFoundException(""));
    }
}
package com.example.StudyWithMe.controllers.socailmedia.chat;

import com.example.StudyWithMe.dataTransferObjects.socialmedia.message.ChatRoomDTO;
import com.example.StudyWithMe.models.socialmedia.message.ChatRoom;
import com.example.StudyWithMe.responses.ResponseObject;
import com.example.StudyWithMe.responses.socialmedia.chat.ChatRoomResponse;
import com.example.StudyWithMe.responses.socialmedia.chat.ListMessageResponse;
import com.example.StudyWithMe.services.socialmedia.message.IChatRoomService;
import com.example.StudyWithMe.services.socialmedia.message.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/${api.prefix}/chat")
@RequiredArgsConstructor
public class ChatController {
    private final IChatRoomService chatRoomService;
    private final IMessageService messageService;
    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createChatRoom(
            @RequestBody ChatRoomDTO chatRoomDTO
    ) {
        ChatRoom chatRoom = chatRoomService.createChatRoom(chatRoomDTO);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK,"" +
                "Create chat room successfully!", ChatRoomResponse.fromChatRoom(chatRoom)));
    }
    @GetMapping("/chatRoom")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getAllChatRooms() {
        List<ChatRoomResponse> chatRooms = chatRoomService.getAllChatRooms();
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK,"" +
                "Get all chat room successfully!",chatRooms));
    }
    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getAllMessage(
            @RequestParam Long roomId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        ListMessageResponse messageResponses = messageService.getAllMessage(roomId, page, limit);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "" +
                "Get all message successfully!", messageResponses));
    }
}

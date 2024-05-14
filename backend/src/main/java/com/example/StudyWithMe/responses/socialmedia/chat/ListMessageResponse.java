package com.example.StudyWithMe.responses.socialmedia.chat;

import lombok.Builder;
import lombok.Data;
import java.util.List;
@Data
@Builder
public class ListMessageResponse {
    private int totalPage;
    private List<MessageResponse> messages;
}

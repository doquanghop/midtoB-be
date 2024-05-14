package com.example.StudyWithMe.responses.socialmedia.comment;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class ListCommentResponse {
    private int totalPages;
    private List<CommentResponse> comments;
}

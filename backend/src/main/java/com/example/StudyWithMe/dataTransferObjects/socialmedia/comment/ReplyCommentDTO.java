package com.example.StudyWithMe.dataTransferObjects.socialmedia.comment;

import jakarta.validation.constraints.Max;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Getter
public class ReplyCommentDTO {
    private Long commentId;
    private String content;
    @Max(value = 5)
    private List<MultipartFile> attachments;
}

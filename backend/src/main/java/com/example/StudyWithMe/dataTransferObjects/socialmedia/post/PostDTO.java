package com.example.StudyWithMe.dataTransferObjects.socialmedia.post;

import jakarta.validation.constraints.Max;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Data
public class PostDTO {
    private String content;
    @Max(value = 5)
    private List<MultipartFile> attachments;
}

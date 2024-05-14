package com.example.StudyWithMe.dataTransferObjects.exercise;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Getter
public class QuestionDTO {

    private Long subjectId;
    private String title;
    private String content;
    @Max(value = 5)
    private List<MultipartFile> attachments;

}

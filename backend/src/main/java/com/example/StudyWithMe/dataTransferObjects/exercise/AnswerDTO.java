package com.example.StudyWithMe.dataTransferObjects.exercise;

import jakarta.validation.constraints.Max;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Getter
public class AnswerDTO {

    private Long questionId;
    private String content;
    @Max(value = 5)
    private List<MultipartFile> attachments;

}

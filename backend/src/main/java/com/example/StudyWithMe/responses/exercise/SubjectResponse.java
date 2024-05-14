package com.example.StudyWithMe.responses.exercise;

import com.example.StudyWithMe.models.exercise.subject.Subject;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubjectResponse {

    private Long subjectId;
    private String subjectName;

    public static SubjectResponse fromSubject(Subject subject) {
        return SubjectResponse.builder()
                .subjectId(subject.getSubjectId())
                .subjectName(subject.getSubjectName())
                .build();
    }
}

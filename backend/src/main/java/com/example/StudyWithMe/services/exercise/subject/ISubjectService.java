package com.example.StudyWithMe.services.exercise.subject;

import com.example.StudyWithMe.dataTransferObjects.exercise.SubjectDTO;
import com.example.StudyWithMe.models.exercise.subject.Subject;
import com.example.StudyWithMe.responses.exercise.SubjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ISubjectService {

    SubjectResponse createSubject(SubjectDTO subjectDTO);
    Subject findById(Long subjectId);
    Page<SubjectResponse> getAllSubject(PageRequest pageRequest);
}

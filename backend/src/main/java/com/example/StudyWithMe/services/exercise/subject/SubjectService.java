package com.example.StudyWithMe.services.exercise.subject;

import com.example.StudyWithMe.dataTransferObjects.exercise.SubjectDTO;
import com.example.StudyWithMe.exceptions.DataNotFoundException;
import com.example.StudyWithMe.models.exercise.subject.Subject;
import com.example.StudyWithMe.repositories.exercise.subject.SubjectRepository;
import com.example.StudyWithMe.responses.exercise.SubjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService implements ISubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public SubjectResponse createSubject(SubjectDTO subjectDTO) {
        Subject subject = Subject.builder()
                .subjectName(subjectDTO.getSubjectName())
                .build();
        subjectRepository.save(subject);
        return SubjectResponse.fromSubject(subject);
    }

    @Override
    public Subject findById(Long subjectId) {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new DataNotFoundException("Subject not found"));
    }

    @Override
    public Page<SubjectResponse> getAllSubject(PageRequest pageRequest) {
        Page<Subject> subjectPage = subjectRepository.findAll(pageRequest);
        List<SubjectResponse> subjectResponses = subjectPage.map(SubjectResponse::fromSubject).getContent();

        return new PageImpl<>(subjectResponses, subjectPage.getPageable(), subjectPage.getTotalElements());
    }
}

package com.example.StudyWithMe.services.study.category;

import com.example.StudyWithMe.dataTransferObjects.study.SubjectDTO;
import com.example.StudyWithMe.models.study.category.Category;
import com.example.StudyWithMe.responses.study.SubjectResponse;
import com.example.StudyWithMe.responses.study.category.ListCategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ICategoryService {

    SubjectResponse createSubject(SubjectDTO subjectDTO);
    Category findById(Long subjectId);
    Page<SubjectResponse> getAllSubject(PageRequest pageRequest);
}

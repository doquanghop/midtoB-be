package com.example.StudyWithMe.services.study.category;

import com.example.StudyWithMe.dataTransferObjects.study.SubjectDTO;
import com.example.StudyWithMe.exceptions.DataNotFoundException;
import com.example.StudyWithMe.models.study.category.Category;
import com.example.StudyWithMe.repositories.study.category.CategoryRepository;
import com.example.StudyWithMe.responses.study.SubjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public SubjectResponse createSubject(SubjectDTO subjectDTO) {
        Category category = Category.builder()
                .subjectName(subjectDTO.getSubjectName())
                .build();
        categoryRepository.save(category);
        return SubjectResponse.fromSubject(category);
    }

    @Override
    public Category findById(Long subjectId) {
        return categoryRepository.findById(subjectId)
                .orElseThrow(() -> new DataNotFoundException("Subject not found"));
    }

    @Override
    public Page<SubjectResponse> getAllSubject(PageRequest pageRequest) {
        Page<Category> subjectPage = categoryRepository.findAll(pageRequest);
        List<SubjectResponse> subjectResponses = subjectPage.map(SubjectResponse::fromSubject).getContent();

        return new PageImpl<>(subjectResponses, subjectPage.getPageable(), subjectPage.getTotalElements());
    }
}

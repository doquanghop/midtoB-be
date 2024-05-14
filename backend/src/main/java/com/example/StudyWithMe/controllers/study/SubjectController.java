package com.example.StudyWithMe.controllers.exercise;

import com.example.StudyWithMe.dataTransferObjects.exercise.SubjectDTO;
import com.example.StudyWithMe.responses.ResponseObject;
import com.example.StudyWithMe.responses.exercise.SubjectResponse;
import com.example.StudyWithMe.services.exercise.subject.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/${api.prefix}/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_SCHOOL') or hasRole('ROLE_TEACHER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createSubject(@RequestBody SubjectDTO subjectDTO) {
        SubjectResponse newSubject = subjectService.createSubject(subjectDTO);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Create subject successfully", newSubject));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllSubjects(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<SubjectResponse> subjectResponses = subjectService.getAllSubject(pageRequest);
        return ResponseEntity.ok(ResponseObject.success(HttpStatus.OK, "Get all subjects", subjectResponses));
    }
}

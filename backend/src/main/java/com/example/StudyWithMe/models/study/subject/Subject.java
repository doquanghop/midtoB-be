package com.example.StudyWithMe.models.exercise.subject;

import com.example.StudyWithMe.models.exercise.question.Question;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "subjects")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long subjectId;

    @NotNull
    @Column(name = "subject_name")
    private String subjectName;

    @OneToMany(mappedBy = "subject")
    private List<Question> questions;

}

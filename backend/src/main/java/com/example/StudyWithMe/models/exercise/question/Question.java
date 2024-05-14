package com.example.StudyWithMe.models.exercise.question;

import com.example.StudyWithMe.models.BaseEntity;
import com.example.StudyWithMe.models.exercise.subject.Subject;
import com.example.StudyWithMe.models.exercise.answer.Answer;
import com.example.StudyWithMe.models.user.auth.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "questions")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content", length = 65536)
    private String content;

//    @Column(name = "tags")
//    private String tags;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    @OneToMany(mappedBy = "question")
    private List<QuestionAttachment> attachments;

}

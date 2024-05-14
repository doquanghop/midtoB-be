package com.example.StudyWithMe.models.exercise.answer;

import com.example.StudyWithMe.models.BaseEntity;
import com.example.StudyWithMe.models.exercise.question.Question;
import com.example.StudyWithMe.models.user.auth.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Table(name = "answers")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long answerId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Lob
    @Column(name = "content", length = 65536)
    private String content;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "rating")
    private int rating;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "solution")
    private boolean solution;

    @OneToMany(mappedBy = "answer")
    private List<AnswerAttachment> attachments;

}

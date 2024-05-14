package com.example.StudyWithMe.services.exercise.question;

import com.example.StudyWithMe.dataTransferObjects.exercise.QuestionDTO;
import com.example.StudyWithMe.exceptions.DataNotFoundException;
import com.example.StudyWithMe.models.exercise.question.Question;
import com.example.StudyWithMe.models.exercise.question.QuestionAttachment;
import com.example.StudyWithMe.models.exercise.subject.Subject;
import com.example.StudyWithMe.models.user.auth.User;
import com.example.StudyWithMe.models.user.profile.Profile;
import com.example.StudyWithMe.repositories.exercise.answer.AnswerRepository;
import com.example.StudyWithMe.repositories.exercise.question.QuestionAttachmentRepository;
import com.example.StudyWithMe.repositories.exercise.question.QuestionRepository;
import com.example.StudyWithMe.responses.exercise.QuestionResponse;
import com.example.StudyWithMe.responses.user.profile.UserResponse;
import com.example.StudyWithMe.services.attachment.IAttachmentService;
import com.example.StudyWithMe.services.user.auth.IAuthService;
import com.example.StudyWithMe.services.user.profile.IUserService;
import com.example.StudyWithMe.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final QuestionAttachmentRepository attachmentRepository;
    private final IAttachmentService attachmentService;
    private final IAuthService authService;
    private final IUserService userService;

    @Override
    public QuestionResponse createQuestion(QuestionDTO questionDTO, Subject subject) {
        User asker = authService.getCurrentUser();
        Question question = Question.builder()
                .subject(subject)
                .user(asker)
                .title(questionDTO.getTitle())
                .content(questionDTO.getContent())
                .attachments(new ArrayList<>())
                .build();
        questionRepository.save(question);

        if (questionDTO.getAttachments() != null && !questionDTO.getAttachments().isEmpty()) {
            for (MultipartFile file : questionDTO.getAttachments()){
                String url = attachmentService.upload(file);
                QuestionAttachment attachment = QuestionAttachment.builder()
                        .question(question)
                        .fileType(Utils.getFileType(file.getContentType()))
                        .fileUrl(url)
                        .build();
                question.getAttachments().add(attachment);
                attachmentRepository.save(attachment);
            }
        }
        Profile profile = userService.getProfile(asker.getUserId());
        return QuestionResponse.fromQuestion(UserResponse.fromUser(asker, profile), question);
    }

    @Override
    public Page<QuestionResponse> getAllQuestion(PageRequest pageRequest) {
        Page<Question> questionPage = questionRepository.findAll(pageRequest);
        List<QuestionResponse> questionResponses = questionPage.map(question -> {
            User asker = authService.getUserByUserId(question.getUser().getUserId());
            Profile profile = userService.getProfile(asker.getUserId());
            return QuestionResponse.fromQuestion(UserResponse.fromUser(asker, profile), question);
        }).getContent();

        return new PageImpl<>(questionResponses, questionPage.getPageable(), questionPage.getTotalPages());
    }

    @Override
    public Question getQuestionDetail(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new DataNotFoundException("Question not found"));
    }

    @Override
    public Page<QuestionResponse> getQuestionsByUserId(Long userId, PageRequest pageRequest) {
        Page<Question> questionPage = questionRepository.findByUserId(userId, pageRequest);
        List<QuestionResponse> questionResponses = questionPage.map(question -> {
            User asker = authService.getUserByUserId(question.getUser().getUserId());
            Profile profile = userService.getProfile(asker.getUserId());
            return QuestionResponse.fromQuestion(UserResponse.fromUser(asker, profile), question);
        }).getContent();

        return new PageImpl<>(questionResponses, questionPage.getPageable(), questionPage.getTotalPages());
    }

    @Override
    public Page<QuestionResponse> getQuestionsBySubjectId(Long subjectId, PageRequest pageRequest) {
        Page<Question> questionPage = questionRepository.findBySubjectId(subjectId, pageRequest);
        List<QuestionResponse> questionResponses = questionPage.map(question -> {
            User asker = authService.getUserByUserId(question.getUser().getUserId());
            Profile profile = userService.getProfile(asker.getUserId());
            return QuestionResponse.fromQuestion(UserResponse.fromUser(asker, profile), question);
        }).getContent();

        return new PageImpl<>(questionResponses, questionPage.getPageable(), questionPage.getTotalPages());
    }

    @Override
    public void deleteQuestion(Long questionId) {
        attachmentRepository.deleteByQuestionId(questionId);
        answerRepository.deleteByQuestionId(questionId);
        questionRepository.deleteById(questionId);
    }
}

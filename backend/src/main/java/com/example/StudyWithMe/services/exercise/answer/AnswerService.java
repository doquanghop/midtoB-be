package com.example.StudyWithMe.services.exercise.answer;

import com.example.StudyWithMe.dataTransferObjects.exercise.AnswerDTO;
import com.example.StudyWithMe.models.exercise.answer.Answer;
import com.example.StudyWithMe.models.exercise.answer.AnswerAttachment;
import com.example.StudyWithMe.models.exercise.question.Question;
import com.example.StudyWithMe.models.user.auth.User;
import com.example.StudyWithMe.repositories.exercise.answer.AnswerAttachmentRepository;
import com.example.StudyWithMe.repositories.exercise.answer.AnswerRepository;
import com.example.StudyWithMe.responses.exercise.AnswerResponse;
import com.example.StudyWithMe.services.attachment.IAttachmentService;
import com.example.StudyWithMe.services.user.auth.IAuthService;
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
public class AnswerService implements IAnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerAttachmentRepository attachmentRepository;
    private final IAuthService authService;
    private final IAttachmentService attachmentService;

    @Override
    public AnswerResponse createAnswer(AnswerDTO answerDTO, Question question) {
        User answerer = authService.getCurrentUser();
        Answer answer = Answer.builder()
                .user(answerer)
                .question(question)
                .content(answerDTO.getContent())
                .attachments(new ArrayList<>())
                .build();
        answerRepository.save(answer);

        if (answerDTO.getAttachments() != null && !answerDTO.getAttachments().isEmpty()) {
            for (MultipartFile file : answerDTO.getAttachments()){
                String url = attachmentService.upload(file);
                AnswerAttachment attachment = AnswerAttachment.builder()
                        .answer(answer)
                        .fileType(Utils.getFileType(file.getContentType()))
                        .fileUrl(url)
                        .build();
                answer.getAttachments().add(attachment);
                attachmentRepository.save(attachment);
            }
        }

        return AnswerResponse.fromAnswer(answer);
    }

    @Override
    public Page<AnswerResponse> getAnswersByQuestionId(Long questionId, PageRequest pageRequest) {
        Page<Answer> answerPage = answerRepository.findByQuestionId(questionId, pageRequest);
        List<AnswerResponse> answerResponses = answerPage.map(AnswerResponse::fromAnswer).getContent();

        return new PageImpl<>(answerResponses, answerPage.getPageable(), answerPage.getTotalElements());
    }

    @Override
    public void deleteAnswer(Long answerId) {
        attachmentRepository.deleteByAnswerId(answerId);
        answerRepository.deleteById(answerId);
    }
}

package com.example.StudyWithMe.services.exercise.answer;

import com.example.StudyWithMe.exceptions.InvalidParamException;
import com.example.StudyWithMe.models.study.answer.AnswerScore;
import com.example.StudyWithMe.models.user.auth.User;
import com.example.StudyWithMe.repositories.exercise.answer.AnswerScoreRepository;
import com.example.StudyWithMe.responses.exercise.answer.AnswerScoreResponse;
import com.example.StudyWithMe.responses.exercise.answer.ListAnswerScoreResponse;
import com.example.StudyWithMe.services.user.auth.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnswerScoreService implements IAnswerScoreService{
    private final AnswerScoreRepository answerScoreRepository;
    private final IAuthService authService;
    @Override
    public AnswerScore createRateAnswer(Long answerId) {
        User currentUser = authService.getCurrentUser();
        AnswerScore newRateAnswer = AnswerScore.builder()
                .userId(currentUser.getUserId())
                .answerId(answerId)
                .score(0)
                .build();
        answerScoreRepository.save(newRateAnswer);
        return null;
    }
    @Override
    public ListAnswerScoreResponse getAllAnswerScore(Long answerId) {
        List<AnswerScore> answerScoreList = answerScoreRepository.getAllAnswerScore(answerId);
        int[] totalScore = {0};
        List<AnswerScoreResponse> answerScoreResponses = answerScoreList.stream().map(answerScore -> {
            totalScore[0] += answerScore.getScore();

            return AnswerScoreResponse.builder()
                    .userId(answerScore.getUserId())
                    .score(answerScore.getScore())
                    .build();
        }).toList();
        return ListAnswerScoreResponse.builder()
                .totalScore(totalScore[0])
                .scores(answerScoreResponses)
                .build();
    }

    @Override
    public void updateAnswerScore(Long answerId, int score) {
        if (score>=1 && score<=5) {
            answerScoreRepository.updateAnswerScore(answerId, score);
        } else {
            throw new InvalidParamException("Score must be between 1 and 5");
        }
    }
}

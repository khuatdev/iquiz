package swp391.quizpracticing.service;

import swp391.quizpracticing.model.QuizreviewQuestion;

import java.util.List;
import swp391.quizpracticing.dto.QuizReviewResponse;

public interface IQuizreviewQuestionService {

    public List<QuizreviewQuestion> getAllByQuizreviewId(Integer id);

    public Integer getNumberOfCorrectAnswerByQuizreviewId(Integer quizreviewId, Boolean isCorrect);
    
    public List<QuizReviewResponse> getAllByQuizreviewResponse(Integer id);
}

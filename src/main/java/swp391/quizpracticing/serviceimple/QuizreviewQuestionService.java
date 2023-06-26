package swp391.quizpracticing.serviceimple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.model.QuizreviewQuestion;
import swp391.quizpracticing.repository.IQuizreviewQuestionRepository;
import swp391.quizpracticing.service.IQuizreviewQuestionService;

import java.util.List;
import swp391.quizpracticing.dto.AnswerQuestionDTO;
import swp391.quizpracticing.dto.QuizReviewResponse;

@Service
public class QuizreviewQuestionService implements IQuizreviewQuestionService {

    @Autowired
    private IQuizreviewQuestionRepository iQuizreviewQuestionRepository;

    @Override
    public List<QuizreviewQuestion> getAllByQuizreviewId(Integer id) {
        return iQuizreviewQuestionRepository.getAllByQuizreviewId(id);
    }

    @Override
    public Integer getNumberOfCorrectAnswerByQuizreviewId(Integer quizreviewId, Boolean isCorrect) {
        return iQuizreviewQuestionRepository.getNumberOfCorrectAnswer(quizreviewId, isCorrect);
    }
    
    @Override
    public List<QuizReviewResponse> getAllByQuizreviewResponse(Integer id) {
        return iQuizreviewQuestionRepository.getAllByQuizreviewResponse(id);
    }
    
    public List<AnswerQuestionDTO> getAnswerByIdQuestion(Integer id) {
        return iQuizreviewQuestionRepository.getAnswerByIdQuestion(id);
    }
}

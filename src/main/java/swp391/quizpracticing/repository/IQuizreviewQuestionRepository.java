/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.QuizreviewQuestion;
import swp391.quizpracticing.model.QuizreviewQuestionKey;

import java.util.List;
import swp391.quizpracticing.dto.AnswerQuestionDTO;
import swp391.quizpracticing.dto.QuizReviewResponse;

/**
 *
 * @author Mosena
 */
@Repository
public interface IQuizreviewQuestionRepository extends JpaRepository<QuizreviewQuestion,QuizreviewQuestionKey> {

    @Query(value = "select * from iquiz.quizreview_question where quizreview_id = :quizreview_id", nativeQuery = true)
    public List<QuizreviewQuestion> getAllByQuizreviewId(@Param("quizreview_id") Integer quizreviewId);

    @Query(value = "select count(is_correct) from iquiz.quizreview_question where quizreview_id = :quizreview_id and is_correct = :is_correct", nativeQuery = true)
    public Integer getNumberOfCorrectAnswer(@Param("quizreview_id") Integer quizreviewId, @Param("is_correct") Boolean isCorrect);

    @Query(value = """
                   SELECT iquiz.question.*,
                     (SELECT bookmark FROM iquiz.quizreview_question WHERE question_id = iquiz.question.id AND quizreview_id = :id) AS bookmark,
  COALESCE(
                         (SELECT user_answer FROM iquiz.quizreview_question WHERE user_answer not IN 
                           (SELECT explanation FROM iquiz.answer
                           WHERE question_id = iquiz.question.id AND checking = 1)
                           AND question_id = iquiz.question.id AND quizreview_id = :id),
                         'true') AS checking
                   FROM iquiz.question
                   WHERE id IN (
                     SELECT question_id FROM iquiz.lesson_question WHERE
                     lesson_id = (SELECT lesson_id FROM iquiz.quizreview WHERE id = :id AND user_id = 10)
                   ) order by id;
                   """, nativeQuery = true)
    public List<QuizReviewResponse> getAllByQuizreviewResponse(@Param("id") Integer id);
    
    @Query(value = """
                  select * from iquiz.answer where question_id =:id
                   """, nativeQuery = true)
    public List<AnswerQuestionDTO> getAnswerByIdQuestion(@Param("id") Integer id);
    
}

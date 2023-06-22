/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.service;

import swp391.quizpracticing.model.Question;

import java.util.List;

/**
 *
 * @author Mosena
 */
public interface IQuestionService {
    public Question getQuestionsById(Integer id);
    public List<Question> getBySubCategory(Integer id);
    public List<Question> getRandomBySubCategories(Integer id, Integer number);
    public void save(Question q);
    public List<Question> getQuestionByLessonAndSub(Integer lessonId, Integer subCategoryId);
}

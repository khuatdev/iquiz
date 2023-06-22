/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.QuestionDTO;
import swp391.quizpracticing.model.Question;
import swp391.quizpracticing.repository.IQuestionRepository;
import swp391.quizpracticing.service.IQuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Mosena
 */
@Service
public class QuestionService implements IQuestionService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IQuestionRepository iQuestionRepository;
    
    private QuestionDTO convertEntityToDTO(Question entity){
        return modelMapper.map(entity,QuestionDTO.class);
    }

    @Override
    public Question getQuestionsById(Integer id) {
        return iQuestionRepository.getQuestionsById(id);
    }

    @Override
    public List<Question> getBySubCategory(Integer id) {
        return iQuestionRepository.findBySubCategories_Id(id);
    }

    @Override
    public List<Question> getRandomBySubCategories(Integer id, Integer number){
        List<Question> questions = new ArrayList<>();
        List<Question> randomQues= iQuestionRepository.findBySubCategories_Id(id);
        for (int i=0; i<number; i++){
            Random random = new Random();

            int randomNumber = random.nextInt(randomQues.size()-1);
            System.out.println(randomNumber);
            questions.add(randomQues.get(0));
        }

        return questions;
    }


    @Override
    public void save(Question q) {
        iQuestionRepository.save(q);
    }

    @Override
    public List<Question> getQuestionByLessonAndSub(Integer lessonId, Integer subCategoryId) {
        List<Question> q = new ArrayList<>();
        List<Question> questionLessonList=iQuestionRepository.findByLessons_Id(lessonId);
        List<Question> questionSubList=iQuestionRepository.findBySubCategories_Id(subCategoryId);
        for (Question qles: questionLessonList){
            for (Question question: questionSubList){
                if (Objects.equals(question.getId(), qles.getId()))
                    q.add(question);
            }
        }
        return q;
    }

}

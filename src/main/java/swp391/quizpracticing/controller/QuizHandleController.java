/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.quizpracticing.dto.AnswerDTO;
import swp391.quizpracticing.dto.LessonDTO;
import swp391.quizpracticing.dto.QuestionDTO;
import swp391.quizpracticing.service.ILessonService;

/**
 *
 * @author Lenovo
 */
@Controller
public class QuizHandleController {

    @Autowired
    ILessonService service;

    @GetMapping("/quiz-handle")
    public String quizShow(@RequestParam Map<String, String> parameters, Model model) {
        int id = Integer.parseInt(parameters.get("id"));
        int qid = Integer.parseInt(parameters.get("qid"));
        LessonDTO lesson = service.findById(id);
        List<QuestionDTO> questions = lesson.getQuestions();
        int index = 0;
        int count = 0;
        for (QuestionDTO question : questions) {

            if (count == qid) {
                model.addAttribute("id", id);
                model.addAttribute("qid", qid);
                model.addAttribute("currentQuestion", question);
                model.addAttribute("currentAnswer", question.getAnswer());
                model.addAttribute("size", questions.size() - 1);
                
                break;
            }
            count++;

        }

        return "customer/quizhandle";
    }
}

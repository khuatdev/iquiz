/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import swp391.quizpracticing.dto.AnswerQuestionDTO;
import swp391.quizpracticing.dto.QuizReviewResponse;
import swp391.quizpracticing.model.Answer;
import swp391.quizpracticing.model.QuizreviewQuestionKey;
import swp391.quizpracticing.service.IAnswerService;
import swp391.quizpracticing.service.ILessonService;
import swp391.quizpracticing.service.IQuestionService;
import swp391.quizpracticing.service.IQuizreviewQuestionService;
import swp391.quizpracticing.service.IQuizreviewService;
import swp391.quizpracticing.serviceimple.QuizreviewQuestionService;

/**
 *
 * @author Admin
 */
@Controller
public class QuizReviewController {
    
    @Autowired
    private IQuizreviewService iQuizreviewService;

    @Autowired
    private ILessonService iLessonService;

    @Autowired
    private IQuizreviewQuestionService iQuizreviewQuestionService;

    @Autowired
    private IQuestionService iQuestionService;

    @Autowired
    private IAnswerService iAnswerService;

    @Autowired
    private QuizreviewQuestionService quizreviewQuestionService;
    
    @GetMapping("/quiz_review")
    public String showQuizReviewForm(@Param(value = "id") int id, @Param(value = "index_question") int index_question, Model model) {
        List<QuizReviewResponse> x = quizreviewQuestionService.getAllByQuizreviewResponse(id);
        List<AnswerQuestionDTO> a = quizreviewQuestionService.getAnswerByIdQuestion(x.get(index_question).getId());
        if (index_question == 0) {
            model.addAttribute("back", 0);
        } else {
            model.addAttribute("back", 1);
        }
        if (index_question + 1 == x.size()) {
            model.addAttribute("next", 0);
        } else {
            model.addAttribute("next", 1);
        }
        model.addAttribute("id", id);
        model.addAttribute("all", x.get(index_question));
        model.addAttribute("answer", a);
        return "customer/quiz_review_form";
    }

    @GetMapping("/quiz_review_result")
    public String showQuizReviewResultForm(@Param(value = "id") int id, Model model, @Param(value = "type") String type) {
        if (type == null) {
            List<QuizReviewResponse> x = quizreviewQuestionService.getAllByQuizreviewResponse(id);
            model.addAttribute("listQuestion", x);
            model.addAttribute("id", id);
            return "customer/quiz_review_result_form";
        } else if (type.equals("correct")) {
            List<QuizReviewResponse> x = quizreviewQuestionService.getAllByQuizreviewResponse(id);
            List<QuizReviewResponse> responses = new ArrayList<>();
            for (QuizReviewResponse quizReviewResponse : x) {
                System.out.println(quizReviewResponse.getChecking() + "checking");
                System.out.println(quizReviewResponse.getBookmark());
                if (quizReviewResponse.getChecking().equals("true")) {
                    responses.add(quizReviewResponse);
                }
            }
            model.addAttribute("listQuestion", responses);
            model.addAttribute("id", id);
            return "customer/quiz_review_result_form";
        } else if (type.equals("bookmark")) {
            List<QuizReviewResponse> x = quizreviewQuestionService.getAllByQuizreviewResponse(id);
            List<QuizReviewResponse> responses = new ArrayList<>();
            for (QuizReviewResponse quizReviewResponse : x) {
                System.out.println(quizReviewResponse.getChecking() + "checking");
                System.out.println(quizReviewResponse.getBookmark());
                if (quizReviewResponse.getBookmark() == 1) {
                    responses.add(quizReviewResponse);
                }
            }
            model.addAttribute("listQuestion", responses);
            model.addAttribute("id", id);
            return "customer/quiz_review_result_form";
        } else if (type.equals("incorrect")) {
            List<QuizReviewResponse> x = quizreviewQuestionService.getAllByQuizreviewResponse(id);
            List<QuizReviewResponse> responses = new ArrayList<>();
            for (QuizReviewResponse quizReviewResponse : x) {
                System.out.println(quizReviewResponse.getChecking() + "checking");
                System.out.println(quizReviewResponse.getBookmark());
                if (!quizReviewResponse.getChecking().equals("true")) {
                    responses.add(quizReviewResponse);
                }
            }
            model.addAttribute("listQuestion", responses);
            model.addAttribute("id", id);
            return "customer/quiz_review_result_form";
        } else if (type.equals("all")) {
           System.out.println(id + "???");
            List<QuizReviewResponse> x = quizreviewQuestionService.getAllByQuizreviewResponse(id);
            model.addAttribute("listQuestion", x);
            model.addAttribute("id", id);
            return "customer/quiz_review_result_form";
        } else {
            List<QuizReviewResponse> x = quizreviewQuestionService.getAllByQuizreviewResponse(id);
            model.addAttribute("listQuestion", x);
            model.addAttribute("id", id);
            return "customer/quiz_review_result_form";
        }
    }
    
}

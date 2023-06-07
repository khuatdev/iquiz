package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpRequest;

@Controller
public class PracticeDetailController {

    @GetMapping("/practice-detail")
    public String getToPracticeDetail(@RequestParam(name = "id") Integer id, Model model, HttpSession session) {
        model.addAttribute("id", id);
        return "practice_detail/practice_detail";
    }
}

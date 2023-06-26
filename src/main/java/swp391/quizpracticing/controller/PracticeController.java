package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import swp391.quizpracticing.model.Dimension;
import swp391.quizpracticing.model.Subcategory;
import swp391.quizpracticing.model.Subject;
import swp391.quizpracticing.model.User;
import swp391.quizpracticing.repository.IDimensionRepository;
import swp391.quizpracticing.repository.ISubcategoryRepository;
import swp391.quizpracticing.repository.ISubjectRepository;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/practice")
public class PracticeController {
    @Autowired
    private ISubjectRepository iSubjectRepository;

    @Autowired
    private ISubcategoryRepository iSubcategoryRepository;

    @Autowired
    private IDimensionRepository iDimensionRepository;
    @GetMapping("/detail")
    public String showFormPractice(@RequestParam(name = "id") Integer id, HttpSession session,HttpServletRequest request, Model model){
//        HttpSession session = request.getSession(false);
 //       User user = (User) session.getAttribute("user");
        List<Subject> list = iSubjectRepository.findAll();
        List<Subcategory> listSub = iSubcategoryRepository.findAll();
        List<Dimension> listDimension = iDimensionRepository.findAll();
        model.addAttribute("id", id);
        model.addAttribute("list",list);
        model.addAttribute("listSub",listSub);
        model.addAttribute("listDimension",listDimension);
        model.addAttribute("userSession", session.getAttribute("user"));
        return "practice_detail/PracticeDetail";
    }
}

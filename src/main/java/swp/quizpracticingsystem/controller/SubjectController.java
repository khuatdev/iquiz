/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp.quizpracticingsystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import swp.quizpracticingsystem.dto.CategoryDTO;
import swp.quizpracticingsystem.dto.SubjectDTO;
import swp.quizpracticingsystem.service.ICategoryService;
import swp.quizpracticingsystem.service.ISubjectService;

/**
 *
 * @author Mosena
 */
@Controller
public class SubjectController {
    
    @Autowired
    private ISubjectService subjectService;
    
    @Autowired
    private ICategoryService categoryService;
    
    @GetMapping("/subjects/page/{pageNo}")
    public String getAllSubject(@PathVariable("pageNo") int pageNo,
                                Model model){
        Page<SubjectDTO> subjects=subjectService
                .findPaginatedAllSubjects(pageNo, 6);
        List<CategoryDTO> listCategory=categoryService.findAll();
        model.addAttribute("subjects", subjects);
        model.addAttribute("categories", listCategory);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("totalPages"
                , subjects.getTotalPages());
        model.addAttribute("totaSubjects"
                , subjects.getTotalElements());
        return "home/subjects";
    }
    
    @GetMapping("/subjects")
    public String accessSubject(Model model){
        Page<SubjectDTO> subjects=subjectService
                .findPaginatedAllSubjects(1, 6);
        List<CategoryDTO> listCategory=categoryService.findAll();
        model.addAttribute("subjects", subjects);
        model.addAttribute("categories", listCategory);
        model.addAttribute("pageNo", 1);
        model.addAttribute("totalPages"
                , subjects.getTotalPages());
        model.addAttribute("totaSubjects"
                , subjects.getTotalElements());
        return "home/subjects";
    }
}

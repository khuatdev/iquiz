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
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @GetMapping("/subjects")
    public String getAllSubject(
            @RequestParam(value="pageNo",defaultValue = "1") int pageNo,
            @RequestParam(value="category",required = false) Integer categoryId,
            Model model){
        Page<SubjectDTO> subjects;
        if(categoryId!=null){
            subjects=subjectService.findPaginatedSubjectsByCategory(pageNo, 
                    3, categoryId);
        }
        else{
            subjects=subjectService
                .findPaginatedAllSubjects(pageNo, 3);
        }
        List<CategoryDTO> listCategory=categoryService.findAll();
        model.addAttribute("category", categoryId);
        model.addAttribute("subjects", subjects);
        model.addAttribute("categories", listCategory);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("totalPages"
                , subjects.getTotalPages());
        model.addAttribute("totaSubjects"
                , subjects.getTotalElements());
        return "home/subjects";
    }
    
@GetMapping("/subjects/search")
    public String searchSubject(
            @RequestParam(value="searchValue", required = true) String searchValue,
            @RequestParam(value="pageNo",defaultValue = "1") int pageNo,
            @RequestParam(value="category",required = false) Integer categoryId,
            Model model){
        Page<SubjectDTO> subjects;
        if(categoryId!=null){
            subjects=subjectService.findPaginatedSubjectsByCategory(pageNo, 
                    3, categoryId);
        }
        else{
            subjects=subjectService
                .findPaginatedSubjectBySubjectName(pageNo, 3, searchValue);
        }
        List<CategoryDTO> listCategory=categoryService.findAll();
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("category", categoryId);
        model.addAttribute("subjects", subjects);
        model.addAttribute("categories", listCategory);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("totalPages"
                , subjects.getTotalPages());
        model.addAttribute("totaSubjects"
                , subjects.getTotalElements());
        return "home/subjects";
    }
}

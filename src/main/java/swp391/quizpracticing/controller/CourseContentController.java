/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.quizpracticing.model.Category;
import swp391.quizpracticing.model.Subcategory;
import swp391.quizpracticing.model.Subject;
import swp391.quizpracticing.model.User;
import swp391.quizpracticing.service.ICategoryService;
import swp391.quizpracticing.service.ISubcategoryService;
import swp391.quizpracticing.service.ISubjectService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Mosena
 */
@Controller
public class CourseContentController {

    @Autowired
    private ISubjectService iSubjectService;

    @Autowired
    private ISubcategoryService iSubcategoryService;

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("admin/subjects-list")
    public String AdminGetToSubjectsList(@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                                         @RequestParam(name = "itemPerPage", defaultValue = "10") Integer itemPerPage,
                                         @RequestParam(name = "subject-name", defaultValue = "") String searchTerm,
                                         @RequestParam(name = "category-id", defaultValue = "-1") Integer categoryId,
                                         Model model, HttpSession session) {

        User loggedinUser = (User)session.getAttribute("user");

        //Get all categories and subcategories
        List<Subcategory> allSubcategories = iSubcategoryService.getAll();

        List<Category> allCategories = iCategoryService.listAll();

        if(searchTerm.isEmpty() && categoryId == -1) {  // display the page initially
            Page<Subject> subjectsWithPagination = iSubjectService.findSubjectsWithPagination(pageNum, itemPerPage);

            if(!subjectsWithPagination.hasContent()) {
                subjectsWithPagination = iSubjectService.findSubjectsWithPagination(0, itemPerPage);
            }

            model.addAttribute("subjects", subjectsWithPagination);

        } else if(!searchTerm.isEmpty() && categoryId == -1) {
            Page<Subject> subjectsWithPagination = iSubjectService.searchForSubjectsByName(pageNum, itemPerPage, searchTerm);
            System.out.println("number of subjects = " + subjectsWithPagination.getTotalElements());

            model.addAttribute("subjects", subjectsWithPagination);
        } else {

            List<Subject> allSubjects = iSubjectService.listAll();
            HashMap<Integer, Subject> map = new HashMap<>();
            for(Subject subject : allSubjects) {
                List<Subcategory> subcategories = subject.getSubCategories();

                for(Subcategory subcategory : subcategories) {
                    if(Objects.equals(subcategory.getCategory().getId(), categoryId)) {
                        if(!map.containsKey(subject.getId())) {
                            map.put(subject.getId(), subject);
                        }
                    }
                }
            }

            List<Subject> filteredSubjects = new ArrayList<>(map.values());
            int totalSubjects = filteredSubjects.size();
            Page<Subject> subjectsWithPagination = new PageImpl<>(filteredSubjects, PageRequest.of(pageNum, itemPerPage), totalSubjects);
            System.out.println(subjectsWithPagination.getSize());

            model.addAttribute("subjects", subjectsWithPagination);
        }


        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("subjectName", searchTerm);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("selectedItemPerPage", itemPerPage);
        model.addAttribute("categories", allCategories);
        model.addAttribute("subcategories", allSubcategories);
        model.addAttribute("categoryId", categoryId);

        return "course_content/subjects-list";
    }

    @GetMapping("expert/subjects-list")
    public String ExpertGetToSubjectsList(@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                                          @RequestParam(name = "itemPerPage", defaultValue = "10") Integer itemPerPage,
                                          @RequestParam(name = "subject-name", defaultValue = "") String searchTerm,
                                          @RequestParam(name = "category-id", defaultValue = "-1") Integer categoryId,
                                          @RequestParam(name = "subcategory-id", defaultValue = "-1") Integer subcategoryId,
                                          Model model, HttpSession session) {

        User loggedinUser = (User)session.getAttribute("user");

        //Find subjects by expert_id
        Page<Subject> subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPaginationByExpertId(loggedinUser.getId(), pageNum, itemPerPage);

        if(!subjectsWithPaginationByExpertId.hasContent()) {
            subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPaginationByExpertId(loggedinUser.getId(), 0, itemPerPage);
        }

        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("subjects", subjectsWithPaginationByExpertId);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("selectedItemPerPage", itemPerPage);

        return "course_content/subjects-list";

    }

}

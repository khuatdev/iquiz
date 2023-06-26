/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import swp391.quizpracticing.dto.CategoryDTO;
import swp391.quizpracticing.dto.SubcategoryDTO;
import swp391.quizpracticing.dto.SubjectDTO;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.service.ICategoryService;
import swp391.quizpracticing.service.ISubcategoryService;
import swp391.quizpracticing.service.ISubjectService;
import swp391.quizpracticing.service.IUserService;

/**
 *
 * @author Mosena
 */
@Controller
public class PublicController {
    @Autowired
    private IUserService userService;
    
    @Autowired
    private ISubjectService subjectService;
    
    @Autowired
    private ICategoryService categoryService;
    
    @Autowired
    private ISubcategoryService subCategoryService;
    
    @GetMapping("/user-created/verify")
    public String verify(@RequestParam("code") String code, Model model){
        UserDTO u=userService.findUserByToken(code);
        if(u!=null){
            userService.updateUserStatusAndToken(u.getId(), Boolean.TRUE);
            return "/common/verify_success";
        }
        model.addAttribute("msg", "Get lost?");
        return "/admin/error";
    }
    
    @GetMapping("/user-created/discard")
    public String discard(@RequestParam("code") String code, Model model){
        UserDTO u=userService.findUserByToken(code);
        if(u!=null){
            userService.remove(u);
            return "/admin/discardaccount";
        }
        model.addAttribute("msg", "Get lost?");
        return "/admin/error";
    }
    
    @GetMapping("/subjects/subjects-list")
    public String getSubjects(HttpSession session,
            Model model,
            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "order", defaultValue = "asc") String order,
            @RequestParam(name = "subCategory", required = false) 
                    List<Integer> subCategories){
        int pageSize = 6;
        Page<SubjectDTO> page=subjectService.findAll(pageNo, pageSize, search, 
                order, subCategories);
        Integer totalPages=page.getTotalPages();
        if(totalPages==0 || pageNo<1 || pageNo>totalPages){
            model.addAttribute("msg", "Not found");
            return "/admin/error";
        }
        List<SubjectDTO> subjectList=page
                .stream()
                .collect(Collectors.toList());
        List<CategoryDTO> categoryList=categoryService.findAll();
        model.addAttribute("userSession", 
                session.getAttribute("user"));
        model.addAttribute("subjects", subjectList);
        model.addAttribute("categories", categoryList);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("totalPages", totalPages);
        return "/public/subjects";
    }
    
    @GetMapping("/subCategories")
    @ResponseBody
    public List<SubcategoryDTO> getSubcategories(
            @RequestParam("categoryId") Integer categoryId){
        return subCategoryService.findByCategoryId(categoryId);
    }
}

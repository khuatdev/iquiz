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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import swp391.quizpracticing.model.Category;
import swp391.quizpracticing.model.Subcategory;
import swp391.quizpracticing.model.Subject;
import swp391.quizpracticing.model.User;
import swp391.quizpracticing.service.ICategoryService;
import swp391.quizpracticing.service.ISubcategoryService;
import swp391.quizpracticing.service.ISubjectService;
import swp391.quizpracticing.service.IUserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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

    @Autowired
    private IUserService iUserService;

    private final String FOLDER_PATH = "C:/Users/DELL/Documents/2_CodingZone/2_InSchool_(FPTUni)/5_SWP391/SWP391GitProject/summer2023-swp391.se1714-g5/src/main/resources/static/database_images";

    @GetMapping("admin/subjects-list")
    public String AdminGetToSubjectsList(@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                                         @RequestParam(name = "itemPerPage", defaultValue = "10") Integer itemPerPage,
                                         @RequestParam(name = "subject-name", defaultValue = "") String searchTerm,
                                         @RequestParam(name = "category-id", defaultValue = "-1") Integer categoryId,
                                         @RequestParam(name = "status", defaultValue = "-1") String status,
                                         @RequestParam(name = "check", defaultValue = "false") Boolean check,
                                         Model model, HttpSession session) {

        System.out.println("check: " + check);
        if(check != null) {
            model.addAttribute("check", check);
        }


        User loggedinUser = (User)session.getAttribute("user");

        //Get all categories and subcategories and status
        List<Subcategory> allSubcategories = iSubcategoryService.getAll();

        List<Category> allCategories = iCategoryService.listAll();

        List<Subject> allSubjects = iSubjectService.listAll();

        Set<Boolean> allStatus = new HashSet<>();
        for(Subject subject : allSubjects) {
            allStatus.add(subject.getStatus());
        }

        if(searchTerm.isEmpty() && categoryId == -1 && status.equals("-1")) {  // display all subjects (no search, no filter)
            Page<Subject> subjectsWithPagination = iSubjectService.findSubjectsWithPagination(pageNum, itemPerPage);

            if(!subjectsWithPagination.hasContent()) {
                subjectsWithPagination = iSubjectService.findSubjectsWithPagination(0, itemPerPage);
            }

            model.addAttribute("subjects", subjectsWithPagination);

        } else if(!searchTerm.isEmpty()) {  // perform searching
            Page<Subject> subjectsWithPagination = iSubjectService.searchForSubjectsByName(pageNum, itemPerPage, searchTerm);
            System.out.println("number of subjects = " + subjectsWithPagination.getTotalElements());

            model.addAttribute("subjects", subjectsWithPagination);
        } else {  //Perform filtering

            Page<Subject> subjectsWithPagination;

            if(!status.equals("-1") && categoryId == -1) {  //filter by status
                subjectsWithPagination = iSubjectService.findSubjectsByStatus(Boolean.valueOf(status), pageNum, itemPerPage);
                model.addAttribute("subjects", subjectsWithPagination);
            }
            if(status.equals("-1") && categoryId != -1) {  //filter by catecory_id
                //Take all subjects + loop through all the subjects and get its list of subcategories
                //loop through received sub-categories and get + get categories from the subcategories list
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
                subjectsWithPagination = new PageImpl<>(filteredSubjects, PageRequest.of(pageNum, itemPerPage), totalSubjects);

                model.addAttribute("subjects", subjectsWithPagination);
            }
            if (!status.equals("-1") && categoryId != -1) {  //filter by both
                //Take all subjects + loop through all the subjects and get its list of subcategories
                //loop through received sub-categories and get + get categories from the subcategories list
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

                List<Subject> filteredSubjects = new ArrayList<>();
                for(Subject subject : map.values()) {
                    if(subject.getStatus() == Boolean.parseBoolean(status)) {
                        filteredSubjects.add(subject);
                    }
                }
                int totalSubjects = filteredSubjects.size();
                subjectsWithPagination = new PageImpl<>(filteredSubjects, PageRequest.of(pageNum, itemPerPage), totalSubjects);

                model.addAttribute("subjects", subjectsWithPagination);
            }
        }

        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("subjectName", searchTerm);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("itemPerPage", itemPerPage);
        model.addAttribute("selectedItemPerPage", itemPerPage);
        model.addAttribute("categories", allCategories);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("allStatus", allStatus);
        model.addAttribute("selectedStatus", status);

        return "course_content/subjects-list";
    }

    @GetMapping("expert/subjects-list")
    public String ExpertGetToSubjectsList(@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                                          @RequestParam(name = "itemPerPage", defaultValue = "10") Integer itemPerPage,
                                          @RequestParam(name = "subject-name", defaultValue = "") String searchTerm,
                                          @RequestParam(name = "category-id", defaultValue = "-1") Integer categoryId,
                                          @RequestParam(name = "status", defaultValue = "-1") String status,
                                          @RequestParam(name = "check") Boolean check,
                                          Model model, HttpSession session) {

        User loggedinUser = (User)session.getAttribute("user");

        //Find subjects by expert_id
        Page<Subject> subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPaginationByExpertId(loggedinUser.getId(), pageNum, itemPerPage);

        Set<Boolean> allStatus = new HashSet<>();
        for(Subject subject : subjectsWithPaginationByExpertId) {
            allStatus.add(subject.getStatus());
        }

        Set<Integer> allCategoriesId = new HashSet<>();
        for(Subject subject : subjectsWithPaginationByExpertId) {
            List<Subcategory> subcategories = subject.getSubCategories();
            for(Subcategory subcategory : subcategories) {
                int cId = subcategory.getCategory().getId();
                allCategoriesId.add(cId);
            }
        }

        Set<Category> allCategories = new HashSet<>();
        for(Integer id : allCategoriesId) {
            Category c = iCategoryService.getById(id);
            allCategories.add(c);
        }

        if(searchTerm.isEmpty() && categoryId == -1 && status.equals("-1")) {  // display all subjects (no search, no filter)

            if(!subjectsWithPaginationByExpertId.hasContent()) {
                subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPagination(0, itemPerPage);
            }

            model.addAttribute("subjects", subjectsWithPaginationByExpertId);

        } else if(!searchTerm.isEmpty()) {  // perform searching
            subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPaginationByExpertIdAndByName(loggedinUser.getId(), searchTerm, pageNum, itemPerPage);
            System.out.println("number of subjects = " + subjectsWithPaginationByExpertId.getTotalElements());

            model.addAttribute("subjects", subjectsWithPaginationByExpertId);
        } else {  //Perform filtering

            Page<Subject> subjectsWithPagination;

            if(!status.equals("-1") && categoryId == -1) {  //filter by status
                Integer statusInt = Boolean.parseBoolean(status) ? 1 : 0;
                subjectsWithPagination = iSubjectService.findSubjectsWithPaginationByExpertIdAndByStatus(loggedinUser.getId(), statusInt, pageNum, itemPerPage);
                model.addAttribute("subjects", subjectsWithPagination);
            }
            if(status.equals("-1") && categoryId != -1) {  //filter by catecory_id
                //Take all subjects + loop through all the subjects and get its list of subcategories
                //loop through received sub-categories and get + get categories from the subcategories list
                subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPaginationByExpertId(loggedinUser.getId(), pageNum, itemPerPage);
                HashMap<Integer, Subject> map = new HashMap<>();
                for(Subject subject : subjectsWithPaginationByExpertId) {
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
                subjectsWithPagination = new PageImpl<>(filteredSubjects, PageRequest.of(pageNum, itemPerPage), totalSubjects);

                model.addAttribute("subjects", subjectsWithPagination);
            }
            if (!status.equals("-1") && categoryId != -1) {  //filter by both
                //Take all subjects + loop through all the subjects and get its list of subcategories
                //loop through received sub-categories and get + get categories from the subcategories list
                subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPaginationByExpertId(loggedinUser.getId(), pageNum, itemPerPage);
                HashMap<Integer, Subject> map = new HashMap<>();
                for(Subject subject : subjectsWithPaginationByExpertId) {
                    List<Subcategory> subcategories = subject.getSubCategories();
                    for(Subcategory subcategory : subcategories) {
                        if(Objects.equals(subcategory.getCategory().getId(), categoryId)) {
                            if(!map.containsKey(subject.getId())) {
                                map.put(subject.getId(), subject);
                            }
                        }
                    }
                }

                List<Subject> filteredSubjects = new ArrayList<>();
                for(Subject subject : map.values()) {
                    if(subject.getStatus() == Boolean.parseBoolean(status)) {
                        filteredSubjects.add(subject);
                    }
                }
                int totalSubjects = filteredSubjects.size();
                subjectsWithPagination = new PageImpl<>(filteredSubjects, PageRequest.of(pageNum, itemPerPage), totalSubjects);

                model.addAttribute("subjects", subjectsWithPagination);
            }
        }

        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("subjectName", searchTerm);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("itemPerPage", itemPerPage);
        model.addAttribute("selectedItemPerPage", itemPerPage);
        model.addAttribute("categories", allCategories);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("allStatus", allStatus);
        model.addAttribute("selectedStatus", status);

        return "course_content/subjects-list";

    }

    @GetMapping("/admin/new-subject")
    public String getToNewSubjectForm(Model model, HttpSession session) {

        User loggedinUser = (User)session.getAttribute("user");

        List<Category> allCategories = iCategoryService.listAll();
        List<User> allExperts = iUserService.getAllExpert();
        List<Boolean> isFeatured = new ArrayList<>(Arrays.asList(true, false));
        List<Boolean> status = new ArrayList<>(Arrays.asList(true, false));
        List<Subcategory> allSubcategories = iSubcategoryService.getAll();

        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("categories", allCategories);
        model.addAttribute("subcategories", allSubcategories);
        model.addAttribute("experts", allExperts);
        model.addAttribute("featured", isFeatured);
        model.addAttribute("status", status);

        return "course_content/subject_form";
    }

    @PostMapping("/admin/new-subject-submit")
    public String addNewSubject(@RequestParam(name = "name") String subjectName,
                                @RequestParam(name = "category") Integer categoryId,
                                @RequestParam(name = "subcategory") Integer subcategoryId,
                                @RequestParam(name = "owner") Integer ownerId,
                                @RequestParam(name = "featured") Boolean featured,
                                @RequestParam(name = "status") Boolean status,
                                @RequestParam(name = "description") String description,
                                @RequestParam(name = "thumbnail") MultipartFile multipartFile,
                                Model model, HttpSession session) throws IOException {

        Boolean check = true;
        String ms1, ms2;

        //check if subject name is existed
        if(iSubjectService.checkIfSubjectExistByBriefInfo(subjectName)) {
            check = false;
            ms1 = "This Subject Name is Existed!";
            System.out.println("condition 1 fail");

            model.addAttribute("ms1", ms1);
        } else {
            //check if the selected subcategory match the selected category
            Subcategory selectedSubcategory = iSubcategoryService.getById(subcategoryId);
            Category selectedCategory = iCategoryService.getById(categoryId);
            List<Subcategory> selectedCategory_subcategories = selectedCategory.getSubCategories();
            for(Subcategory subcategory : selectedCategory_subcategories) {
                if (!subcategory.getCategory().getName().equals(selectedSubcategory.getCategory().getName())) {
                    check = false;
                    ms2 = "Selected Subcategory DOES NOT MATCH Selected Category!";
                    System.out.println("condition 2 fail");

                    model.addAttribute("ms2", ms2);
                } else {
                    check = true;
                    break;
                }
            }
        }

        System.out.println(check);

        //Take the file name user has uploaded
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println("fileName: " + fileName);
        //Store the fileName into the database with the respective subject

        //Store the actual file to the file directory
        Path fileNameAndPath = Paths.get(FOLDER_PATH, multipartFile.getOriginalFilename());
        String uploadDir = FOLDER_PATH;
        Files.write(fileNameAndPath, multipartFile.getBytes());


        if(check) {
            //Save new subject
            model.addAttribute("check", check);
            return "redirect:../admin/subjects-list?check=" + check;
        } else {
            model.addAttribute("check", check);
            return "redirect:../admin/new-subject";
        }

    }
}

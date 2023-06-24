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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import swp391.quizpracticing.dto.DimensionDTO;
import swp391.quizpracticing.dto.PricepackageDTO;
import swp391.quizpracticing.dto.SubjectDTO;
import swp391.quizpracticing.model.*;
import swp391.quizpracticing.repository.IBlogRepository;
import swp391.quizpracticing.repository.ISubjectRepository;
import swp391.quizpracticing.service.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import swp391.quizpracticing.dto.UserDTO;

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

    @Autowired
    private ISubjectRepository iSubjectRepository;

    @Autowired
    private IDimensionService iDimensionService;

    @Autowired
    private IPricepackageService iPricepackageService;

    private final String FOLDER_PATH = "D:\\Java Web\\quizpracticingsystem\\src\\main\\resources\\static\\database_images";

    @GetMapping("admin/subjects-list")
    public String AdminGetToSubjectsList(@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                                         @RequestParam(name = "itemPerPage", defaultValue = "10") Integer itemPerPage,
                                         @RequestParam(name = "subject-name", defaultValue = "") String searchTermNoTrim,
                                         @RequestParam(name = "categoriesId", required = false) Integer[] selectedCategoriesId,
                                         @RequestParam(name = "status", defaultValue = "-1") String status,
                                         @RequestParam(name = "check", defaultValue = "false") Boolean check,
//                                         @ModelAttribute(name = "newSubject") Subject newSubject,
                                         Model model, HttpSession session) {

        System.out.println("check: " + check);
        if(check != null) {
            model.addAttribute("check", check);
        }

        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");
        if(loggedinUser != null) {
            System.out.println(loggedinUser.getRole().getName());
            String userRoleForUrl = switch (loggedinUser.getRole().getName()) {
                case "ROLE_ADMIN" -> "admin";
                case "ROLE_EXPERT" -> "expert";
                case "ROLE_SALE" -> "sale";
                case "ROLE_MARKETING" -> "marketing";
                case "ROLE_CUSTOMER" -> "user";
                default -> "";
            };
            System.out.println("userRoleForUrl: " + userRoleForUrl);
            model.addAttribute("userRoleForUrl", userRoleForUrl);
        }

        Subject newSubject = (Subject) session.getAttribute("newSubject");
        if(newSubject != null) {
            model.addAttribute("newSubject", newSubject);
        }

        String searchTerm = searchTermNoTrim.trim();

        //Get all categories and subcategories and status
        List<Subcategory> allSubcategories = iSubcategoryService.getAll();

        List<Category> allCategories = iCategoryService.listAll();

        List<Subject> allSubjects = iSubjectService.listAll();

        Set<Boolean> allStatus = new HashSet<>();
        for(Subject subject : allSubjects) {
            allStatus.add(subject.getStatus());
        }

        if(searchTerm.isEmpty() && (selectedCategoriesId == null || selectedCategoriesId.length == 0) && status.equals("-1")) {  // display all subjects (no search, no filter)
            Page<Subject> subjectsWithPagination = iSubjectService.findSubjectsWithPagination(pageNum, itemPerPage);

            if(!subjectsWithPagination.hasContent()) {
                subjectsWithPagination = iSubjectService.findSubjectsWithPagination(0, itemPerPage);
            }

            model.addAttribute("subjects", subjectsWithPagination);

        } else if(!searchTerm.isEmpty()) {  // perform searching
            Page<Subject> subjectsWithPagination = iSubjectService.searchForSubjectsByName(pageNum, itemPerPage, searchTerm);

            model.addAttribute("subjects", subjectsWithPagination);
        } else {  //Perform filtering
            Page<Subject> subjectsWithPagination;

            if(!status.equals("-1") && (selectedCategoriesId == null || selectedCategoriesId.length == 0)) {  //filter by status
                subjectsWithPagination = iSubjectService.findSubjectsByStatus(Boolean.valueOf(status), pageNum, itemPerPage);
                model.addAttribute("subjects", subjectsWithPagination);
            }
            if(status.equals("-1") && selectedCategoriesId != null && selectedCategoriesId.length != 0) {  //filter by catecories_id
                if(Arrays.asList(selectedCategoriesId).contains(-1)) {
                    subjectsWithPagination = iSubjectService.findSubjectsWithPagination(pageNum, itemPerPage);

                    if(!subjectsWithPagination.hasContent()) {
                        subjectsWithPagination = iSubjectService.findSubjectsWithPagination(0, itemPerPage);
                    }

                    model.addAttribute("subjects", subjectsWithPagination);
                } else {
                    //Take all subjects + loop through all the subjects and get its list of subcategories
                    //loop through received sub-categories and get + get categories from the subcategories list
                    List<Category> selectedCategories = new ArrayList<>();
                    for(Integer id : selectedCategoriesId) {
                        Category c = iCategoryService.getById(id);
                        selectedCategories.add(c);
                    }

                    model.addAttribute("selectedCategories", selectedCategories);

                    List<Subcategory> subcategories = new ArrayList<>();
                    for(Category category : selectedCategories) {
                        List<Subcategory> sub = category.getSubCategories();
                        subcategories.addAll(sub);
                    }

                    Set<Integer> subjectsId = new HashSet<>();
                    List<Subject> subjectsRaw = new ArrayList<>();
                    for(Subcategory sub : subcategories) {
                        List<Subject> s = sub.getSubjects();
                        subjectsRaw.addAll(s);
                    }
                    for(Subject s : subjectsRaw) {
                        subjectsId.add(s.getId());
                    }

                    List<Subject> filteredSubjects = new ArrayList<>();

                    for(Integer i : subjectsId) {
                        Subject s = iSubjectService.getById(i);
                        filteredSubjects.add(s);
                    }
                    filteredSubjects.sort(Comparator.comparing(Subject::getId));

                    int totalSubjects = filteredSubjects.size();

                    List<Subject> filteredSubjectsPagination = new ArrayList<>();
                    int startItem = pageNum*itemPerPage;
                    int toIndex = Math.min(startItem + itemPerPage, filteredSubjects.size());
                    filteredSubjectsPagination = filteredSubjects.subList(startItem, toIndex);

                    subjectsWithPagination = new PageImpl<>(filteredSubjectsPagination, PageRequest.of(pageNum, itemPerPage).withSort(Sort.by(Sort.Direction.ASC, "id")), totalSubjects);

                    model.addAttribute("subjects", subjectsWithPagination);
                }

            }
            if (!status.equals("-1") && selectedCategoriesId != null && selectedCategoriesId.length != 0) {  //filter by both

                List<Subject> filteredSubjects = new ArrayList<>();

                if(Arrays.asList(selectedCategoriesId).contains(-1)) {
                    filteredSubjects = iSubjectService.listAll();
                } else {
                    //Take all subjects + loop through all the subjects and get its list of subcategories
                    //loop through received sub-categories and get + get categories from the subcategories list
                    List<Category> selectedCategories = new ArrayList<>();
                    for(Integer id : selectedCategoriesId) {
                        Category c = iCategoryService.getById(id);
                        selectedCategories.add(c);
                    }

                    model.addAttribute("selectedCategories", selectedCategories);

                    List<Subcategory> subcategories = new ArrayList<>();
                    for(Category category : selectedCategories) {
                        List<Subcategory> sub = category.getSubCategories();
                        subcategories.addAll(sub);
                    }

                    Set<Integer> subjectsId = new HashSet<>();
                    List<Subject> subjectsRaw = new ArrayList<>();
                    for(Subcategory sub : subcategories) {
                        List<Subject> s = sub.getSubjects();
                        subjectsRaw.addAll(s);
                    }
                    for(Subject s : subjectsRaw) {
                        subjectsId.add(s.getId());
                    }

                    for(Integer i : subjectsId) {
                        Subject s = iSubjectService.getById(i);
                        filteredSubjects.add(s);
                    }
                }

                //status
                List<Subject> filteredSubjectsFull = new ArrayList<>();
                for(Subject subject : filteredSubjects) {
                    if(subject.getStatus() == Boolean.parseBoolean(status)) {
                        filteredSubjectsFull.add(subject);
                    }
                }
                filteredSubjectsFull.sort(Comparator.comparing(Subject::getId));

                int totalSubjects = filteredSubjectsFull.size();
//                subjectsWithPagination = new PageImpl<>(filteredSubjectsFull, PageRequest.of(pageNum, itemPerPage), totalSubjects);

                List<Subject> filteredSubjectsPagination = new ArrayList<>();
                int startItem = pageNum*itemPerPage;
                int toIndex = Math.min(startItem + itemPerPage, filteredSubjectsFull.size());
                filteredSubjectsPagination = filteredSubjectsFull.subList(startItem, toIndex);

                subjectsWithPagination = new PageImpl<>(filteredSubjectsPagination, PageRequest.of(pageNum, itemPerPage), totalSubjects);

                model.addAttribute("subjects", subjectsWithPagination);

                model.addAttribute("subjects", subjectsWithPagination);
            }
        }

        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("subjectName", searchTerm);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("itemPerPage", itemPerPage);
        model.addAttribute("selectedItemPerPage", itemPerPage);
        model.addAttribute("categories", allCategories);
        if(selectedCategoriesId != null && selectedCategoriesId.length != 0) {
            System.out.println("selectedCategoriesId size = " + selectedCategoriesId.length);
            StringBuilder pagination_links = new StringBuilder(selectedCategoriesId[0].toString());
            for(int i = 1; i < selectedCategoriesId.length; i++) {
                pagination_links.append(",").append(selectedCategoriesId[i].toString());
            }

            List<Category> selectedCategories = new ArrayList<>();
            for(Integer id : selectedCategoriesId) {
                Category c = iCategoryService.getById(id);
                selectedCategories.add(c);
            }

            System.out.println(pagination_links.toString());

            String[] strArr = pagination_links.toString().split(",");
            Integer[] intArr = new Integer[strArr.length];

            for (int i = 0; i < strArr.length; i++) {
                intArr[i] = Integer.parseInt(strArr[i]);
            }

            model.addAttribute("selectedCategories", selectedCategories);
            model.addAttribute("used_for_pagination_links", pagination_links.toString());
            model.addAttribute("string_to_int_array", intArr);
            model.addAttribute("selectedCategoriesId", Arrays.asList(selectedCategoriesId));
        }

//        if(newSubject != null) {
//            System.out.println(newSubject);
//            model.addAttribute("newSubject", newSubject);
//        }

        model.addAttribute("allStatus", allStatus);
        model.addAttribute("selectedStatus", status);

        return "course_content/subjects-list";
    }

    @GetMapping("expert/subjects-list")
    public String ExpertGetToSubjectsList(@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                                          @RequestParam(name = "itemPerPage", defaultValue = "10") Integer itemPerPage,
                                          @RequestParam(name = "subject-name", defaultValue = "") String searchTerm,
                                          @RequestParam(name = "categoriesId", required = false) Integer[] selectedCategoriesId,
                                          @RequestParam(name = "status", defaultValue = "-1") String status,
                                          @RequestParam(name = "check", defaultValue = "false") Boolean check,
                                          Model model, HttpSession session) {

        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");
        if(loggedinUser != null) {
            System.out.println(loggedinUser.getRole().getName());
            String userRoleForUrl = switch (loggedinUser.getRole().getName()) {
                case "ROLE_ADMIN" -> "admin";
                case "ROLE_EXPERT" -> "expert";
                case "ROLE_SALE" -> "sale";
                case "ROLE_MARKETING" -> "marketing";
                case "ROLE_CUSTOMER" -> "user";
                default -> "";
            };
            System.out.println("userRoleForUrl: " + userRoleForUrl);
            model.addAttribute("userRoleForUrl", userRoleForUrl);
        }

        //Find subjects by expert_id
        Page<Subject> subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPaginationByExpertId(loggedinUser.getId(), pageNum, itemPerPage);
        System.out.println("size: " + subjectsWithPaginationByExpertId.getSize());
        List<Subject> subjectsByExpertId = iSubjectService.findByExpertId(loggedinUser.getId());
        System.out.println("list size: " + subjectsByExpertId.size());

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

        if(searchTerm.isEmpty() && (selectedCategoriesId == null || selectedCategoriesId.length == 0) && status.equals("-1")) {  // display all subjects (no search, no filter)

            if(!subjectsWithPaginationByExpertId.hasContent()) {
//                subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPagination(0, itemPerPage);
                subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPaginationByExpertId(loggedinUser.getId(), 0, itemPerPage);
            }

            model.addAttribute("subjects", subjectsWithPaginationByExpertId);

        } else if(!searchTerm.isEmpty()) {  // perform searching
            subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPaginationByExpertIdAndByName(loggedinUser.getId(), searchTerm, pageNum, itemPerPage);
            System.out.println("number of subjects = " + subjectsWithPaginationByExpertId.getTotalElements());

            model.addAttribute("subjects", subjectsWithPaginationByExpertId);
        } else {  //Perform filtering

            Page<Subject> subjectsWithPagination;

            if(!status.equals("-1") && (selectedCategoriesId == null || selectedCategoriesId.length == 0)) {  //filter by status
                Integer statusInt = Boolean.parseBoolean(status) ? 1 : 0;
                subjectsWithPagination = iSubjectService.findSubjectsWithPaginationByExpertIdAndByStatus(loggedinUser.getId(), statusInt, pageNum, itemPerPage);
                model.addAttribute("subjects", subjectsWithPagination);
            }
            if(status.equals("-1") && selectedCategoriesId != null && selectedCategoriesId.length != 0) {  //filter by catecory_id

                if(Arrays.asList(selectedCategoriesId).contains(-1)) {
                    subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPaginationByExpertId(loggedinUser.getId(), pageNum, itemPerPage);

                    if(!subjectsWithPaginationByExpertId.hasContent()) {
                        subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPaginationByExpertId(loggedinUser.getId(), 0, itemPerPage);
                    }

                    model.addAttribute("subjects", subjectsWithPaginationByExpertId);
                } else {
                    //Take all subjects + loop through all the subjects and get its list of subcategories
                    //loop through received sub-categories and get + get categories from the subcategories list

                    //Get all selected categories
                    List<Category> selectedCategories = new ArrayList<>();
                    for(Integer id : selectedCategoriesId) {
                        Category c = iCategoryService.getById(id);
                        selectedCategories.add(c);
                    }

                    model.addAttribute("selectedCategories", selectedCategories);

                    //From the list of selected categories, get all subcategories from that
                    List<Subcategory> subcategories = new ArrayList<>();
                    for(Category category : selectedCategories) {
                        List<Subcategory> sub = category.getSubCategories();
                        subcategories.addAll(sub);
                    }

                    //from that list of selected subcategories, get all subjects + subjects_id from that
                    Set<Integer> subjectsId = new HashSet<>();
                    List<Subject> subjectsRaw = new ArrayList<>();
                    for(Subcategory sub : subcategories) {
                        List<Subject> s = sub.getSubjects();
                        subjectsRaw.addAll(s);
                    }
                    for(Subject s : subjectsRaw) {
                        subjectsId.add(s.getId());
                    }

                    Set<Integer> subjectsIdFiltered = new HashSet<>();

                    //check if the subjects-id that exist in the all subjects by expert
                    for(Subject subject : subjectsByExpertId) {
                        if(subjectsId.contains(subject.getId())) {
                            subjectsIdFiltered.add(subject.getId());
                        }
                    }

                    System.out.println("subjectsIdFiltered: " + subjectsIdFiltered.size());

                    //From the list of subjects-id, get the subjects
                    List<Subject> filteredSubjects = new ArrayList<>();

                    for(Integer i : subjectsIdFiltered) {
                        Subject s = iSubjectService.getById(i);
                        filteredSubjects.add(s);
                    }
                    filteredSubjects.sort(Comparator.comparing(Subject::getId));

                    int totalSubjects = filteredSubjects.size();

                    List<Subject> filteredSubjectsPagination = new ArrayList<>();
                    int startItem = pageNum*itemPerPage;
                    int toIndex = Math.min(startItem + itemPerPage, filteredSubjects.size());
                    filteredSubjectsPagination = filteredSubjects.subList(startItem, toIndex);

                    subjectsWithPagination = new PageImpl<>(filteredSubjectsPagination, PageRequest.of(pageNum, itemPerPage).withSort(Sort.by(Sort.Direction.ASC, "id")), totalSubjects);

                    model.addAttribute("subjects", subjectsWithPagination);

                }

            }
            if (!status.equals("-1") && selectedCategoriesId != null && selectedCategoriesId.length != 0) {  //filter by both

                List<Subject> filteredSubjects = new ArrayList<>();

                //check categories-id
                if(Arrays.asList(selectedCategoriesId).contains(-1)) {
                    filteredSubjects = iSubjectService.findByExpertId(loggedinUser.getId());
                } else {
                    //Take all subjects + loop through all the subjects and get its list of subcategories
                    //loop through received sub-categories and get + get categories from the subcategories list
                    List<Category> selectedCategories = new ArrayList<>();
                    for(Integer id : selectedCategoriesId) {
                        Category c = iCategoryService.getById(id);
                        selectedCategories.add(c);
                    }

                    model.addAttribute("selectedCategories", selectedCategories);

                    List<Subcategory> subcategories = new ArrayList<>();
                    for(Category category : selectedCategories) {
                        List<Subcategory> sub = category.getSubCategories();
                        subcategories.addAll(sub);
                    }

                    Set<Integer> subjectsId = new HashSet<>();
                    List<Subject> subjectsRaw = new ArrayList<>();
                    //get subjects from selected categories
                    for(Subcategory sub : subcategories) {
                        List<Subject> s = sub.getSubjects();
                        subjectsRaw.addAll(s);
                    }
                    for(Subject s : subjectsRaw) {
                        subjectsId.add(s.getId());
                    }

                    Set<Integer> subjectsIdFiltered = new HashSet<>();

                    //check if the subjects-id that exist in the all subjects by expert
                    for(Subject subject : subjectsByExpertId) {
                        if(subjectsId.contains(subject.getId())) {
                            subjectsIdFiltered.add(subject.getId());
                        }
                    }

                    for(Integer i : subjectsIdFiltered) {
                        Subject s = iSubjectService.getById(i);
                        filteredSubjects.add(s);
                    }
                }

                //check status
                List<Subject> filteredSubjectsFull = new ArrayList<>();
                for(Subject subject : filteredSubjects) {
                    if(subject.getStatus() == Boolean.parseBoolean(status)) {
                        filteredSubjectsFull.add(subject);
                    }
                }
                filteredSubjectsFull.sort(Comparator.comparing(Subject::getId));

                int totalSubjects = filteredSubjectsFull.size();
//                subjectsWithPagination = new PageImpl<>(filteredSubjectsFull, PageRequest.of(pageNum, itemPerPage), totalSubjects);

                List<Subject> filteredSubjectsPagination = new ArrayList<>();
                int startItem = pageNum*itemPerPage;
                int toIndex = Math.min(startItem + itemPerPage, filteredSubjectsFull.size());
                filteredSubjectsPagination = filteredSubjectsFull.subList(startItem, toIndex);

                subjectsWithPagination = new PageImpl<>(filteredSubjectsPagination, PageRequest.of(pageNum, itemPerPage), totalSubjects);

                model.addAttribute("subjects", subjectsWithPagination);

            }
        }

        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("subjectName", searchTerm);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("itemPerPage", itemPerPage);
        model.addAttribute("selectedItemPerPage", itemPerPage);
        model.addAttribute("categories", allCategories);
        if(selectedCategoriesId != null && selectedCategoriesId.length != 0) {
            System.out.println("selectedCategoriesId size = " + selectedCategoriesId.length);
            StringBuilder pagination_links = new StringBuilder(selectedCategoriesId[0].toString());
            for(int i = 1; i < selectedCategoriesId.length; i++) {
                pagination_links.append(",").append(selectedCategoriesId[i].toString());
            }

            List<Category> selectedCategories = new ArrayList<>();
            for(Integer id : selectedCategoriesId) {
                Category c = iCategoryService.getById(id);
                selectedCategories.add(c);
            }

            System.out.println(pagination_links.toString());

            String[] strArr = pagination_links.toString().split(",");
            Integer[] intArr = new Integer[strArr.length];

            for (int i = 0; i < strArr.length; i++) {
                intArr[i] = Integer.parseInt(strArr[i]);
            }

            model.addAttribute("selectedCategories", selectedCategories);
            model.addAttribute("used_for_pagination_links", pagination_links.toString());
            model.addAttribute("string_to_int_array", intArr);
            model.addAttribute("selectedCategoriesId", Arrays.asList(selectedCategoriesId));
        }
        model.addAttribute("allStatus", allStatus);
        model.addAttribute("selectedStatus", status);

        return "course_content/subjects-list";

    }

    @GetMapping("/admin/new-subject")
    public String getToNewSubjectForm(@ModelAttribute(name = "name") String subjectName,
                                      @ModelAttribute(name = "owner") User owner,
                                      @ModelAttribute(name = "selectedCategory") Category selectedCategory,
                                      @ModelAttribute(name = "selectedSubcategory") Subcategory selectedSubcategory,
                                      @ModelAttribute(name = "selectedFeatured") String selectedFeatured,
                                      @ModelAttribute(name = "selectedStatus") String selectedStatus,
                                      @ModelAttribute(name = "description") String description,
                                      @ModelAttribute(name = "thumbnail") MultipartFile thumbnail,
                                      @ModelAttribute(name = "ms1") String ms1,
                                      @ModelAttribute(name = "ms2") String ms2,
                                      Model model, HttpSession session) {

        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");

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

        if(ms1 != null || ms2 != null) {
            model.addAttribute("name", subjectName);
            model.addAttribute("selectedOwner", owner);
            model.addAttribute("selectedCategory", selectedCategory);
            model.addAttribute("selectedSubcategory", selectedSubcategory);
            model.addAttribute("selectedFeatured", selectedFeatured);
            model.addAttribute("selectedStatus", selectedStatus);
            model.addAttribute("description", description);
//            model.addAttribute("thumbnail", thumbnail);

            MultipartFile uploadedImg = (MultipartFile) session.getAttribute("thumbnail");
            model.addAttribute("thumbnail", uploadedImg);
            model.addAttribute("ms1", ms1);
            model.addAttribute("ms2", ms2);
        }

        return "course_content/subject_form";
    }

    @PostMapping("/admin/new-subject-submit")
    public String addNewSubject(@RequestParam(name = "name") String subjectNameNotTrim,
                                @RequestParam(name = "category") Integer categoryId,
                                @RequestParam(name = "subcategory") Integer subcategoryId,
                                @RequestParam(name = "owner") Integer ownerId,
                                @RequestParam(name = "featured") Boolean featured,
                                @RequestParam(name = "status") Boolean status,
                                @RequestParam(name = "description") String descriptionNotTrim,
                                @RequestParam(name = "thumbnail", required = false) MultipartFile multipartFile,
                                RedirectAttributes redirectAttribute,
                                Model model, HttpSession session) throws IOException {

        Boolean check = true;
        String ms1 = "", ms2 = "";

        String subjectName = subjectNameNotTrim.trim();
        System.out.println("subjectNameNotTrim: " + subjectNameNotTrim);
        System.out.println("subjectName: " + subjectName);

        String description = descriptionNotTrim.trim();

        //check if any input is empty, return false immediately
        if(subjectName.isEmpty() || description.isEmpty() || multipartFile.isEmpty()) {
            check = false;

            String emptyError = "Please fill all the input before submit!";

            redirectAttribute.addFlashAttribute("name", subjectName);
            redirectAttribute.addFlashAttribute("owner", iUserService.getByUserId(ownerId));
            redirectAttribute.addFlashAttribute("selectedCategory", iCategoryService.getById(categoryId));
            redirectAttribute.addFlashAttribute("selectedSubcategory", iSubcategoryService.getById(subcategoryId));
            redirectAttribute.addFlashAttribute("selectedFeatured", featured.toString());
            redirectAttribute.addFlashAttribute("selectedStatus", status.toString());
            redirectAttribute.addFlashAttribute("description", description);
            redirectAttribute.addFlashAttribute("emptyError", emptyError);

            return "redirect:../admin/new-subject";
        }

        //check if subject name is existed
        if(iSubjectService.checkIfSubjectExistByBriefInfo(subjectName)) {
            System.out.println("check if subject exist: " + iSubjectService.checkIfSubjectExistByBriefInfo(subjectName));
            check = false;
            ms1 = "Subject Name Existed!";
            System.out.println("condition 1 fail");
        }

        //check if the selected subcategory match the selected category
        Subcategory selectedSubcategory = iSubcategoryService.getById(subcategoryId);
        Category selectedCategory = iCategoryService.getById(categoryId);
        List<Subcategory> selectedCategory_subcategories = selectedCategory.getSubCategories();
        for(Subcategory subcategory : selectedCategory_subcategories) {
            if (!subcategory.getCategory().getName().equals(selectedSubcategory.getCategory().getName())) {
                check = false;
                ms2 = "Selected Subcategory DOES NOT MATCH Selected Category!";
                System.out.println("condition 2 fail");
            }
        }


        System.out.println(check);

        if(check) {  //save new subject

            String fileName;

            if(multipartFile != null) {
                //Take the file name user has uploaded
                fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
                System.out.println("fileName: " + fileName);
                //Store the fileName into the database with the respective subject

                //Store the actual file to the file directory
                Path fileNameAndPath = Paths.get(FOLDER_PATH, multipartFile.getOriginalFilename());
                String uploadDir = FOLDER_PATH;
                Files.write(fileNameAndPath, multipartFile.getBytes());
            } else {
                fileName="null";
            }

            List<Subcategory> subcategories = new ArrayList<>();
            subcategories.add(iSubcategoryService.getById(subcategoryId));

            Subject newSubject = new Subject();
            newSubject.setBriefInfo(subjectName);
            newSubject.setDescription(description);
            newSubject.setFeatured(featured);
            newSubject.setStatus(status);
            newSubject.setOwner(iUserService.getByUserId(ownerId));
            newSubject.setSubCategories(subcategories);
            newSubject.setThumbnail(fileName);

            newSubject = iSubjectRepository.save(newSubject);

            model.addAttribute("check", check);
            redirectAttribute.addFlashAttribute("newSubject", newSubject);
            session.setAttribute("newSubject", newSubject);
            return "redirect:../admin/subjects-list?check=" + check;
        } else {  //errors

            System.out.println("ms1: " + ms1);
            System.out.println("ms2: " + ms2);

            redirectAttribute.addFlashAttribute("name", subjectName);
            redirectAttribute.addFlashAttribute("owner", iUserService.getByUserId(ownerId));
            redirectAttribute.addFlashAttribute("selectedCategory", iCategoryService.getById(categoryId));
            redirectAttribute.addFlashAttribute("selectedSubcategory", iSubcategoryService.getById(subcategoryId));
            redirectAttribute.addFlashAttribute("selectedFeatured", featured.toString());
            redirectAttribute.addFlashAttribute("selectedStatus", status.toString());
            redirectAttribute.addFlashAttribute("description", description);
            redirectAttribute.addFlashAttribute("thumbnail", multipartFile);
            session.setAttribute("thumbnail", multipartFile);
            redirectAttribute.addFlashAttribute("ms1", ms1);
            redirectAttribute.addFlashAttribute("ms2", ms2);
            return "redirect:../admin/new-subject";
        }

    }

    @GetMapping("admin/subject-detail")
    public String AdminGetToSubjectDetails(@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                                           @RequestParam(name = "id", required = true) Integer id,
                                         @RequestParam(name = "categoriesId", required = false) Integer[] selectedCategoriesId,
                                         @RequestParam(name = "status", defaultValue = "-1") String status,
                                         @RequestParam(name = "check", defaultValue = "false") Boolean check,
                                         Model model, HttpSession session) {

        System.out.println("check: " + check);
        if(check != null) {
            model.addAttribute("check", check);
        }

        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");
        if(loggedinUser != null) {
            System.out.println(loggedinUser.getRole().getName());
            String userRoleForUrl = switch (loggedinUser.getRole().getName()) {
                case "ROLE_ADMIN" -> "admin";
                case "ROLE_EXPERT" -> "expert";
                case "ROLE_SALE" -> "sale";
                case "ROLE_MARKETING" -> "marketing";
                case "ROLE_CUSTOMER" -> "user";
                default -> "";
            };
            System.out.println("userRoleForUrl: " + userRoleForUrl);
            model.addAttribute("userRoleForUrl", userRoleForUrl);
        }

        Subject subject = iSubjectService.getSubjectById(id);
        model.addAttribute("subject", subject);
        model.addAttribute("userSession", session.getAttribute("user"));
        return "course_content/subjectdetails";

    }

    @GetMapping("expert/subject-detail")
    public String ExpertGetToSubjectDetails(@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                                            @RequestParam(name = "id", required = true) Integer id,
                                            @RequestParam(name = "categoriesId", required = false) Integer[] selectedCategoriesId,
                                            @RequestParam(name = "status", defaultValue = "-1") String status,
                                            @RequestParam(name = "check", defaultValue = "false") Boolean check,
                                            Model model, HttpSession session) {

        User loggedinUser = (User)session.getAttribute("user");
        if(loggedinUser != null) {
            System.out.println(loggedinUser.getRole().getName());
            String userRoleForUrl = switch (loggedinUser.getRole().getName()) {
                case "ROLE_ADMIN" -> "admin";
                case "ROLE_EXPERT" -> "expert";
                case "ROLE_SALE" -> "sale";
                case "ROLE_MARKETING" -> "marketing";
                case "ROLE_CUSTOMER" -> "user";
                default -> "";
            };
            System.out.println("userRoleForUrl: " + userRoleForUrl);
            model.addAttribute("userRoleForUrl", userRoleForUrl);
        }

        Subject subject = iSubjectService.getSubjectById(id);
        model.addAttribute("subject", subject);
        model.addAttribute("userSession", session.getAttribute("user"));
        return "course_content/subjectdetails";

    }

    @GetMapping("/admin/subject-details-edit")
    public String AdminEditSubjectDetails(@RequestParam(name = "id", required = true) Integer id, Model model) {
        Subject subject = iSubjectService.getSubjectById(id);
        model.addAttribute("subject", subject);
        return "course_content/subjectdetailsedit";
    }

    @GetMapping("/admin/subject-dimension")
    public String AdminGetToDimensionListPage(Model model) {
        List<DimensionDTO> dimension = (List<DimensionDTO>) iDimensionService.getAllDimension();
        model.addAttribute("dimension", dimension);
        return "course_content/dimension";
    }

    @GetMapping("/admin/subject-pricepackage")
    public String AdminGetToPricepackage(@RequestParam(name = "sid", required = true) Integer sid, Model model) {
        Pricepackage pricepackage = iPricepackageService.getPricepackageBySubId(sid);
        model.addAttribute("pricepackage", pricepackage);
        return "course_content/pricepackage";
    }

    // @GetMapping("/admin/new-pricepackage")

    @GetMapping("/expert/subject-details-edit")
    public String ExpertEditSubjectDetails(@RequestParam(name = "id", required = true) Integer id, Model model) {
        Subject subject = iSubjectService.getSubjectById(id);
        model.addAttribute("subject", subject);
        return "course_content/subjectdetailsedit";
    }

    @GetMapping("/expert/subject-dimension")
    public String ExpertGetToDimensionListPage(Model model) {
        List<DimensionDTO> dimension = (List<DimensionDTO>) iDimensionService.getAllDimension();
        model.addAttribute("dimension", dimension);
        return "course_content/dimension";
    }

    @GetMapping("/expert/subject-pricepackage")
    public String ExpertGetToPricepackage(@RequestParam(name = "sid", required = true) Integer sid, Model model) {
        Pricepackage pricepackage = iPricepackageService.getPricepackageBySubId(sid);
        model.addAttribute("pricepackage", pricepackage);
        return "course_content/pricepackage";
    }

}

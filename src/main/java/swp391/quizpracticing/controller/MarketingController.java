/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import swp391.quizpracticing.dto.BlogDTO;
import swp391.quizpracticing.dto.SliderDTO;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.model.*;
import swp391.quizpracticing.repository.IBlogRepository;
import swp391.quizpracticing.repository.ISliderRepository;
import swp391.quizpracticing.service.IBlogService;
import swp391.quizpracticing.service.ISliderService;
import swp391.quizpracticing.serviceimple.BlogService;
import swp391.quizpracticing.serviceimple.SliderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
public class MarketingController {
    @Autowired
    private ISliderService iSliderService;

    @Autowired
    private SliderService sliderService;

    @Autowired
    private ISliderRepository iSliderRepository;

    private final String FOLDER_PATH = "E:\\SWP391\\summer2023-swp391.se1714-g5\\summer2023-swp391.se1714-g5\\src\\main\\resources\\static\\database_images";

    private final String FOLDER_PATH_2 = "src/main/resources/static/database_images";

    @GetMapping("/slider/sliders-list")
    public String getToSliderListPage(Model model) {
        List<SliderDTO> slider = (List<SliderDTO>) iSliderService.getAllSlider();
        model.addAttribute("slider", slider);
        return "marketing/sliders_list";
    }

    @GetMapping("/slider/search")
    public String searchSlider(@RequestParam("searchTerm") String searchTerm, Model model, HttpSession session) {

        System.out.println("Call searchSlider() method!!!");
        if(searchTerm.isEmpty()) {
            return "redirect:/sliders-list";
        }
        //Search for sliders
        List<SliderDTO> searchedSlider = iSliderService.searchSliderByTitle(searchTerm);

        model.addAttribute("slider", searchedSlider);
        model.addAttribute("userSession", session.getAttribute("user"));

        return "marketing/sliders_list";
    }
    @GetMapping("/slider/slider-detail")
    public String showSliderDetails(@RequestParam(name = "id", required = true) Integer id, Model model) {
        Slider slider = sliderService.getSliderById(id);
        model.addAttribute("slider", slider);
        model.addAttribute("pageTitle", "SLIDER DETAIL (ID: " + id + ")");
        return "marketing/slider_detail";
    }

    @GetMapping("/slider/slider-detail-edit")
    public String showEditSlider(@RequestParam("id") Integer id,
                                 @ModelAttribute(name = "title") String title,
                                 @ModelAttribute(name = "backLink") String backLink,
                                 @ModelAttribute(name = "selectedStatus") String selectedStatus,
                                 @ModelAttribute(name = "image") MultipartFile image,
                                 Model model, HttpSession session, RedirectAttributes ra) {
        try {
            Slider slider = sliderService.get(id);
            UserDTO loggedinUser = (UserDTO)session.getAttribute("user");
            List<Boolean> status = new ArrayList<>(Arrays.asList(true, false));

            model.addAttribute("userSession", session.getAttribute("user"));
            model.addAttribute("status", status);
            model.addAttribute("slider", slider);
            model.addAttribute("pageTitle", "EDIT SLIDER (ID: " + id + ")");
            model.addAttribute("title", title);
            model.addAttribute("backLink", backLink);
            model.addAttribute("selectedStatus", selectedStatus);

            MultipartFile uploadedImg = (MultipartFile) session.getAttribute("image");
            model.addAttribute("image", uploadedImg);
            return "marketing/slider_edit";
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/slider/sliders-list";
        }
    }

    @PostMapping("/slider/save")
    public String saveSlider(@RequestParam(name = "id", required = true) Integer id,
                             @RequestParam(name = "image", required = false) MultipartFile multipartFile,
                             RedirectAttributes redirectAttribute,
                             Model model, Slider slider, HttpSession session) throws IOException {

        String fileName;
        String originalFileName;
        String fileExtension;

        if(multipartFile != null) {
            //Take the file name user has uploaded
            originalFileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            System.out.println("originalFileName: " + originalFileName);
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            fileName = System.currentTimeMillis() + fileExtension;
            System.out.println("fileName after changing: " + fileName);

            //Get the Absolute Path of the file Name (include file name)
            Path fileNameAndPath = Paths.get(FOLDER_PATH_2, fileName);
            System.out.println("fileNameAndPath: " + fileNameAndPath);
            //Store the actual file to the file directory
            Files.write(fileNameAndPath, multipartFile.getBytes());
        } else {
            fileName="null";
        }

        Slider existingSlider = sliderService.getSliderById(id);
        existingSlider.setTitle(slider.getTitle());
        existingSlider.setBackLink(slider.getBackLink());
        existingSlider.setStatus(slider.getStatus());
        existingSlider.setImage(fileName);

        sliderService.saveSlider(existingSlider);
        redirectAttribute.addFlashAttribute("existingSlider", existingSlider);
        session.setAttribute("existingSlider", existingSlider);
        redirectAttribute.addFlashAttribute("message", "The slider has been saved successfully.");
        return "redirect:../slider/sliders-list";
    }

    @GetMapping("/slider/new-slider")
    public String getToNewSliderForm(@ModelAttribute(name = "title") String title,
                                     @ModelAttribute(name = "backLink") String backLink,
                                     @ModelAttribute(name = "selectedStatus") String selectedStatus,
                                     @ModelAttribute(name = "image") MultipartFile image,
                                     @ModelAttribute(name = "ms1") String ms1,
                                     @ModelAttribute(name = "ms2") String ms2,
                                     Model model, HttpSession session) {

        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");

        List<Boolean> status = new ArrayList<>(Arrays.asList(true, false));

        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("status", status);

        if(ms1 != null || ms2 != null) {
            model.addAttribute("title", title);
            model.addAttribute("backLink", backLink);
            model.addAttribute("selectedStatus", selectedStatus);

            MultipartFile uploadedImg = (MultipartFile) session.getAttribute("image");
            model.addAttribute("image", uploadedImg);
            model.addAttribute("ms1", ms1);
            model.addAttribute("ms2", ms2);
        }
        return "marketing/slider_add";
    }

    @PostMapping("/slider/new-slider-submit")
    public String addNewSlider(@RequestParam(name = "title") String titleNotTrim,
                               @RequestParam(name = "backLink") String backLink,
                               @RequestParam(name = "status") Boolean status,
                               @RequestParam(name = "image", required = false) MultipartFile multipartFile,
                               RedirectAttributes redirectAttribute,
                               Model model, HttpSession session) throws IOException {

        Boolean check = true;
        String ms1 = "", ms2 = "";

        String title = titleNotTrim.trim();
        System.out.println("titleNotTrim: " + titleNotTrim);
        System.out.println("title: " + title);

        if(title.isEmpty() || multipartFile.isEmpty()) {
            check = false;

            String emptyError = "Please fill all the input before submit!";

            redirectAttribute.addFlashAttribute("title", title);
            redirectAttribute.addFlashAttribute("backLink", backLink);
            redirectAttribute.addFlashAttribute("selectedStatus", status.toString());
            redirectAttribute.addFlashAttribute("emptyError", emptyError);

            return "redirect:../slider/new-slider";
        }

        //check if slider name is existed
        if(iSliderService.checkIfSliderExistByTitle(title)) {
            System.out.println("check if slider exist: " + iSliderService.checkIfSliderExistByTitle(title));
            check = false;
            ms1 = "Slider Name Existed!";
            System.out.println("condition 1 fail");
        }

        System.out.println(check);

        if(check) {  //save new slider

            String fileName;
            String originalFileName;
            String fileExtension;

            if(multipartFile != null) {
                //Take the file name user has uploaded
                originalFileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
                System.out.println("originalFileName: " + originalFileName);
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                fileName = System.currentTimeMillis() + fileExtension;
                System.out.println("fileName after changing: " + fileName);


                //Get the Absolute Path of the file Name (include file name)
                Path fileNameAndPath = Paths.get(FOLDER_PATH_2, fileName);
                System.out.println("fileNameAndPath: " + fileNameAndPath);
                //Store the actual file to the file directory
                Files.write(fileNameAndPath, multipartFile.getBytes());
            } else {
                fileName="null";
            }

            Slider newSlider = new Slider();
            newSlider.setTitle(title);
            newSlider.setBackLink(backLink);
            newSlider.setStatus(status);
            newSlider.setImage(fileName);

            newSlider = iSliderRepository.save(newSlider);

            model.addAttribute("check", check);
            redirectAttribute.addFlashAttribute("newSlider", newSlider);
            redirectAttribute.addFlashAttribute("message", "The slider has been added successfully.");
            session.setAttribute("newSlider", newSlider);
            return "redirect:../slider/sliders-list?check=" + check;
        } else {  //errors

            System.out.println("ms1: " + ms1);
            System.out.println("ms2: " + ms2);

            redirectAttribute.addFlashAttribute("title", title);
            redirectAttribute.addFlashAttribute("backLink", backLink);
            redirectAttribute.addFlashAttribute("selectedStatus", status.toString());
            redirectAttribute.addFlashAttribute("image", multipartFile);
            session.setAttribute("image", multipartFile);
            redirectAttribute.addFlashAttribute("ms1", ms1);
            redirectAttribute.addFlashAttribute("ms2", ms2);
            return "redirect:../slider/new-slider";
        }
    }

//    @GetMapping("/filter")
//    public String filterSlider(@RequestParam("selectedStatus") List<String> selectedStatus, Model model, HttpSession session) {
//        //Find Selected Post Category by id
//        List<Slider> filterSlider = new ArrayList<>();
//        for(String selectedStatus : selectedStatus) {
//
//            if(selectedStatus.equals("0")) {
//                return "redirect:/sliders-list";
//            } else {
//                PostCategory postCategory = new PostCategory();
//                postCategory = iBlogCategoryService.findByPostCategoryId(Integer.parseInt(selectedCategory));
//                filteredCategories.add(postCategory);
//            }
//        }
//        //Get Filtered Posts by Selected Post Categories
//        List<PostsDTO> filteredPosts = iBlogService.getFilteredPosts(filteredCategories);
//
//        //Get all categories
//        List<PostCategoryDTO> postCategories = iBlogCategoryService.getAllCategories();
//
//        //Get latest blogs
//        List<PostsDTO> latestBlogs = iBlogService.getLatestPosts(2);
//
//        model.addAttribute("posts", filteredPosts);
//        model.addAttribute("postCategories", postCategories);
//        model.addAttribute("lastestBlogs", latestBlogs);
//
//        model.addAttribute("userSession", session.getAttribute("user"));
//        return "blogs_list/blogs_list";
//    }

    @Autowired
    private IBlogService iBlogService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private IBlogRepository iBlogRepository;

    @GetMapping("/blog/blogs-list")
    public String getToBlogListPage(Model model) {
        List<BlogDTO> blog = (List<BlogDTO>) iBlogService.getAllBlog();
        model.addAttribute("blog", blog);
        return "marketing/blogs_list";
    }

    @GetMapping("/blog/search")
    public String searchBlog(@RequestParam("searchTerm") String searchTerm, Model model, HttpSession session) {

        System.out.println("Call searchBlog() method!!!");
        if(searchTerm.isEmpty()) {
            return "redirect:/blogs-list";
        }
        //Search for posts
        List<BlogDTO> searchedBlog = iBlogService.searchBlogByTitle(searchTerm);

        model.addAttribute("blog", searchedBlog);
        model.addAttribute("userSession", session.getAttribute("user"));
        return "marketing/blogs_list";
    }

    @GetMapping("/blog/blog-detail")
    public String showBlogDetails(@RequestParam(name = "id", required = true) Integer id, Model model) {
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        model.addAttribute("pageTitle", "POST DETAIL (ID: " + id + ")");
        return "marketing/blog_detail";
    }

    @GetMapping("/blog/blog-detail-edit")
    public String showEditBlog(@RequestParam("id") Integer id,
                               @ModelAttribute(name = "title") String title,
                               @ModelAttribute(name = "briefInfo") String briefInfo,
                               @ModelAttribute(name = "content") String content,
                               @ModelAttribute(name = "selectedStatus") String selectedStatus,
                               @ModelAttribute(name = "image") MultipartFile image,
                               Model model, HttpSession session, RedirectAttributes ra) {
        try {
            Blog blog = blogService.get(id);
            UserDTO loggedinUser = (UserDTO)session.getAttribute("user");
            List<Boolean> status = new ArrayList<>(Arrays.asList(true, false));

            model.addAttribute("userSession", session.getAttribute("user"));
            model.addAttribute("status", status);
            model.addAttribute("blog", blog);
            model.addAttribute("pageTitle", "EDIT POST (ID: " + id + ")");
            model.addAttribute("title", title);
            model.addAttribute("briefInfo", briefInfo);
            model.addAttribute("content", content);
            model.addAttribute("selectedStatus", selectedStatus);

            MultipartFile uploadedImg = (MultipartFile) session.getAttribute("image");
            model.addAttribute("image", uploadedImg);
            return "marketing/blog_edit";
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/blog/blogs-list";
        }
    }

    @PostMapping("/blog/save")
    public String saveBlog(@RequestParam(name = "id", required = true) Integer id,
                           @RequestParam(name = "image", required = false) MultipartFile multipartFile,
                           RedirectAttributes redirectAttribute,
                           Model model, Blog blog, HttpSession session) throws IOException {

        String fileName;
        String originalFileName;
        String fileExtension;

        if(multipartFile != null) {
            //Take the file name user has uploaded
            originalFileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            System.out.println("originalFileName: " + originalFileName);
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            fileName = System.currentTimeMillis() + fileExtension;
            System.out.println("fileName after changing: " + fileName);

            //Get the Absolute Path of the file Name (include file name)
            Path fileNameAndPath = Paths.get(FOLDER_PATH_2, fileName);
            System.out.println("fileNameAndPath: " + fileNameAndPath);
            //Store the actual file to the file directory
            Files.write(fileNameAndPath, multipartFile.getBytes());
        } else {
            fileName="null";
        }

        Blog existingBlog = blogService.getBlogById(id);
        existingBlog.setTitle(blog.getTitle());
        existingBlog.setBriefInfo(blog.getBriefInfo());
        existingBlog.setContent(blog.getContent());
        existingBlog.setStatus(blog.getStatus());
        existingBlog.setThumbnail(fileName);

        blogService.saveBlog(existingBlog);
        redirectAttribute.addFlashAttribute("existingBlog", existingBlog);
        session.setAttribute("existingBlog", existingBlog);
        redirectAttribute.addFlashAttribute("message", "The blog has been saved successfully.");
        return "redirect:../blog/blogs-list";
    }

    @GetMapping("/blog/new-blog")
    public String getToNewBlogForm(@ModelAttribute(name = "title") String title,
                                   @ModelAttribute(name = "briefInfo") String briefInfo,
                                   @ModelAttribute(name = "content") String content,
                                   @ModelAttribute(name = "selectedStatus") String selectedStatus,
                                   @ModelAttribute(name = "thumbnail") MultipartFile thumbnail,
                                   @ModelAttribute(name = "ms1") String ms1,
                                   @ModelAttribute(name = "ms2") String ms2,
                                   Model model, HttpSession session) {

        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");

        List<Boolean> status = new ArrayList<>(Arrays.asList(true, false));

        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("status", status);

        if(ms1 != null || ms2 != null) {
            model.addAttribute("title", title);
            model.addAttribute("briefInfo", briefInfo);
            model.addAttribute("content", content);
            model.addAttribute("selectedStatus", selectedStatus);

            MultipartFile uploadedImg = (MultipartFile) session.getAttribute("thumbnail");
            model.addAttribute("thumbnail", uploadedImg);
            model.addAttribute("ms1", ms1);
            model.addAttribute("ms2", ms2);
        }
        return "marketing/blog_add";
    }

    @PostMapping("/blog/new-blog-submit")
    public String addNewBlog(@RequestParam(name = "title") String titleNotTrim,
                             @RequestParam(name = "briefInfo") String briefInfo,
                             @RequestParam(name = "content") String contentNotTrim,
                             @RequestParam(name = "status") Boolean status,
                             @RequestParam(name = "thumbnail", required = false) MultipartFile multipartFile,
                             RedirectAttributes redirectAttribute,
                             Model model, HttpSession session) throws IOException {

        Boolean check = true;
        String ms1 = "", ms2 = "";

        String title = titleNotTrim.trim();
        System.out.println("titleNotTrim: " + titleNotTrim);
        System.out.println("title: " + title);

        String content = contentNotTrim.trim();

        if(title.isEmpty() || content.isEmpty() || multipartFile.isEmpty()) {
            check = false;

            String emptyError = "Please fill all the input before submit!";

            redirectAttribute.addFlashAttribute("title", title);
            redirectAttribute.addFlashAttribute("briefInfo", briefInfo);
            redirectAttribute.addFlashAttribute("content", content);
            redirectAttribute.addFlashAttribute("selectedStatus", status.toString());
            redirectAttribute.addFlashAttribute("emptyError", emptyError);

            return "redirect:../blog/new-blog";
        }

        //check if blog name is existed
        if(iBlogService.checkIfBlogExistByBriefInfo(title)) {
            System.out.println("check if blog exist: " + iBlogService.checkIfBlogExistByBriefInfo(title));
            check = false;
            ms1 = "Blog Name Existed!";
            System.out.println("condition 1 fail");
        }

        System.out.println(check);

        if(check) {  //save new blog

            String fileName;
            String originalFileName;
            String fileExtension;

            if(multipartFile != null) {
                //Take the file name user has uploaded
                originalFileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
                System.out.println("originalFileName: " + originalFileName);
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                fileName = System.currentTimeMillis() + fileExtension;
                System.out.println("fileName after changing: " + fileName);


                //Get the Absolute Path of the file Name (include file name)
                Path fileNameAndPath = Paths.get(FOLDER_PATH_2, fileName);
                System.out.println("fileNameAndPath: " + fileNameAndPath);
                //Store the actual file to the file directory
                Files.write(fileNameAndPath, multipartFile.getBytes());
            } else {
                fileName="null";
            }

            Blog newBlog = new Blog();
            newBlog.setTitle(title);
            newBlog.setBriefInfo(briefInfo);
            newBlog.setContent(content);
            newBlog.setStatus(status);
            newBlog.setThumbnail(fileName);
            Date lastUpdatedTime=Date.valueOf(LocalDate.now());;
            newBlog.setLastUpdated(lastUpdatedTime);

            newBlog = iBlogRepository.save(newBlog);

            model.addAttribute("check", check);
            redirectAttribute.addFlashAttribute("newBlog", newBlog);
            redirectAttribute.addFlashAttribute("message", "The blog has been added successfully.");
            session.setAttribute("newBlog", newBlog);
            return "redirect:../blog/blogs-list?check=" + check;
        } else {  //errors

            System.out.println("ms1: " + ms1);
            System.out.println("ms2: " + ms2);

            redirectAttribute.addFlashAttribute("title", title);
            redirectAttribute.addFlashAttribute("briefInfo", briefInfo);
            redirectAttribute.addFlashAttribute("content",content);
            redirectAttribute.addFlashAttribute("selectedStatus", status.toString());
            redirectAttribute.addFlashAttribute("thumbnail", multipartFile);
            session.setAttribute("thumbnail", multipartFile);
            redirectAttribute.addFlashAttribute("ms1", ms1);
            redirectAttribute.addFlashAttribute("ms2", ms2);
            return "redirect:../blog/new-blog";
        }
    }

    @GetMapping("/admin/blog/delete")
    public String deleteBlog(@RequestParam("id") Integer id, RedirectAttributes ra) {
        try {
            blogService.delete(id);
            ra.addFlashAttribute("message", "The blog ID " + id + " has been deleted.");
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/blog/blogs-list";
    }
//    @GetMapping("/filter")
//    public String filterBlog(@RequestParam("selectedCategories") List<String> selectedCategories, Model model, HttpSession session) {
//        //Find Selected Post Category by id
//        List<PostCategory> filteredCategories = new ArrayList<>();
//        for(String selectedCategory : selectedCategories) {
//
//            if(selectedCategory.equals("0")) {
//                return "redirect:/blogs-list";
//            } else {
//                PostCategory postCategory = new PostCategory();
//                postCategory = iBlogCategoryService.findByPostCategoryId(Integer.parseInt(selectedCategory));
//                filteredCategories.add(postCategory);
//            }
//
//        }
//
//        //Get Filtered Posts by Selected Post Categories
//        List<PostsDTO> filteredPosts = iBlogService.getFilteredPosts(filteredCategories);
//
//        //Get all categories
//        List<PostCategoryDTO> postCategories = iBlogCategoryService.getAllCategories();
//
//        //Get latest blogs
//        List<PostsDTO> latestBlogs = iBlogService.getLatestPosts(2);
//
//        model.addAttribute("posts", filteredPosts);
//        model.addAttribute("postCategories", postCategories);
//        model.addAttribute("lastestBlogs", latestBlogs);
//
//        model.addAttribute("userSession", session.getAttribute("user"));
//        return "blogs_list/blogs_list";
//    }
}

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.quizpracticing.dto.BlogDTO;
import swp391.quizpracticing.dto.SliderDTO;
import swp391.quizpracticing.model.Blog;
import swp391.quizpracticing.model.Slider;
import swp391.quizpracticing.service.IBlogService;
import swp391.quizpracticing.service.ISliderService;

import java.util.List;

@Controller
public class MarketingController {
    @Autowired
    private ISliderService sliderService;

    @GetMapping("/slider/sliders-list")
    public String getToSliderListPage(Model model) {
        List<SliderDTO> slider = (List<SliderDTO>) sliderService.getAllSlider();
        model.addAttribute("slider", slider);
        return "marketing/sliders_list";
    }
//    @GetMapping("/sliders-list")
//    public String listByPage(@RequestParam(name = "page", required = false) Integer page, Model model, HttpSession session) {
//
//        System.out.println("pageNum="+page);
//
//        if (page == null) {
//            //Get all sliders with pagination
//                Page<Slider> sliderWithPagination = sliderService.getAllSlidersWithPagination(0);
//            model.addAttribute("sliders", sliderWithPagination);
//            return "marketing/sliders_list";
//        } else {
//            Page<Slider> sliderWithPagination = sliderService.getAllSlidersWithPagination(page - 1);
//            model.addAttribute("sliders", sliderWithPagination);
//        }
//        return "marketing/sliders_list";
//    }
    @GetMapping("/slider/search")
    public String searchSlider(@RequestParam("searchTerm") String searchTerm, Model model, HttpSession session) {

        System.out.println("Call searchSlider() method!!!");
        if(searchTerm.isEmpty()) {
            return "redirect:/sliders-list";
        }
        //Search for sliders
        List<SliderDTO> searchedSlider = sliderService.searchSliderByTitle(searchTerm);

        model.addAttribute("slider", searchedSlider);
        model.addAttribute("userSession", session.getAttribute("user"));

        return "marketing/sliders_list";
    }
    @GetMapping("/slider/slider-detail")
    public String showSliderDetails(@RequestParam(name = "id", required = true) Integer id, Model model) {
        Slider slider = sliderService.getSliderById(id);
        model.addAttribute("slider", slider);
        return "marketing/slider_edit";
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
    private IBlogService blogService;
    @GetMapping("/blog/blogs-list")
    public String getToBlogListPage(Model model) {
        List<BlogDTO> blog = (List<BlogDTO>) blogService.getAllBlog();
        model.addAttribute("blog", blog);
        return "marketing/blogs_list";
    }

    @GetMapping("/blogs-list")
    public String listBlogByPage(@RequestParam(name = "page", required = false) Integer page, Model model, HttpSession session) {
        System.out.println("pageNum="+page);
        if (page != null) {
            Page<Blog> blogWithPagination = blogService.getAllBlogsWithPagination(page - 1);
            model.addAttribute("blogs", blogWithPagination);
            return "marketing/blogs_list";
        } else {
            Page<Blog> blogWithPagination = blogService.getAllBlogsWithPagination(0);
            model.addAttribute("blogs", blogWithPagination);
        }
        return "marketing/blogs_list";
    }

    @GetMapping("/blog/search")
    public String searchBlog(@RequestParam("searchTerm") String searchTerm, Model model, HttpSession session) {

        System.out.println("Call searchBlog() method!!!");
        if(searchTerm.isEmpty()) {
            return "redirect:/blogs-list";
        }
        //Search for posts
        List<BlogDTO> searchedBlog = blogService.searchBlogByTitle(searchTerm);

        model.addAttribute("blog", searchedBlog);
        model.addAttribute("userSession", session.getAttribute("user"));
        return "marketing/blogs_list";
    }
    @GetMapping("/blog/blog-detail")
    public String showBlogDetails(@RequestParam(name = "id", required = true) Integer id, Model model) {
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        return "marketing/blog_edit";
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

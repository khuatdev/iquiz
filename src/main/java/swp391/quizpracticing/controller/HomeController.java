package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import swp391.quizpracticing.dto.BlogDTO;
import swp391.quizpracticing.dto.SliderDTO;
import swp391.quizpracticing.model.Subject;
import swp391.quizpracticing.model.*;
import swp391.quizpracticing.repository.*;
import swp391.quizpracticing.service.*;
import swp391.quizpracticing.serviceimple.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
	@Autowired
	private ISliderService sliderService;

	@Autowired
	private IBlogService blogService;

	@Autowired
	private ISubjectService subjectService;

	@Autowired
	private IUserRepository iUserRepository;

	@GetMapping("/")
	public String redirectToHome() {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String getToHomePage(Model model, HttpSession session) {

		//Get sliders
		List<SliderDTO> sliders = sliderService.getAllSlidersForHomepage();
		System.out.println("sliders size: " + sliders.size());

		//Get featured posts
		List<BlogDTO> blogs = blogService.getFeaturedPosts(true);
		System.out.println("blogs size: " + blogs.size());

		//Get latest posts
		List<BlogDTO> latestBlogs = blogService.getFeaturedLatestPosts(true, 2);
		System.out.println("latest blogs size: " + latestBlogs.size());

		//Get all Featured Subjects
		List<Subject> featuredSubjects = subjectService.findByFeaturing(true);
		System.out.println("featured subjects size: " + featuredSubjects.size());

		//Testing user Session
		User u = iUserRepository.findById(10);
		System.out.println(u);
		session.setAttribute("user", u);

		//Add to model
		model.addAttribute("sliders", sliders);
		model.addAttribute("blogs", blogs);
		model.addAttribute("latestBlogs", latestBlogs);
		model.addAttribute("featuredSubjects", featuredSubjects);

		model.addAttribute("userSession", session.getAttribute("user"));

		return "homepage/homepage";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home";
	}

}

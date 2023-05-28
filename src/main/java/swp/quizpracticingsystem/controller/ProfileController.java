/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp.quizpracticingsystem.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import swp.quizpracticingsystem.model.User;
import swp.quizpracticingsystem.model.Userprofile;
import swp.quizpracticingsystem.service.MyProfileService;

/**
 *
 * @author Lenovo
 */
@Controller
public class ProfileController {
    @Autowired
    MyProfileService service;
    @GetMapping("/profile")
    public String MyProfile(@RequestParam("userid") String userId, Model model, HttpSession session) {  
        User user =(User) session.getAttribute("user");
        String avatar = service.getAvatarByUserID();
        model.addAttribute("avatar", avatar);
        model.addAttribute("user", user);
        return "myprofile/MyProfile";
    }
    
}

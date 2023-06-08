/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.controller;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import swp391.quizpracticing.model.Role;
import swp391.quizpracticing.model.User;
import swp391.quizpracticing.service.RegisterService;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 * @author Lenovo
 */
@Controller
public class RegisterController {

    @Autowired
    private RegisterService service;

    @Autowired
    private JavaMailSenderImpl mailSender;

    @GetMapping("/register")
    public String register(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/home";
        } else {
            return "common/register";
        }
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, HttpServletRequest request, Model model) {
        List<User> getAllAccount = service.getAllAccount();
        boolean isExist = false;
        for (User user1 : getAllAccount) {
            if(user1.getEmail().equals(user.getEmail())) {
                isExist = true;
            }
        }
        if(isExist) {
            model.addAttribute("message", "Email has been used to create account!");
            return "common/register";
        } else {
        String randomCode = RandomString.make(64);
        user.setToken(randomCode);
        user.setEnable(false);
        //Role role = service.getRoleById();
        Role role = new Role(6, "customer", null, null, null);
        user.setRole(role);
        service.register(user);
        try {

            sendVerificationEmail(user, swp391.quizpracticing.Utils.Utility.getSiteURL(request));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "common/register_successfully";
        }
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (service.verify(code)) {
            return "common/verify_success";
        } else {
            return "common/verify_fail";
        }
    }

    public void sendVerificationEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "gmail@sss";
        String senderName = "IQuizz";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        System.out.println(user.getFullName());
        content = content.replace("[[name]]", user.getFullName());
        String verifyURL = siteURL + "/verify?code=" + user.getToken();

        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);

        mailSender.send(message);

    }

}

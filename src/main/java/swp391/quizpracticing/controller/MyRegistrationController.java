package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.model.*;
import swp391.quizpracticing.service.ICategoryService;
import swp391.quizpracticing.service.IPricepackageService;
import swp391.quizpracticing.service.ISubjectService;
import swp391.quizpracticing.service.IUserSubjectService;

import java.sql.Date;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class MyRegistrationController {
    private final IUserSubjectService userCourseService;
    private final ISubjectService subService;
    private final IPricepackageService packageService;
    private final ICategoryService categoryService;
    @Autowired
    private HttpSession UserSession;

    private List<MyRegistration> reg(Integer id) {
        List<Subject> subjects = userCourseService.courseById(id);
        List<MyRegistration> regis = new ArrayList<>(Collections.nCopies(subjects.size(), null));
        List<Date> dateRegis = new ArrayList<>();
        List<String> status = new ArrayList<>();
        List<String> registationId = new ArrayList<>();
        List<Integer> price = new ArrayList<>();

        for (UserSubject uc : userCourseService.listAll()) {
            if (uc.getUser().getId().intValue() == id) {
                for (Subject sub : subjects) {
                    if (sub.getId().intValue() == uc.getSubject().getId().intValue()) {

                        Date date = new Date(uc.getRegistrationTime().getTime());
                        dateRegis.add(date);
                        status.add(uc.getRegistrationStatus().getName());
                        registationId.add(uc.getUser().getId() + "" + uc.getSubject().getId());
                        price.add(uc.getPricePackage().getId());

                    }
                }
            }
        }
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < subjects.size(); i++) {
            MyRegistration r = new MyRegistration();
            r.setCid(subjects.get(i).getId());
            r.setSubName(subjects.get(i).getBriefInfo());
            r.setRegisId(registationId.get(i));
            r.setDateRegis(dateRegis.get(i));
            r.setStatus(status.get(i));
            r.setTotalcost(packageService.getById((Integer) price.get(i)).getSalePrice());
            r.setPackageName(packageService.getById((Integer) price.get(i)).getName());

            Date date = dateRegis.get(i);
            Date date1 = dateRegis.get(i);
            calendar.setTime(date);
            if (r.getPackageName().contains("3")) {
                calendar.add(Calendar.DATE, 90);
                date = new Date(calendar.getTimeInMillis());
            } else if (r.getPackageName().contains("6")) {
                calendar.add(Calendar.DATE, 180);
                date = new Date(calendar.getTimeInMillis());
            } else {
                date = null;
            }
            r.setValidTo(date);
            r.setValidfrom(date1);
            regis.set(i, r);
        }
        return regis;
    }
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    @GetMapping("/users/myregistration")
    public String showRegistration( Model model, @RequestParam(value = "id",required = false) String id) {
        try {
            Integer uid = Integer.parseInt(id);
            Object sessionInfo = UserSession.getAttribute("user");
            UserDTO u = (UserDTO) sessionInfo;

            List<MyRegistration> regis = reg(uid);
            List<Subject> subjects = userCourseService.courseById(uid);
            List<Category> cat = categoryService.listAll();
            model.addAttribute("sub", subjects);
            model.addAttribute("cat", cat);
            model.addAttribute("regis", regis);

            return "customer/my_registration";
        } catch (NumberFormatException e) {
            return "redirect:/home";
        }
    }

    @GetMapping("/users/myregistration/{cid}")
    public String regisCourse(@PathVariable("cid") Integer cid, @RequestParam(value = "uid",required = false) String id, Model model) {
        try {
            Integer uid = Integer.parseInt(id);

            Object sessionInfo =UserSession.getAttribute("user");
            UserDTO u = (UserDTO) sessionInfo;

            List<MyRegistration> regis = reg(u.getId());
            MyRegistration re = new MyRegistration();
            List<Category> cat = categoryService.listAll();
            model.addAttribute("cat", cat);

            for (MyRegistration r : regis) {
                if (r.getCid()==cid) {
                    re = r;
                    break;
                }
            }
            model.addAttribute("userSession", UserSession.getAttribute("user"));
            model.addAttribute("re", re);
            Subject su = subService.getById(cid);
            List<Pricepackage> price = packageService.getBySubjectId(cid);
            model.addAttribute("sub", su);
            model.addAttribute("pack", price);
            return "customer/my_registration";
        }
        catch (NumberFormatException e){
            return "redirect:/home";
        }


    }
}

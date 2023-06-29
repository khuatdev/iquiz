/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import swp391.quizpracticing.dto.PricepackageDTO;
import swp391.quizpracticing.dto.RegistrationstatusDTO;
import swp391.quizpracticing.dto.RoleDTO;
import swp391.quizpracticing.dto.SubjectDTO;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.dto.UserSubjectDTO;
import swp391.quizpracticing.service.IPricepackageService;
import swp391.quizpracticing.service.IRegistrationstatusService;
import swp391.quizpracticing.service.IRoleService;
import swp391.quizpracticing.service.ISubjectService;
import swp391.quizpracticing.service.IUserService;
import swp391.quizpracticing.service.IUserSubjectService;
import swp391.quizpracticing.service.IVerificationService;
import swp391.quizpracticing.service.RegisterService;

/**
 *
 * @author Mosena
 */
@Controller
@RequestMapping("/sale")
public class SaleController {
    
    @Autowired
    private IUserSubjectService userSubjectService;
    
    @Autowired
    private ISubjectService subjectService;
    
    @Autowired
    private IPricepackageService pricePackageService;
    
    @Autowired
    private IRegistrationstatusService statusService;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IVerificationService verifycationService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RegisterService registerService;
    
    @Autowired
    private IRoleService roleService;
    
    @GetMapping("/registrations-list")
    public String getRegistrations(HttpSession session, 
            @RequestParam(name = "pageNo", defaultValue = "1") String sPageNo,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy, 
            @RequestParam(name = "order", defaultValue = "asc") String order, 
            @RequestParam(name = "searchValue", required = false) String search, 
            @RequestParam(name = "validFrom", required = false) Timestamp validFrom, 
            @RequestParam(name = "validTo", required = false) Timestamp validTo,
            @RequestParam(name = "status", required = false) String status, 
            Model model){
        int pageSize=6;
        Integer pageNo;
        try{
            pageNo=Integer.parseInt(sPageNo);
        }
        catch(NumberFormatException ex){
            model.addAttribute("msg", "Not found");
            return "/sale/announce";
        }
        Page<UserSubjectDTO> registrations=userSubjectService.listAll(pageNo, 
                    pageSize, sortBy, order, search, 
                    validFrom, validTo, status);
        int totalPages=registrations.getTotalPages();
        if(totalPages==0 || pageNo<1 || pageNo>totalPages){
            model.addAttribute("msg", "Not found");
            return "/sale/announce";
        }
        List<SubjectDTO> subjectList=subjectService.findAll();
        List<RegistrationstatusDTO> statusListForm=statusService.findAll();
        List<PricepackageDTO> pricePackageList
                =pricePackageService.getBySubjectId(1);
        List<String> statusList=userSubjectService
                .getRegistrationStatusList(registrations);
        List<Timestamp> validFromList=userSubjectService
                .getValidFromList(registrations);
        List<Timestamp> validToList=userSubjectService
                .getValidToList(registrations);
        model.addAttribute("pricePackageList", pricePackageList);
        model.addAttribute("subjectList", subjectList);
        model.addAttribute("statusListForm", statusListForm);
        model.addAttribute("userSession", 
                session.getAttribute("user"));
        model.addAttribute("listPriceIni",
                pricePackageList.get(0).getListPrice());
        model.addAttribute("salePriceIni",
                pricePackageList.get(0).getSalePrice());
        model.addAttribute("statusList", statusList);
        model.addAttribute("validFromList", validFromList);
        model.addAttribute("validToList", validToList);
        model.addAttribute("registrations", registrations);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("totalPages",totalPages);
        return "/sale/registrations";
    }
    
    @GetMapping("/registration-detail")
    public String getRegistrationDetail(HttpSession session, 
            @RequestParam(name="registrationId") Integer id, 
            Model model){
        UserSubjectDTO registration=userSubjectService.getRegistration(id);
        UserDTO u=(UserDTO) session.getAttribute("user");
        if(u.getId().equals(registration.getUserCreated().getId())){
            model.addAttribute("isYours", true);
        }
        else{
            model.addAttribute("isYours", false);
        }
        List<SubjectDTO> subjectList=subjectService.findAll();
        List<PricepackageDTO> pricePackageList=pricePackageService
                .getBySubjectId(registration.getSubject().getId());
        List<RegistrationstatusDTO> statusList=statusService.findAll();
        model.addAttribute("userSession", 
                session.getAttribute("user"));
        model.addAttribute("registration", registration);
        model.addAttribute("pricePackageList", pricePackageList);
        model.addAttribute("subjectList", subjectList);
        model.addAttribute("statusList", statusList);
        return "/sale/registration-detail";
    }
    
    @PostMapping("/registration-detail/change")
    public String updateRegistration(HttpSession session,
            @RequestParam(name="registrationId") Integer id,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "fullName", required = false) String fullName,
            @RequestParam(name = "gender", required = false) Boolean gender,
            @RequestParam(name = "mobile", required = false) String mobile,
            @RequestParam(name = "subject", required = false) Integer subjectId,
            @RequestParam(name = "pricePackage", required = false) 
                    Integer pricePackageId,
            @RequestParam(name = "status", required = false) Integer statusId,
            @RequestParam(name = "notes", required = false) String note,
            @RequestParam(name = "validFrom", required = false) String validFrom,
            @RequestParam(name = "isYours") Boolean isYours,
            Model model){
        Boolean msg;
        try{
            UserDTO currentUser=(UserDTO) session.getAttribute("user");
            UserSubjectDTO registration=userSubjectService.getRegistration(id);
            if(isYours){
                UserDTO user=registration.getUser();
                user.setEmail(email);
                user.setFullName(fullName);
                user.setGender(gender);
                user.setMobile(mobile);
                if(userService.findUserByEmail(email)==null){
                    registerService.register(user);
                    if(statusId==1){
                    String randomPass=RandomString.make(12);
                    String randomCode = RandomString.make(64);
                    user.setPassword(passwordEncoder.encode(randomPass));
                    user.setEnable(false);
                    user.setToken(randomCode);
                    verifycationService.sendVerification(fullName, email, 
                            randomCode, randomPass);
                    }
                }
                registration.setUser(user);
                SubjectDTO subject=subjectService.getDTOById(subjectId);
                registration.setSubject(subject);
                PricepackageDTO pricePackage=pricePackageService.getById(pricePackageId);
                registration.setPricePackage(pricePackage);
                Timestamp time=Timestamp.valueOf(LocalDateTime.now());
                registration.setRegistrationTime(time);
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(validFrom, formatter);
                registration.setValidFrom(Timestamp.valueOf(dateTime));
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(registration.getValidFrom());
                calendar.add(Calendar.MONTH, pricePackage.getAccessDuration());
                registration.setValidTo(new Timestamp(calendar.getTimeInMillis()));
            }
            RegistrationstatusDTO status=statusService.getById(statusId);
            registration.setRegistrationStatus(status);
            registration.setNotes(note);
            registration.setUserUpdate(currentUser);
            userSubjectService.saveRegistration(registration);
            msg=true;
            return "redirect:/sale/announce?announcement="+msg;
        }
        catch(Exception ex){
            msg=false;
            ex.printStackTrace();
            return "redirect:/sale/announce?announcement="+msg;
        }
    }
    
    @PostMapping("/add-registration")
    public String addRegistration(HttpSession session,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "fullName", required = false) String fullName,
            @RequestParam(name = "gender", required = false) Boolean gender,
            @RequestParam(name = "mobile", required = false) String mobile,
            @RequestParam(name = "subject") Integer subjectId,
            @RequestParam(name = "pricePackage") Integer pricePackageId,
            @RequestParam(name = "status", defaultValue = "1") Integer statusId,
            @RequestParam(name = "notes", required = false) String note,
            @RequestParam(name = "validFrom", required = false) 
                    String validFrom,
            Model model){
        Boolean msg;
        try{
            UserDTO currentUser=(UserDTO) session.getAttribute("user");
            UserSubjectDTO registration=new UserSubjectDTO();
            UserDTO u=userService.findUserByEmail(email);
            if(u==null){
                UserDTO user=new UserDTO();
                user.setEmail(email);
                user.setFullName(fullName);
                user.setGender(gender);
                user.setMobile(mobile);
                String randomPass=RandomString.make(12);
                String randomCode = RandomString.make(64);

                user.setPassword(passwordEncoder.encode(randomPass));
                user.setEnable(false);
                user.setToken(randomCode);
                RoleDTO role=roleService.findRole(5);
                user.setRole(role);
                registerService.register(user);
                user=userService.findUserByEmail(email);
                registration.setUser(user);
                if(statusId==1){
                    verifycationService.sendVerification(fullName, email, 
                        randomCode, randomPass);
                }
            }
            else{
                registration.setUser(u);
            }
            SubjectDTO subject=subjectService.getDTOById(subjectId);
            registration.setSubject(subject);
            PricepackageDTO pricePackage=pricePackageService.getById(pricePackageId);

            RegistrationstatusDTO status=statusService.getById(statusId);
            registration.setRegistrationStatus(status);
            registration.setPricePackage(pricePackage);
            registration.setUserCreated(currentUser);
            registration.setUserUpdate(currentUser);

            Timestamp time=Timestamp.valueOf(LocalDateTime.now());
            registration.setRegistrationTime(time);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(validFrom, formatter);
            registration.setValidFrom(Timestamp.valueOf(dateTime));

            Calendar calendar=Calendar.getInstance();
            calendar.setTime(registration.getValidFrom());
            calendar.add(Calendar.MONTH, pricePackage.getAccessDuration());
            registration.setValidTo(new Timestamp(calendar.getTimeInMillis()));
            userSubjectService.addRegistration(registration);
            msg=true;
            return "redirect:/sale/announce?announcement="+msg;
        }
        catch(Exception ex){
            msg=false;
            return "redirect:/sale/announce?announcement="+msg;
        }
    }
    
    @GetMapping("/get-pricepackages")
    @ResponseBody
    public List<PricepackageDTO> getPricePackages(
            @RequestParam("subjectId") Integer subjectId){
        return pricePackageService.getBySubjectId(subjectId);
    }
    
    @GetMapping("/get-pricepackage")
    @ResponseBody
    public PricepackageDTO getPricePackage(
            @RequestParam("pricePackageId") Integer pricePackageId){
        return pricePackageService.getById(pricePackageId);
    }
    
    @GetMapping("/announce")
    public String announce(@RequestParam("announcement") Boolean condition,
            Model model){
        String msg;
        if(condition){
            msg="Successfully";
            model.addAttribute("msg", msg);
        }
        else{
            msg="Error found";
            model.addAttribute("msg", msg);
        }
        return "/sale/announce";
    }
}

package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.quizpracticing.dto.LessonDTO;

import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.model.*;
import swp391.quizpracticing.repository.ISubcategoryRepository;
import swp391.quizpracticing.service.*;

import java.net.http.HttpRequest;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class QuizzesController {
    @Autowired
    HttpSession userSession;
    @Autowired
    private ILessonService iLessonService;
    @Autowired
    private ISubjectService iSubjectService;
    @Autowired
    private ITesttypeService iTesttypeService;
    @Autowired
    private ILevelService iLevelService;
    @Autowired
    private ISubcategoryService iSubcategoryService;
    @Autowired
    private IQuestionService iQuestionService;
    @GetMapping("/testingcontent/quizzeslist")
    public String quizList(HttpSession session,
                             Model model,
                             @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                             @RequestParam(name="searchValue", required = false) String searchValue,
                             @RequestParam(name = "subject", required = false) Integer subjectId,
                             @RequestParam(name ="testtype", required=false) Integer quizTypeId,
                             @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                             @RequestParam(name = "order",defaultValue = "asc") String order){
        int pageSize=10;
        Page<Lesson> lessonDTOS=iLessonService.getLessons(pageNo, pageSize,
         searchValue, subjectId,  quizTypeId, sortBy, order);
        List<Subject> subjects= iSubjectService.listAll();

        List<Testtype> tests=iTesttypeService.listAll();
        int totalPages=lessonDTOS.getTotalPages();
        if(totalPages==0 || pageNo<1 || pageNo>totalPages){
            model.addAttribute("msg", "Not found");
            return "/admin/error";
        }
        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("subjects", subjects);
        model.addAttribute("types", tests);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("lessons", lessonDTOS);
        return "TestingContent/quizzesList";
    }
    @GetMapping("/testingcontent/add")
    public String addQuiz(Model model){
        Lesson lesson=new Lesson();

        Object sessionInfo = userSession.getAttribute("user");
        UserDTO u = (UserDTO) sessionInfo;
        Integer uid = u.getId();

        List<Subject> subjects= iSubjectService.listAll();
        List<Level> levels=iLevelService.listAll();
        Subject sub= iSubjectService.getById(2);
        List<Testtype> tests=iTesttypeService.listAll();
        List<SubCategoryDTO> subcategories=iSubcategoryService.findbySubId(2).stream().map(SubCategoryDTO::of).toList();
        List<Integer> numberOfQues = new ArrayList<>();
        for (SubCategoryDTO subcategoryDTO: subcategories){
            numberOfQues.add( iQuestionService.getQuestionByLessonAndSub(lesson.getId(),subcategoryDTO.id).size());
        }
        model.addAttribute("user", uid);

        model.addAttribute("duration", new Duration());
        model.addAttribute("lesson", lesson);
        model.addAttribute("subCategories", subcategories);
        model.addAttribute("numberOfQues", numberOfQues);
        model.addAttribute("subjects", subjects);
        model.addAttribute("levels", levels);
        model.addAttribute("types", tests);
        return "TestingContent/quizDetail";
    }
    @GetMapping("/testingcontent/quiz-details")
    public String quizDetail( Model model, @RequestParam(name = "quiz-id") Integer quizId){
        List<Subject> subjects= iSubjectService.listAll();
        List<Level> levels=iLevelService.listAll();
        Subject sub= iSubjectService.findByLesson(quizId);
        List<Testtype> tests=iTesttypeService.listAll();
        Lesson lesson = iLessonService.findId(quizId);
        List<SubCategoryDTO> subcategories=iSubcategoryService.findbySubId(sub.getId()).stream().map(SubCategoryDTO::of).toList();
        List<Integer> numberOfQues = new ArrayList<>();
        for (SubCategoryDTO subcategoryDTO: subcategories){
            numberOfQues.add( iQuestionService.getQuestionByLessonAndSub(lesson.getId(),subcategoryDTO.id).size());
        }
        Object sessionInfo = userSession.getAttribute("user");
        UserDTO u = (UserDTO) sessionInfo;
        Integer uid = u.getId();
        model.addAttribute("user", uid);
        model.addAttribute("lesson", lesson);
        model.addAttribute("duration", Duration.of(lesson.getDuration()));
        model.addAttribute("subCategories", subcategories);
        model.addAttribute("numberOfQues", numberOfQues);
        model.addAttribute("subjects", subjects);
        model.addAttribute("levels", levels);
        model.addAttribute("types", tests);
        return "TestingContent/quizDetail";
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class Duration {
        private int hour;
        private int minute;
        private int second;
        public static Duration of(Time duration) {
            return builder().hour(duration.toLocalTime().getHour())
                    .minute(duration.toLocalTime().getMinute())
                    .second(duration.toLocalTime().getSecond())
                    .build();
        }
    }

    @PostMapping("/quiz/save")
    public String save(Lesson lesson, HttpServletRequest request) {
        try {
            List<String> topic = Arrays.stream(request.getParameterValues("topicSelect")).toList();

            if (topic.size() >0) {
                iLessonService.save(lesson);
                for (int i = 0; i < topic.size(); i++) {
                    List<Question> qes = iQuestionService.getRandomBySubCategories(Integer.parseInt(topic.get(i)), topic.size());
                    for (Question qe : qes) {
                        iQuestionService.save(qe);
                    }
                }
            }


            return "redirect:/testingcontent/quizzeslist";

        }
        catch (Exception e){
            return "redirect:/testingcontent/quizzeslist";
        }

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class TypeDTO {
        private Integer id;
        private String name;

        public static TypeDTO of(Testtype testtype) {
            return builder().id(testtype.getId())
                    .name(testtype.getName())
                    .build();
        }
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class SubCategoryDTO {

        private Integer id;
        private String name;

        public static SubCategoryDTO of(Subcategory subcategory) {
            SubCategoryDTO s= new SubCategoryDTO();
            return builder().id(subcategory.getId())
                    .name(subcategory.getName())
                    .build();
        }
    }

}

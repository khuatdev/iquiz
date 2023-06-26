package swp391.quizpracticing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.quizpracticing.model.Lesson;
import swp391.quizpracticing.model.Question;
import swp391.quizpracticing.repository.*;
import swp391.quizpracticing.service.IQuestionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private IQuestionRepository iQuestionRepository;

    @Autowired
    private IQuestionService iQuestionService;
    @Autowired
    private ISubjectRepository iSubjectRepository;

    @Autowired
    private IDimensionRepository iDimensionRepository;
    @Autowired
    private ILevelRepository iLevelRepository;

    @Autowired
    private ILessonRepository iLessonRepository;

    @GetMapping("/questionList")
    public String getData(Model model,
                          @RequestParam(name = "page",required = false) Optional<Integer> page,
                          @RequestParam(name = "size",required = false) Optional<Integer> size,
                          @RequestParam(name = "lessonID",required = false) Integer lessonID,
                          @RequestParam(name = "dimensionID",required = false) Integer dimensionID,
                          @RequestParam(name = "level",required = false) Integer level,
                          @RequestParam(name = "status",required = false) Integer status,
                          @RequestParam(name = "search",required = false) String search
                          ) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        List<Question> list = iQuestionRepository.findAll();
        if(lessonID != null && lessonID != -1){
            list = list.stream().filter(n -> n.getLessons().contains(new Lesson(lessonID))).collect(Collectors.toList());
            model.addAttribute("lessonID",lessonID);
        }
        if(dimensionID != null && dimensionID != -1){
            list = list.stream().filter(n -> n.getDimension().getId() == dimensionID).collect(Collectors.toList());
            model.addAttribute("dimensionID",dimensionID);
        }
        if(level != null && level != -1){
            list = list.stream().filter(n -> n.getLevel().getId() == level).collect(Collectors.toList());
            model.addAttribute("level",level);
        }
        if(status != null && status != 0){
            boolean check = status == 1;
            list = list.stream().filter(n -> n.getStatus().booleanValue() == check).collect(Collectors.toList());
            model.addAttribute("status",status);
        }
        if(search != null && !search.trim().isEmpty()){
            list = list.stream().filter(n -> n.getContent().toLowerCase().contains(search.trim().toLowerCase())).collect(Collectors.toList());
            model.addAttribute("search",search);
        }
        Page<Question> listPaging = iQuestionService.findPaginated(PageRequest.of(currentPage - 1, pageSize),list);
        model.addAttribute("listPaging", listPaging);
        int totalPages = listPaging.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("lessonList",iLessonRepository.findAll());
        model.addAttribute("dimensionList",iDimensionRepository.findAll());
        model.addAttribute("levelList",iLevelRepository.findAll());
        return "/question/QuestionList.html";
    }
}

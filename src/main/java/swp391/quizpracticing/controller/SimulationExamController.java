package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.quizpracticing.dto.LessonDTO;
import swp391.quizpracticing.model.Subject;
import swp391.quizpracticing.model.User;
import swp391.quizpracticing.model.UserSubject;
import swp391.quizpracticing.service.ILessonService;
import swp391.quizpracticing.service.ISubjectService;
import swp391.quizpracticing.service.IUserSubjectService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SimulationExamController {

    @Autowired
    private IUserSubjectService iUserSubjectService;

    @Autowired
    private ILessonService iLessonService;

    @Autowired
    private ISubjectService iSubjectService;

    @GetMapping("/simulation-exam")
    public String getToSimulationExam(Model model, HttpSession session) {

        User loggedinUser = (User)session.getAttribute("user");

        //Get all accessed subjects of the logged-in user
        List<UserSubject> accessedSubjects = iUserSubjectService.getAllByUserId(loggedinUser.getId());

        //Get all lesson from the accessed subjects
        List<LessonDTO> accessedLessons = new ArrayList<>();
        for(UserSubject accessdSubject : accessedSubjects) {
            List<LessonDTO> lessons = iLessonService.findAllSimulationExamsBySubjectId(accessdSubject.getSubject().getId());
            accessedLessons.addAll(lessons);
        }

        model.addAttribute("accessedLessons", accessedLessons);
        model.addAttribute("accessedSubjects", accessedSubjects);

        model.addAttribute("userSession", session.getAttribute("user"));

        return "simulation_exam/simulation_exam";
    }

    @GetMapping("/search")
    public String searchByExamName(Model model, @RequestParam(name = "exam-name") String examName, HttpSession session) {

        User loggedinUser = (User)session.getAttribute("user");

        if(examName.isEmpty()) {
            model.addAttribute("examName", examName);
            return "redirect:/simulation-exam";
        } else {
            //Get all accessed subjects and accessed lessons of the logged in user
            List<UserSubject> accessedSubjects = iUserSubjectService.getAllByUserId(loggedinUser.getId());
            List<LessonDTO> accessedLessons = new ArrayList<>();
            for(UserSubject accessdSubject : accessedSubjects) {
                List<LessonDTO> lessons = iLessonService.findAllSimulationExamsBySubjectId(accessdSubject.getSubject().getId());
                accessedLessons.addAll(lessons);
            }

            //Find all lesson by exam-name
            List<LessonDTO> result_foundLessons = new ArrayList<>();
            List<LessonDTO> foundedLessons = iLessonService.searchByExamName(examName);
            for(LessonDTO lesson : foundedLessons) {
                for(LessonDTO accessedlesson : accessedLessons) {
                    if(accessedlesson.getId() == lesson.getId()) {
                        result_foundLessons.add(lesson);
                    }
                }
            }

            model.addAttribute("examName", examName);
            model.addAttribute("accessedLessons", result_foundLessons);
            model.addAttribute("accessedSubjects", accessedSubjects);

            model.addAttribute("userSession", session.getAttribute("user"));

            return "simulation_exam/simulation_exam";
        }

    }

    @GetMapping("/filter")
    public String filterBySubject(Model model, @RequestParam(name = "subject-id") Integer subjectId, HttpSession session) {

        User loggedinUser = (User)session.getAttribute("user");

        //Get all accessed subjects of the logged-in user
        List<UserSubject> accessedSubjects = iUserSubjectService.getAllByUserId(loggedinUser.getId());

        if(subjectId == -1) {
            model.addAttribute("accessedSubjects", accessedSubjects);
            return "redirect:/simulation-exam";
        } else {
            List<LessonDTO> filteredLessons = iLessonService.findAllSimulationExamsBySubjectId(subjectId);

            //Get the selected subject
            Subject selectedSubject = iSubjectService.getById(subjectId);
            System.out.println("Selected Subject: " + selectedSubject.getBriefInfo());

            model.addAttribute("accessedLessons", filteredLessons);
            model.addAttribute("accessedSubjects", accessedSubjects);
            model.addAttribute("selectedSubject", selectedSubject);
            model.addAttribute("userSession", session.getAttribute("user"));
            return "simulation_exam/simulation_exam";
        }

    }

}

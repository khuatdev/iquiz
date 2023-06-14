/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import java.sql.Timestamp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.model.Subject;
import swp391.quizpracticing.model.UserSubject;
import swp391.quizpracticing.repository.ISubjectRepository;
import swp391.quizpracticing.repository.IUserSubjectRepository;
import swp391.quizpracticing.service.IUserSubjectService;

import java.util.ArrayList;

import java.util.List;
import swp391.quizpracticing.dto.RegistrationstatusDTO;
import swp391.quizpracticing.dto.UserSubjectDTO;

/**
 *
 * @author Mosena
 */
@Service
public class UserSubjectService implements IUserSubjectService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IUserSubjectRepository userSubjectRepository;
    
    @Autowired
    private ISubjectRepository subjectRepository;
    
    @Override
    public List<UserSubject> getAllByUserId(Integer id) {
        return userSubjectRepository.getAllByUserIdAndRegistrationStatus(id, 1);
    }

    @Override
    public List<UserSubject> listAll() {

        return (List<UserSubject>) userSubjectRepository.findAll();
    }
    @Override
    public void save(UserSubject userSubject) {

        userSubjectRepository.save(userSubject);
    }

    @Override
    public UserSubject getId(UserSubject uk) {
        return null;
    }

    @Override
    public List<Subject> courseById(Integer id) {
        List<UserSubject> userCourses = listAll();
        List<UserSubject> filteredUserCourses = new ArrayList<>(); // create a new list

        for (UserSubject uc : userCourses) {
            if (uc.getUser().getId().equals(id)) {
                filteredUserCourses.add(uc); // add to the new list instead of original list
            }
        }

        List<Subject> subjects = new ArrayList<>();
        System.out.println(filteredUserCourses.size());
        for (UserSubject userCourse : filteredUserCourses) { // iterate over the filtered list
            Subject sub = subjectRepository.getById(userCourse.getSubject().getId());
            subjects.add(sub);
        }

        return subjects;
    }

    @Override
    public List<UserSubjectDTO> listAll(int pageNo, int pageSize, String sortBy, 
            String order, String subjectName, Integer subjectId, String email, 
            Timestamp validFrom, Timestamp validTo, RegistrationstatusDTO status) {
        return null;
    }

    @Override
    public void save(UserSubjectDTO userSubject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

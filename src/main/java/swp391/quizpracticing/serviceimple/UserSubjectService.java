/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.UserSubjectDTO;
import swp391.quizpracticing.model.UserSubject;
import swp391.quizpracticing.service.IUserSubjectService;

/**
 *
 * @author Mosena
 */
@Service
public class UserSubjectService implements IUserSubjectService {
    @Autowired
    private ModelMapper modelMapper;
    
    private UserSubjectDTO convertEntityToDTO(UserSubject entity){
        return modelMapper.map(entity,UserSubjectDTO.class);
    }
}

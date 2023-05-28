/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp.quizpracticingsystem.serviceImple;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import swp.quizpracticingsystem.model.Userprofile;
import swp.quizpracticingsystem.repository.MyProfileRepository;
import swp.quizpracticingsystem.service.MyProfileService;

/**
 *
 * @author Lenovo
 */
@Component
public class MyProfileServiceImpl implements MyProfileService{
    @Autowired
    MyProfileRepository repository;

    @Override
    public String getAvatarByUserID() {
        String avatar = repository.getAvatarByUserID();
        return avatar;
    }

    
}

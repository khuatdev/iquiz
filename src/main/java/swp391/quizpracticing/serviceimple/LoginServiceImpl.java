/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceImple;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.model.User;
import swp391.quizpracticing.repository.LoginRepository;
import swp391.quizpracticing.service.LoginService;

/**
 *
 * @author Lenovo
 */
@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    LoginRepository userRepository;

    @Override
    public User login(User user) {
        Optional<User> acc = userRepository.findUserByEmailAndPassword(user.getEmail(),
                user.getPassword());
        return acc.isPresent() ? acc.get() : null;
    }
    
}

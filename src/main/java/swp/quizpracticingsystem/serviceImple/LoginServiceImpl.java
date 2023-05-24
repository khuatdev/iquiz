/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp.quizpracticingsystem.serviceImple;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import swp.quizpracticingsystem.model.User;
import swp.quizpracticingsystem.repository.LoginRepository;
import swp.quizpracticingsystem.service.LoginService;

/**
 *
 * @author Lenovo
 */
@Component
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
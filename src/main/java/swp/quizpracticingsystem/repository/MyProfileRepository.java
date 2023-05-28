/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp.quizpracticingsystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp.quizpracticingsystem.model.Userprofile;

/**
 *
 * @author Lenovo
 */
@Repository
public interface MyProfileRepository extends JpaRepository<Userprofile, Integer>{
    @Query( value = "Select avatar from `userprofile` u"
            + "where u.user_id = ?1",nativeQuery = true)
    public String getAvatarByUserID();
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp.quizpracticingsystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp.quizpracticingsystem.model.Category;

/**
 *
 * @author Mosena
 */
@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    
    @Override
    public List<Category> findAll();
}

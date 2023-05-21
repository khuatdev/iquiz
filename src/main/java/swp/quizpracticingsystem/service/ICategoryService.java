/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp.quizpracticingsystem.service;

import java.util.List;
import swp.quizpracticingsystem.dto.CategoryDTO;

/**
 *
 * @author Mosena
 */
public interface ICategoryService {
    public List<CategoryDTO> findAll();
}

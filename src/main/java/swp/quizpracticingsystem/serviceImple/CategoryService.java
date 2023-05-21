/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp.quizpracticingsystem.serviceImple;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.quizpracticingsystem.dto.CategoryDTO;
import swp.quizpracticingsystem.model.Category;
import swp.quizpracticingsystem.repository.ICategoryRepository;
import swp.quizpracticingsystem.service.ICategoryService;

/**
 *
 * @author Mosena
 */
@Service
@Transactional
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;
    
    @Autowired
    private ModelMapper modelmapper;
    
    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }
    public CategoryDTO convertEntityToDTO(Category category){
        return modelmapper.map(category, CategoryDTO.class);
    }
}

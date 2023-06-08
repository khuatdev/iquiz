/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.PricepackageDTO;
import swp391.quizpracticing.model.Pricepackage;
import swp391.quizpracticing.repository.IPricepackageRepository;
import swp391.quizpracticing.service.IPricepackageService;

import java.util.List;

import swp391.quizpracticing.service.IPricepackageService;

/**
 *
 * @author Mosena
 */
@Service
public class PricepackageService implements IPricepackageService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IPricepackageRepository pricepackageRepository;
    
    private PricepackageDTO convertEntityToDTO(Pricepackage entity){
        return modelMapper.map(entity,PricepackageDTO.class);
    }

    @Override
    public Pricepackage getById(Integer id) {
        return pricepackageRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pricepackage> getBySubjectId(Integer id) {
        return pricepackageRepository.getPricepackageBySubjects(id);
    }
}

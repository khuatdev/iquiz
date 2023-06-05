/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.SettingsDTO;
import swp391.quizpracticing.model.Settings;
import swp391.quizpracticing.service.ISettingsService;

/**
 *
 * @author Mosena
 */
@Service
public class SettingsService implements ISettingsService {
    @Autowired
    private ModelMapper modelMapper;
    
    private SettingsDTO convertEntityToDTO(Settings entity){
        return modelMapper.map(entity,SettingsDTO.class);
    }
}
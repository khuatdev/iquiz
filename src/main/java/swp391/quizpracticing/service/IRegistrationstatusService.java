/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.service;

import java.util.List;
import swp391.quizpracticing.dto.RegistrationstatusDTO;

/**
 *
 * @author Mosena
 */
public interface IRegistrationstatusService {
    public List<RegistrationstatusDTO> findAll();
    
    public RegistrationstatusDTO getById(Integer id);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.Registrationstatus;

/**
 *
 * @author Mosena
 */
@Repository
public interface IRegistrationstatusRepository 
        extends JpaRepository<Registrationstatus,Integer> {
    @Query("Select rs from Registrationstatus rs where rs.name = :status")
    public Registrationstatus findByName(String status);
}

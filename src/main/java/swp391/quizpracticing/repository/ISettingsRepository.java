/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.Settings;

/**
 *
 * @author Mosena
 */
@Repository
public interface ISettingsRepository extends JpaRepository<Settings,Integer>, 
        JpaSpecificationExecutor<Settings> {
    @Override
    public Page<Settings> findAll(Specification spec,Pageable pageable);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.UserSubject;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import swp391.quizpracticing.model.Pricepackage;
import swp391.quizpracticing.model.Registrationstatus;
import swp391.quizpracticing.model.Subject;
import swp391.quizpracticing.model.User;

/**
 *
 * @author Mosena
 */
@Repository
public interface IUserSubjectRepository 
        extends JpaRepository<UserSubject,Integer>,
        JpaSpecificationExecutor<UserSubject> {

    @Query(value = "select * from iquiz.user_subject where user_id = :user_id and registration_status_id = :registration_status_id", nativeQuery = true)
    public List<UserSubject> getAllByUserIdAndRegistrationStatus(@Param("user_id") Integer userId, @Param("registration_status_id") Integer registrationId);

    //Nam's code
    @Override
    public Page<UserSubject> findAll(Specification specifiation,Pageable page);
    
    @Override
    public UserSubject save(UserSubject us);
    
    @Override
    public UserSubject getReferenceById(Integer id);
    
    
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("Update UserSubject us set us.registrationStatus= :status, us.notes = :notes "
            + "where us.id = :id")
    public void updateUserSubject(@Param("id")Integer id, 
            @Param("status")Registrationstatus status,
            @Param("notes")String notes);
    
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("Update UserSubject us set us.user = :user, "
            + "us.subject = :subject, "
            + "us.pricePackage= :pricePackage where us.id= :id")
    public void updateUserSubjectKey(@Param("id")Integer id, 
            @Param("user")User u, 
            @Param("subject")Subject s,
            @Param("pricePackage")Pricepackage p);
}

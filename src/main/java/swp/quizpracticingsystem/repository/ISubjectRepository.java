/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp.quizpracticingsystem.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp.quizpracticingsystem.model.Subject;

/**
 *
 * @author Mosena
 */
@Repository
public interface ISubjectRepository extends JpaRepository<Subject, Long> {

    @Override
    public Page<Subject> findAll(Pageable pageable);
    
    @Override
    public List<Subject> findAll();

    @Query(value="Select s from `subject` s "
            + "join category c "
            + "on s.idCategory=c.id "
            + "where c.catName=%:category%", nativeQuery = true)
    public Page<Subject> findByCategory(Pageable pageable, String category);

    @Query(value="Select s from `subject` s"
            + "where s.course_name like %:subjectName%",nativeQuery = true)
    public Page<Subject> searchSubjectName(Pageable pageable, 
            String subjectName);
}

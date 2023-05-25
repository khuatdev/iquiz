/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp.quizpracticingsystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp.quizpracticingsystem.model.PricePackage;

/**
 *
 * @author Mosena
 */
@Repository
public interface IPricePackageRepository extends 
                                        JpaRepository<PricePackage, Integer> {
    @Query(value="Select * from PricePackage "
            + "where idSub=?1",nativeQuery = true)
    public List<PricePackage> findAll(int subjectId);
    
    @Query(value="Select * from PricePackage "
            + "where idSub=?1",nativeQuery=true)
    public PricePackage findMinPricePackage(int subjectId);
    @Override
    public PricePackage getById(Integer id);
    public List<PricePackage> getBySubject(Integer subId);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp.quizpracticingsystem.service;

import org.springframework.data.domain.Page;
import swp.quizpracticingsystem.dto.SubjectDTO;

/**
 *
 * @author Mosena
 */
public interface ISubjectService {
    public Page<SubjectDTO> findPaginatedAllSubjects(int pageNo, int pageSize);
    public Page<SubjectDTO> filterSubjectByCategory(int pageNo,
                        int pageSize, int category);
    public Page<SubjectDTO> findSubjectBySubjectName(int pageNo,
                        int pageSize, String subjectName);
    public Page<SubjectDTO> findSubjectNameAndFilter(int pageNo, int pageSize,
                        String subjectName, int categoryId);
    public Page<SubjectDTO> sortSubjectBy(int pageNo, int pageSize, 
                        String sortBy);
}

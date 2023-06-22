/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import swp391.quizpracticing.dto.SubjectDTO;
import swp391.quizpracticing.model.Subject;

import java.io.IOException;
import java.util.List;

/**
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
                                          String sortBy, String order);

    public Page<SubjectDTO> filterAndSortSubject(int pageNo, int pageSize,
                                                 int category, String sortBy, String order);

    public Page<SubjectDTO> searchAndSortSubject(int pageNo, int pageSize,
                                                 String subjectName, String sortBy, String order);

    public Page<SubjectDTO> filterAndSearchAndSortSubject(int pageNo,
                                                          int pageSize, int category, String subjectName,
                                                          String sortBy, String order);
    List<SubjectDTO> findAll();
    List<Subject> findByExpertId(Integer id);

    public SubjectDTO getDTOById(Integer id);
            
    List<Subject> findByFeaturing(Boolean isFeatured);

    public List<Subject> listAll();

    public Page<Subject> getAllSubjectsPaginated(int pageNum, int itemPerPage);

    public Subject findByLesson(Integer id);

    public List<Subject> searchByCourseName(String s);

    public void save(Subject subject);

    public Subject getById(int id);

    List<SubjectDTO> getAllSubject();

    Subject getSubjectById(Integer id);

    public List<Subject> findSubjectsWithSorting(String field);

    public Page<Subject> findSubjectsWithPagination(int pageNum, int itemPerPage);

    public Page<Subject> findSubjectsWithPaginationByExpertId(Integer id, int pageNum, int itemPerPage);

    public Page<Subject> findSubjectsWithPaginationByExpertIdAndByName(Integer id, String searchTerm, int pageNum, int itemPerPage);

    public Page<Subject> findSubjectsWithPaginationByExpertIdAndByStatus(Integer id, Integer status, int pageNum, int itemPerPage);

    public Page<Subject> findSubjectsWithPaginationAndSorting(int pageNum, int itemPerPage, String field);

    public Page<Subject> searchForSubjectsByName(int pageNum, int itemPerPage, String searchTerm);

    public Page<Subject> findSubjectsByStatus(Boolean status, int pageNum, int itemPerPage);

    public Boolean checkIfSubjectExistByBriefInfo(String briefInfo);

    public String uploadImage(MultipartFile file) throws IOException;

}

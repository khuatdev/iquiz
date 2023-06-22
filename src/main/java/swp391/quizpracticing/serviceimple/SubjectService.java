/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import swp391.quizpracticing.dto.SubjectDTO;
import swp391.quizpracticing.model.Subject;
import swp391.quizpracticing.repository.ISubjectRepository;
import swp391.quizpracticing.service.ISubjectService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Mosena
 */
@Service
public class SubjectService implements ISubjectService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ISubjectRepository subjectRepository;

    @Autowired
    private ISubjectRepository iSubjectRepository;

    private SubjectDTO convertEntityToDTO(Subject entity){
        return modelMapper.map(entity,SubjectDTO.class);
    }

    @Override
    public Page<SubjectDTO> findPaginatedAllSubjects(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public Page<SubjectDTO> filterSubjectByCategory(int pageNo, int pageSize, int category) {
        return null;
    }

    @Override
    public Page<SubjectDTO> findSubjectBySubjectName(int pageNo, int pageSize, String subjectName) {
        return null;
    }

    @Override
    public Page<SubjectDTO> findSubjectNameAndFilter(int pageNo, int pageSize, String subjectName, int categoryId) {
        return null;
    }

    @Override
    public Page<SubjectDTO> sortSubjectBy(int pageNo, int pageSize, String sortBy, String order) {
        return null;
    }

    @Override
    public Page<SubjectDTO> filterAndSortSubject(int pageNo, int pageSize, int category, String sortBy, String order) {
        return null;
    }

    @Override
    public Page<SubjectDTO> searchAndSortSubject(int pageNo, int pageSize, String subjectName, String sortBy, String order) {
        return null;
    }

    @Override
    public Page<SubjectDTO> filterAndSearchAndSortSubject(int pageNo, int pageSize, int category, String subjectName, String sortBy, String order) {
        return null;
    }

    @Override
    public List<Subject> findByFeaturing(Boolean isFeatured) {
        return iSubjectRepository.findAllByFeatured(isFeatured);
    }

    @Override
    public List<Subject> listAll() {
        return iSubjectRepository.findAll();
    }
    @Override
    public Subject findByLesson(Integer id) {
        return iSubjectRepository.findByLessons_Id(id);
    }
    @Override
    public Page<Subject> getAllSubjectsPaginated(int pageNum, int itemPerPage) {
        return iSubjectRepository.findAllSubjectsPaginated(PageRequest.of(pageNum, itemPerPage));
    }

    @Override
    public List<Subject> searchByCourseName(String s) {
        return null;
    }

    @Override
    public void save(Subject subject) {

    }

    @Override
    public Subject getById(int id) {
        return iSubjectRepository.findById(id);
    }

    @Override
    public List<SubjectDTO> findAll() {
        return iSubjectRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Subject> findByExpertId(Integer id) {
        return null;
    }

    @Override
    public List<Subject> findSubjectsWithSorting(String field) {
        return iSubjectRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Override
    public Page<Subject> findSubjectsWithPagination(int pageNum, int itemPerPage) {
        return iSubjectRepository.findAll(PageRequest.of(pageNum, itemPerPage));
    }

    @Override
    public Page<Subject> findSubjectsWithPaginationByExpertId(Integer id, int pageNum, int itemPerPage) {
        Pageable pageRequest = PageRequest.of(pageNum, itemPerPage);
        return iSubjectRepository.findByOwnerId(id, pageRequest);
    }

    @Override
    public Page<Subject> findSubjectsWithPaginationByExpertIdAndByName(Integer id, String searchTerm, int pageNum, int itemPerPage) {
        return iSubjectRepository.findByOwnerIdAndName(id, searchTerm, PageRequest.of(pageNum, itemPerPage));
    }

    @Override
    public Page<Subject> findSubjectsWithPaginationByExpertIdAndByStatus(Integer id, Integer status, int pageNum, int itemPerPage) {
        return iSubjectRepository.findByOwnerIdAndStatus(id, status, PageRequest.of(pageNum,itemPerPage));
    }

    @Override
    public Page<Subject> findSubjectsWithPaginationAndSorting(int pageNum, int itemPerPage, String field) {
        return iSubjectRepository.findAll(PageRequest.of(pageNum, itemPerPage).withSort(Sort.by(Sort.Direction.ASC, field)));
    }

    @Override
    public Page<Subject> searchForSubjectsByName(int pageNum, int itemPerPage, String searchTerm) {
        return iSubjectRepository.findByBriefInfoContainingIgnoreCase(searchTerm, PageRequest.of(pageNum, itemPerPage));
    }

    @Override
    public Page<Subject> findSubjectsByStatus(Boolean status, int pageNum, int itemPerPage) {
        return iSubjectRepository.findByStatus(status, PageRequest.of(pageNum, itemPerPage));
    }

    @Override
    public Boolean checkIfSubjectExistByBriefInfo(String briefInfo) {
        return iSubjectRepository.existsSubjectByBriefInfo(briefInfo);
    }

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public List<SubjectDTO> getAllSubject() {
        List<Subject> subject = iSubjectRepository.findAll();
        return subject
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Subject getSubjectById(Integer id) {
        return iSubjectRepository.findById(id).get();

    }

    @Override
    public SubjectDTO getDTOById(Integer id) {
        return convertEntityToDTO(iSubjectRepository.getReferenceById(id));
    }


}

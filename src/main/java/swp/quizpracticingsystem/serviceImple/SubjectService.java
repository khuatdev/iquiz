/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp.quizpracticingsystem.serviceImple;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import swp.quizpracticingsystem.dto.SubjectDTO;
import swp.quizpracticingsystem.model.Subject;
import swp.quizpracticingsystem.repository.ISubjectRepository;
import swp.quizpracticingsystem.service.ISubjectService;

/**
 *
 * @author Mosena
 */
@Service
@Transactional
public class SubjectService implements ISubjectService {

    @Autowired
    private ISubjectRepository iSubjectRepository;
    
    @Autowired
    private ModelMapper modelmapper;
    
    @Override
    public Page<SubjectDTO> findPaginatedAllSubjects(int pageNo, int pageSize) {
        Pageable pageable=PageRequest.of(pageNo-1,pageSize);
        Page<Subject> subjectPage = iSubjectRepository.findAll(pageable);
        List<SubjectDTO> paginatedList=subjectPage
                .stream()
                .map(this::convertEntitytoDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(paginatedList,pageable,
                subjectPage.getTotalElements());
    }
    
    @Override
    public Page<SubjectDTO> filterSubjectByCategory(int pageNo, 
            int pageSize, int category) {
        Pageable pageable=PageRequest.of(pageNo-1, pageSize);
        Page<Subject> subjectPage = iSubjectRepository
                .findByCategory(pageable, category);
        List<SubjectDTO> paginatedList=subjectPage
                .stream()
                .map(this::convertEntitytoDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(paginatedList,pageable,
                subjectPage.getTotalElements());
    }

    @Override
    public Page<SubjectDTO> findSubjectBySubjectName(int pageNo, 
            int pageSize, String subjectName) {
        Pageable pageable=PageRequest.of(pageNo-1, pageSize);
        Page<Subject> subjectPage = iSubjectRepository
                .searchSubjectName(pageable, subjectName);
        List<SubjectDTO> paginatedList=subjectPage
                .stream()
                .map(this::convertEntitytoDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(paginatedList,pageable,
                subjectPage.getTotalElements());
    }
    
    @Override
    public Page<SubjectDTO> findSubjectNameAndFilter(int pageNo, int pageSize, 
            String subjectName, int categoryId) {
        Pageable pageable=PageRequest.of(pageNo-1, pageSize);
        Page<Subject> subjectPage=iSubjectRepository
                .searchSubjectNameAndCategory(pageable, subjectName, categoryId);
        List<SubjectDTO> paginatedList=subjectPage
                .stream()
                .map(this::convertEntitytoDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(paginatedList,pageable,
                subjectPage.getTotalElements());
    }
    
    @Override
    public Page<SubjectDTO> sortSubjectBy(int pageNo, int pageSize, String sortBy){
        Pageable pageable=PageRequest.of(pageNo-1, pageSize,
                Sort.by(sortBy));
        Page<Subject>subjectPage=iSubjectRepository.findAll(pageable);
        List<SubjectDTO>paginatedList=subjectPage
                .stream()
                .map(this::convertEntitytoDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(paginatedList,pageable,
                subjectPage.getTotalElements());
    }
    
    public SubjectDTO convertEntitytoDTO(Subject entity){
        return modelmapper.map(entity, SubjectDTO.class);
    }
}

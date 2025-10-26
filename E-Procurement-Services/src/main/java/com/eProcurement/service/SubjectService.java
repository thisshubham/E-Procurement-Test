package com.eProcurement.service;

import com.eProcurement.constants.Commonconstants;
import com.eProcurement.dto.SubjectDto;
import com.eProcurement.entity.Department;
import com.eProcurement.entity.Subject;
import com.eProcurement.repo.DepartmentRepo;
import com.eProcurement.repo.SubjectRepo;
import com.eProcurement.utility.ResponseDto;
import com.eProcurement.utility.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubjectService {
    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private SubjectRepo subjectRepo;

    @Autowired
    private ResponseEntity responseEntity;

    public ResponseDto createSubject(SubjectDto request) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Department department = departmentRepo.findById(request.getDepartmentId())
                    .orElse(null);
            if (Objects.isNull(department)){
                responseDto.setResponseMessege("Department not found");
                responseDto.setResponseCode(HttpStatus.NO_CONTENT.value());
                return responseDto;
            }
            Subject subject = new Subject();
            subject.setName(request.getName());
            subject.setCode(request.getCode());
            subject.setDescription(request.getDescription());
            subject.setTotalMarks(request.getTotalMarks() != null ? request.getTotalMarks() : 100);
            subject.setPassingMarks(request.getPassingMarks() != null ? request.getPassingMarks() : 40);
            subject.setActive(request.getActive() != null ? request.getActive() : true);
            subject.setDepartment(department);
            responseDto.setData(subjectRepo.save(subject));
            responseDto.setResponseMessege("Subject created successfully");
            responseDto.setResponseCode(HttpStatus.CREATED.value());
            return responseDto;
        } catch (RuntimeException e) {
            responseDto.setResponseMessege("Failure");
            responseDto.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            throw new RuntimeException(e);
        }
    }

    public org.springframework.http.ResponseEntity<?> getSubjectsByDepartment(Long departmentId) {
        HashMap<String ,Object> response = new HashMap<>();
        try {
            List<Subject> subjects = subjectRepo.findSubjectsByDepartment_Id(departmentId);
            if (Objects.nonNull(subjects)){
                response.put(Commonconstants.MESSAGE,Commonconstants.SUCCESS_STATUS);
                response.put("subjects",subjects);
                return responseEntity.apiResponse(HttpStatus.OK.value(), response);

            }else {
                response.put(Commonconstants.MESSAGE,"Data not found");
                return responseEntity.apiResponse(HttpStatus.NO_CONTENT.value(), response);
            }
        } catch (Exception e) {
            response.put(Commonconstants.MESSAGE,"Failure");
            return responseEntity.apiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), response);
        }
    }

    public List<Subject> getAllSubjects() {
         return subjectRepo.findAll();
    }

    public Subject getSubjectById(Long id) {
        return subjectRepo.findById(id).orElse(null);
    }
}

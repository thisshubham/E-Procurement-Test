package com.eProcurement.service;

import com.eProcurement.dto.SubjectDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    public SubjectDto createSubject(SubjectDto dto) {
        SubjectDto subjectDto =  new SubjectDto();
        return subjectDto;
    }

    public List<SubjectDto> getSubjectsByDepartment(Long departmentId) {
        List<SubjectDto> subjectDtos = new ArrayList<>();
        return subjectDtos;
    }

    public List<SubjectDto> getAllSubjects() {
        List<SubjectDto> subjectDtos = new ArrayList<>();
        return subjectDtos;
    }

    public SubjectDto getSubjectById(Long id) {
        SubjectDto subjectDto = new SubjectDto();
        return subjectDto;
    }
}

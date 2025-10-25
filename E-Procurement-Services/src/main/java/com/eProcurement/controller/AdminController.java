package com.eProcurement.controller;

import com.eProcurement.constants.Commonconstants;
import com.eProcurement.dto.*;
import com.eProcurement.entity.Admin;
import com.eProcurement.service.*;
import com.eProcurement.utility.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Commonconstants.BASE_URL + Commonconstants.ADMIN)
//@RequiredArgsConstructor
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentServ studentServ;


    @PostMapping(Commonconstants.DEPARTMENT)
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto dto) {
        return ResponseEntity.ok(departmentService.createDepartment(dto));
    }

    @GetMapping("/departments")
    public ResponseDto getDepartments() {
        ResponseDto responseDto = new ResponseDto();
        List<DepartmentDto> allDepartments = departmentService.getAllDepartments();
        if (allDepartments.isEmpty()) {
            responseDto.setResponseCode(404);
            responseDto.setResponseMessege("Data not found.");
            return responseDto;
        }
        responseDto.setResponseCode(200);
        responseDto.setResponseMessege("Success");
        responseDto.setDataList(allDepartments);
        return responseDto;
    }

    @PostMapping(Commonconstants.TEACHERS)
    public ResponseDto createTeacher(@RequestBody TeacherDto dto) {
        return userService.createTeacher(dto);
    }

    @PostMapping(Commonconstants.SUBJECT_QUESTION)
    public ResponseEntity<?> addQuestion(@RequestBody QuestionDto dto) {
        return questionService.addQuestion(dto.getSubjectId(), dto);
    }

    @PostMapping(Commonconstants.SUBJECT)
    public ResponseDto createSubject(@RequestBody SubjectDto dto) {
        return subjectService.createSubject(dto);
    }

    @GetMapping(Commonconstants.TEACHERS)
    public ResponseDto getTeachers() {
        return userService.getAllTeachers();
    }

    @PostMapping("/creStud")
    public StudentDto createTeacher(@RequestBody StudentDto dto) {
        if (dto.getUserRole().equals(Admin.Role.ADMIN.name())) {
            return studentServ.createStudent(dto);
        } else {
            throw new RuntimeException("Invalid Role");
        }
    }
}
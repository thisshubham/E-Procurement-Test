package com.eProcurement.controller;

import com.eProcurement.constants.Commonconstants;
import com.eProcurement.dto.AdminDto;
import com.eProcurement.dto.DepartmentDto;
import com.eProcurement.dto.TeacherDto;
import com.eProcurement.entity.Teacher;
import com.eProcurement.service.DepartmentService;
import com.eProcurement.service.UserService;
import com.eProcurement.utility.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Commonconstants.BASE_URL+Commonconstants.ADMIN)
//@RequiredArgsConstructor
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private  DepartmentService departmentService;
    @Autowired
    private  UserService userService;

    @PostMapping(Commonconstants.DEPARTMENT)
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto dto) {
        return ResponseEntity.ok(departmentService.createDepartment(dto));
    }

    @GetMapping("/departments")
    public ResponseDto getDepartments() {
        ResponseDto responseDto = new ResponseDto();
         List<DepartmentDto> allDepartments = departmentService.getAllDepartments();
         if(allDepartments.isEmpty()){
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

    @GetMapping(Commonconstants.TEACHERS)
    public ResponseDto getTeachers() {
        return userService.getAllTeachers();
    }
}


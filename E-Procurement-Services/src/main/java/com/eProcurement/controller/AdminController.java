package com.eProcurement.controller;

import com.eProcurement.constants.Commonconstants;
import com.eProcurement.dto.AdminDto;
import com.eProcurement.dto.DepartmentDto;
import com.eProcurement.service.DepartmentService;
import com.eProcurement.service.UserService;
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
    public ResponseEntity<List<DepartmentDto>> getDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping(Commonconstants.TEACHERS)
    public ResponseEntity<AdminDto> createTeacher(@RequestBody AdminDto dto) {
        return ResponseEntity.ok(userService.createTeacher(dto));
    }

    @GetMapping(Commonconstants.TEACHERS)
    public ResponseEntity<List<AdminDto>> getTeachers() {
        return ResponseEntity.ok(userService.getAllTeachers());
    }
}


package com.eProcurement.controller;

import com.eProcurement.constants.Commonconstants;
import com.eProcurement.dto.DepartmentDto;
import com.eProcurement.service.DepartmentService;
import com.eProcurement.utility.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Commonconstants.BASE_URL+Commonconstants.DEPARTMENT)
//@RequiredArgsConstructor
public class DepartmentController {
@Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAll() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping(Commonconstants.ID)
    public ResponseEntity<DepartmentDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PutMapping(Commonconstants.ID)
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto update(@PathVariable Long id, @RequestBody DepartmentDto dto) {
        return departmentService.updateDepartment(id, dto);
    }

    @DeleteMapping(Commonconstants.ID)
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto delete(@PathVariable Long id) {
        return  departmentService.deleteDepartment(id);
    }
}


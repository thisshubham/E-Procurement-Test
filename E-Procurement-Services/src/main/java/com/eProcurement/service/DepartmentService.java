package com.eProcurement.service;

import com.eProcurement.dto.DepartmentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    public DepartmentDto createDepartment(DepartmentDto dto) {
        DepartmentDto departmentDto = new DepartmentDto();
        return departmentDto;
    }

    public List<DepartmentDto> getAllDepartments() {
        List<DepartmentDto> departmentDtos = new ArrayList<>();
        return departmentDtos;
    }

    public DepartmentDto getDepartmentById(Long id) {
        DepartmentDto departmentDto = new DepartmentDto();
        return departmentDto;
    }

    public DepartmentDto updateDepartment(Long id, DepartmentDto dto) {
        DepartmentDto departmentDto = new DepartmentDto();
        return departmentDto;
    }

    public void deleteDepartment(Long id) {

    }
}

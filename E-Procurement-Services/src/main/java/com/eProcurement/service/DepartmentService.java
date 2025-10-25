package com.eProcurement.service;

import com.eProcurement.dto.DepartmentDto;
import com.eProcurement.entity.Department;
import com.eProcurement.repo.DepartmentRepo;
import com.eProcurement.utility.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;

    public DepartmentDto createDepartment(DepartmentDto dto) {
        Department department = new Department();
        department.setActive(dto.getActive());
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        department = departmentRepo.save(department);
        return new DepartmentDto(department);
    }

    public List<DepartmentDto> getAllDepartments() {
        List<DepartmentDto> departmentDtos = new ArrayList<>();
        try {
            List<Department> departments = departmentRepo.findAll();
            if (!departments.isEmpty()) {
                for (Department department : departments) {
                    if (department != null) {
                        DepartmentDto departmentDto = new DepartmentDto();
                        departmentDto.setId(department.getId());
                        departmentDto.setActive(department.getActive());
                        departmentDto.setName(department.getName());
                        departmentDto.setDescription(department.getDescription());
                        departmentDtos.add(departmentDto);
                    }
                }
                return departmentDtos;
            }
        } catch (DataAccessException ex) {
            return new ArrayList<>();
        } catch (Exception ex) {
            throw new RuntimeException("Error fetching departments", ex);
        }

        return departmentDtos;
    }


    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepo.findById(id).orElse(null);
        if (department != null) {
            return new DepartmentDto(department);
        }
        return null;
    }

    public ResponseDto updateDepartment(Long id, DepartmentDto dto) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Department department = departmentRepo.findById(id).orElse(null);
            if (Objects.nonNull(department)) {
                department.setDescription(dto.getDescription());
                department.setName(dto.getName());
                departmentRepo.save(department);
                responseDto.setResponseMessege("Success");
                responseDto.setResponseCode(HttpStatus.OK.value());
                responseDto.setData(department);
            } else {
                responseDto.setResponseMessege("Department not found");
                responseDto.setResponseCode(HttpStatus.NO_CONTENT.value());
            }
            return responseDto;
        } catch (Exception e) {
            responseDto.setResponseMessege("Failure");
            responseDto.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            throw new RuntimeException(e);

        }
    }

    public ResponseDto deleteDepartment(Long id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            Department department = departmentRepo.findById(id).orElse(null);
            if (Objects.nonNull(department)) {
                department.setIsDelete(true);
                departmentRepo.save(department);
                responseDto.setResponseMessege("Department delete Successfullt");
                responseDto.setResponseCode(HttpStatus.OK.value());
            } else {
                responseDto.setResponseMessege("Department not found");
                responseDto.setResponseCode(HttpStatus.NO_CONTENT.value());
            }
            return responseDto;
        } catch (Exception e) {
            responseDto.setResponseMessege("Failure");
            responseDto.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            throw new RuntimeException(e);

        }
        return responseDto;

    }
}

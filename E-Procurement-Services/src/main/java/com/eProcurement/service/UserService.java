package com.eProcurement.service;

import com.eProcurement.dto.AdminDto;
import com.eProcurement.dto.TeacherDto;
import com.eProcurement.entity.Department;
import com.eProcurement.entity.Teacher;
import com.eProcurement.repo.DepartmentRepo;
import com.eProcurement.repo.TeacherRepo;
import com.eProcurement.utility.ResponseDto;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private TeacherRepo teacherRepo;

    public ResponseDto createTeacher(TeacherDto request) {
        ResponseDto responseDto = new ResponseDto();
        try {
            if (request == null || request.getDepartmentId() == null) {
                responseDto.setResponseCode(400);
                responseDto.setResponseMessege("Invalid request: Teacher or Department data is missing.");
                return responseDto;
            }

            Department department = departmentRepo.findById(request.getDepartmentId())
                    .orElse(null);
            if(Objects.isNull(department)){
                responseDto.setResponseCode(HttpStatus.NO_CONTENT.value());
                responseDto.setResponseMessege("Department not found.");
                return responseDto;
            }

            Teacher teacher = new Teacher();
            teacher.setUsername(request.getUsername());
            teacher.setPassword(request.getPassword());
            teacher.setEmail(request.getEmail());
            teacher.setFullName(request.getFullName());
            teacher.setEmployeeId(request.getEmployeeId());
            teacher.setSpecialization(request.getSpecialization());
            teacher.setDepartment(department);
            teacher.setRole(Teacher.Role.TEACHER);
            teacher.setActive(true);

            Teacher savedTeacher = teacherRepo.save(teacher);

            responseDto.setResponseCode(HttpStatus.CREATED.value());
            responseDto.setResponseMessege("Teacher created successfully");
            responseDto.setData(savedTeacher);
        } catch (Exception e) {
            responseDto.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDto.setResponseMessege("An internal server error occurred.");
            return responseDto;
        }

        return responseDto;
    }


    public ResponseDto getAllTeachers() {
        ResponseDto responseDto = new ResponseDto();

        List<Teacher> teacherList = teacherRepo.findAll();
        if (teacherList.isEmpty() || teacherList ==null){
            responseDto.setResponseMessege("Data not found.");
            responseDto.setResponseCode(HttpStatus.OK.value());
        }else {
            responseDto.setResponseCode(HttpStatus.NO_CONTENT.value());

            responseDto.setResponseMessege("Success");
            responseDto.setDataList(teacherList);

        }
        return responseDto;
    }
}

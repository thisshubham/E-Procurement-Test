package com.eProcurement.service;

import com.eProcurement.dto.AdminDto;
import com.eProcurement.dto.TeacherDto;
import com.eProcurement.entity.Admin;
import com.eProcurement.entity.Department;
import com.eProcurement.entity.Teacher;
import com.eProcurement.repo.DepartmentRepo;
import com.eProcurement.repo.TeacherRepo;
import com.eProcurement.repo.UserRepo;
import com.eProcurement.utility.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService  {
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private UserRepo userRepo;

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
            teacher.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(request.getPassword()));
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
        }
        catch (DataIntegrityViolationException e) {
            responseDto.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDto.setResponseMessege("An internal server error occurred.");
            return responseDto;
        }catch (Exception e) {
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

    public Admin findByUserName(String username) {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        try {
            Admin adminUser = userRepo.findByUsername(username);
            if (adminUser == null) {
                System.out.println("Admin not found for username: " + username);
            }
            return adminUser;
        } catch (Exception e) {
            System.err.println("Error finding admin by username: " + e.getMessage());
            return null;
        }
    }

    public Admin saveUser(AdminDto user) {
        if (user == null) {
            return null;
        }
        try {
            user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
            Admin admin = new Admin(user);
            Admin savedAdmin = userRepo.save(admin);
            return savedAdmin;
        } catch (Exception e) {
            System.err.println("Error saving admin: " + e.getMessage());
            return null;
        }
    }

}

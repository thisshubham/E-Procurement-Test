package com.eProcurement.service;

import com.eProcurement.dto.StudentDto;
import com.eProcurement.entity.Admin;
import com.eProcurement.entity.Department;
import com.eProcurement.entity.Student;
import com.eProcurement.entity.Teacher;
import com.eProcurement.repo.DepartmentRepo;
import com.eProcurement.repo.StudentRepo;
import com.eProcurement.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServ {

    @Autowired
    private StudentRepo repo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private StudentRepo studentRepo;

    public StudentDto createStudent(StudentDto studentDto) {
        Optional<Department> deptOpt = departmentRepo.findById(studentDto.getDepartmentId());
        Optional<Teacher> teacherOpt = teacherRepo.findById(studentDto.getTeacherId());

        if (!deptOpt.isPresent() || !teacherOpt.isPresent()) {
            return null;
        }

        Student student = new Student();
        student.setStudentId(studentDto.getStudentId());
        student.setEnrollmentYear(studentDto.getEnrollmentYear());
        student.setDepartment(deptOpt.get());
        student.setTeacher(teacherOpt.get());
        student.setFullName((studentDto.getName()));
        student.setEmail(studentDto.getEmail());
        student.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(studentDto.getPassword()));
        student.setActive(true);
        student.setUsername(studentDto.getUserName());
        if (Admin.Role.STUDENT.name().equals(studentDto.getRole())) {
            student.setRole(Admin.Role.STUDENT);
        }

        Student saved = studentRepo.save(student);

        StudentDto responseDto = mapToDto(saved);
        return responseDto;
    }

    public StudentDto getStudentById(Long studentId) {
        Optional<Student> studentOpt = studentRepo.findById(studentId);
        if (!studentOpt.isPresent()) {
            return null;
        }

        StudentDto dto = mapToDto(studentOpt.get());
        return dto;
    }

    private StudentDto mapToDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setStudentId(student.getStudentId());
        dto.setEnrollmentYear(student.getEnrollmentYear());

        dto.setDepartmentId(student.getDepartment().getId());
        dto.setDepartmentName(student.getDepartment().getName());

        dto.setTeacherId(student.getTeacher().getId());
        dto.setTeacherName(student.getTeacher().getFullName());

        return dto;
    }

    public List<Student> getAllStudents() {
        List<Student> students = repo.findAll();
        return students;
    }


    public String deleteStudent(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Student deleted successfully";
        } else {
            return "Student not found";
        }
    }

    public List<Student> getStudentsByDepartmentId(Long departmentId) {
        List<Student> students = repo.findByDepartmentId(departmentId);
        return students;
    }

    public StudentDto getStudails(String studentId) {
         Student studentByStudentIdCode = studentRepo.findStudentByStudentIdCode(studentId);
         if(Objects.nonNull(studentByStudentIdCode)){
             StudentDto dto = new StudentDto();
             dto.setId(studentByStudentIdCode.getId());
             dto.setName(studentByStudentIdCode.getFullName());
             dto.setEmail(studentByStudentIdCode.getEmail());
             if (studentByStudentIdCode.getDepartment() != null) {
                 dto.setDepartmentId(studentByStudentIdCode.getDepartment().getId());
                 dto.setDepartmentName(studentByStudentIdCode.getDepartment().getName());
             }

             if (studentByStudentIdCode.getTeacher() != null) {
                 dto.setTeacherId(studentByStudentIdCode.getTeacher().getId());
                 dto.setTeacherName(studentByStudentIdCode.getTeacher().getFullName());
             }
             dto.setEnrollmentYear(studentByStudentIdCode.getEnrollmentYear());
             dto.setRole(studentByStudentIdCode.getRole().name());
             dto.setPassword(studentByStudentIdCode.getPassword());
             return dto;

         }
         return null;
    }
}
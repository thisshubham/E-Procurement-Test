package com.eProcurement.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Long id;

    private String name;
    private String email;
    private String role;
    private String password;
    private String userName;

    private String studentId;
    private String enrollmentYear;

    private Long departmentId;
    private String departmentName;

    private Long teacherId;
    private String teacherName;
}
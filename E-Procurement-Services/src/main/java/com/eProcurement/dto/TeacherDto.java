package com.eProcurement.dto;
import com.eProcurement.entity.Admin;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)

public class TeacherDto extends Admin {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String employeeId;
    private String specialization;
    private Long departmentId;
}

package com.eProcurement.dto;

import com.eProcurement.entity.Admin;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AdminDto  {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Admin.Role role;
    private Boolean active = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    public enum Role {
        ADMIN, TEACHER, STUDENT
    }
}

package com.eProcurement.service;

import com.eProcurement.dto.AdminDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    public AdminDto createTeacher(AdminDto dto) {
        AdminDto userDto = new AdminDto();
        return userDto;
    }

    public List<AdminDto> getAllTeachers() {
        List<AdminDto> userDtos = new ArrayList<>();
        return userDtos;
    }
}

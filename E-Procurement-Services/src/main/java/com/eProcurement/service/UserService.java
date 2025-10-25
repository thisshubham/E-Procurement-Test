package com.eProcurement.service;

import com.eProcurement.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    public UserDto createTeacher(UserDto dto) {
        UserDto userDto = new UserDto();
        return userDto;
    }

    public List<UserDto> getAllTeachers() {
        List<UserDto> userDtos = new ArrayList<>();
        return userDtos;
    }
}

package com.eProcurement.controller;

import com.eProcurement.constants.Commonconstants;
import com.eProcurement.dto.AdminDto;
import com.eProcurement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Commonconstants.BASE_URL+Commonconstants.AUTH)
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/save")
    public String saveUser(AdminDto user){
        userService.saveUser(user);
        return "Success";
    }
}

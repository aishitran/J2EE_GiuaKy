package com.example.J2EE_BaiTap06.controller;

import com.example.J2EE_BaiTap06.entity.*;
import com.example.J2EE_BaiTap06.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class AuthController {

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerForm(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Student student){

        // encode password
        student.setPassword(passwordEncoder.encode(student.getPassword()));

        // get STUDENT role
        Role role = roleRepo.findByName("STUDENT");

        // assign role
        student.setRoles(Set.of(role));

        // save student
        studentRepo.save(student);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
package com.example.J2EE_BaiTap06.controller;

import com.example.J2EE_BaiTap06.entity.*;
import com.example.J2EE_BaiTap06.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class EnrollmentController {

@Autowired
EnrollmentRepository enrollmentRepo;

@Autowired
CourseRepository courseRepo;

@Autowired
StudentRepository studentRepo;

@PostMapping("/enroll/{courseId}")
public String enroll(@PathVariable Long courseId, Principal principal){

    Student student = studentRepo.findByUsername(principal.getName());

    Course course = courseRepo.findById(courseId).orElse(null);

    if(course == null){
        return "redirect:/home";
    }

    Enrollment e = new Enrollment();

    e.setStudent(student);
    e.setCourse(course);
    e.setEnrollDate(LocalDate.now());

    enrollmentRepo.save(e);

    return "redirect:/mycourses";
}

@GetMapping("/mycourses")
public String mycourses(Model model, Principal principal){

    Student student = studentRepo.findByUsername(principal.getName());

    List<Enrollment> list = enrollmentRepo.findByStudent(student);

    model.addAttribute("courses", list);

    return "mycourse";
}

}
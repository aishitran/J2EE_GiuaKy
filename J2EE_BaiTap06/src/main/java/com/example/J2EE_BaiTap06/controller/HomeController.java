package com.example.J2EE_BaiTap06.controller;

import com.example.J2EE_BaiTap06.entity.Course;
import com.example.J2EE_BaiTap06.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

@Autowired
CourseRepository courseRepo;

@GetMapping({"/","/home"})
public String home(Model model,
                   @RequestParam(defaultValue = "0") int page,
                   @RequestParam(required = false) String keyword){

    Pageable pageable = PageRequest.of(page,5);

    Page<Course> coursePage;

    if(keyword != null && !keyword.isEmpty()){
        coursePage = courseRepo.findByNameContaining(keyword,pageable);
    }else{
        coursePage = courseRepo.findAll(pageable);
    }

    model.addAttribute("courses", coursePage.getContent());
    model.addAttribute("totalPages", coursePage.getTotalPages());
    model.addAttribute("currentPage", page);
    model.addAttribute("keyword", keyword);

    return "home";
}

}
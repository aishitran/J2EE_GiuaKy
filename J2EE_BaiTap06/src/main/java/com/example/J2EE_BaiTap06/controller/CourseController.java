package com.example.J2EE_BaiTap06.controller;

import com.example.J2EE_BaiTap06.entity.Course;
import com.example.J2EE_BaiTap06.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class CourseController {

    @Autowired
    CourseRepository courseRepo;

    @GetMapping("/courses")
    public String list(Model model){

        model.addAttribute("courses", courseRepo.findAll());

        return "course";
    }

    @GetMapping("/course/new")
    public String newCourse(Model model){

        model.addAttribute("course", new Course());

        return "course-form";
    }

    @GetMapping("/course/edit/{id}")
    public String edit(@PathVariable Long id, Model model){

        Course course = courseRepo.findById(id).orElse(null);

        model.addAttribute("course",course);

        return "course-form";
    }

    @PostMapping("/course/save")
    public String save(@ModelAttribute Course course){

        courseRepo.save(course);

        return "redirect:/admin/courses";
    }

    @GetMapping("/course/delete/{id}")
    public String delete(@PathVariable Long id){

        courseRepo.deleteById(id);

        return "redirect:/admin/courses";
    }
}
package com.example.J2EE_BaiTap06.repository;

import com.example.J2EE_BaiTap06.entity.Course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findByNameContaining(String keyword, Pageable pageable);

}
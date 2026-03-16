package com.example.J2EE_BaiTap06.repository;

import com.example.J2EE_BaiTap06.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Student findByUsername(String username);

}
package com.example.J2EE_BaiTap06.repository;

import com.example.J2EE_BaiTap06.entity.Enrollment;
import com.example.J2EE_BaiTap06.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudent(Student student);

}
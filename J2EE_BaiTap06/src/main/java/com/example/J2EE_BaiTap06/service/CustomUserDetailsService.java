package com.example.J2EE_BaiTap06.service;

import com.example.J2EE_BaiTap06.entity.Student;
import com.example.J2EE_BaiTap06.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

@Autowired
StudentRepository studentRepo;

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Student student = studentRepo.findByUsername(username);

    if(student == null){
        throw new UsernameNotFoundException("User not found");
    }

    return new User(
            student.getUsername(),
            student.getPassword(),
            student.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                    .collect(Collectors.toList())
    );
}

}
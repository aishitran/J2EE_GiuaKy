package com.example.J2EE_BaiTap06.repository;

import com.example.J2EE_BaiTap06.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
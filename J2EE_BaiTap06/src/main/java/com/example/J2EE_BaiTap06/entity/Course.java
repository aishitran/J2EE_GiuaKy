package com.example.J2EE_BaiTap06.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    private int credits;

    private String lecturer;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}
package com.example.reportcard.model;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String studentClass;
    private String section;

    public Student() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public String getSection() {
        return section;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
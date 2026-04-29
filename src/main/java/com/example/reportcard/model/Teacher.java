package com.example.reportcard.model;

import jakarta.persistence.*;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String teacherClass;
    private String section;

    public Teacher() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeacherClass() {
        return teacherClass;
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

    public void setTeacherClass(String teacherClass) {
        this.teacherClass = teacherClass;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
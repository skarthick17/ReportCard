package com.example.reportcard.model;

import jakarta.persistence.*;

@Entity
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    private String subject;

    private int examMarks;

    private int assignmentMarks;

    private String month;

    public Marks() {}

    public Long getId() {
        return id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getSubject() {
        return subject;
    }

    public int getExamMarks() {
        return examMarks;
    }

    public int getAssignmentMarks() {
        return assignmentMarks;
    }

    public String getMonth() {
        return month;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setExamMarks(int examMarks) {
        this.examMarks = examMarks;
    }

    public void setAssignmentMarks(int assignmentMarks) {
        this.assignmentMarks = assignmentMarks;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
